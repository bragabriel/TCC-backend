package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageCaronaResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.CaronaImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.images.ImageCaronaRepository;
import br.com.spotted.backend.service.CaronaService;
import br.com.spotted.backend.service.StorageService;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.ecr.model.ImageNotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ImageCaronaService {

    @Autowired
    private ImageCaronaRepository imageCaronaRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CaronaService caronaService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageCaronaResponse>> createCaronaImage(MultipartFile[] file, Long idItem) throws IOException {

        var carona = caronaService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageCaronaResponse> caronaImageCaronaResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            CaronaImage caronaImage = new CaronaImage();

            String finalUrl = concatenarUrl(caronaImage.getUrl());

            caronaImage.setIdCarona(idItem);
            caronaImage.setSequence(i);
            caronaImage.setFileName(retorno.get(i));
            caronaImage.setUrl(finalUrl);

            caronaImageCaronaResponseList.add(new ImageCaronaResponse(caronaImage, retorno.get(i)));

            var caronaImageCreated = imageCaronaRepository.save(caronaImage);
        }

        return new ResponseBase<>(caronaImageCaronaResponseList);
    }

    public ResponseBase<List<ImageCaronaResponse>> findCaronaImageByIdCarona(Long idItem) {

        var carona = caronaService.pesquisarPorId(idItem);

        if(carona.getObjetoRetorno().getListaImagensCarona().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageCaronaResponse> caronaImageList = new ArrayList<>();

        for (int i = 0; i < carona.getObjetoRetorno().getListaImagensCarona().size(); i++) {

            var key = carona.getObjetoRetorno().getListaImagensCarona().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageCaronaResponse userImageCaronaResponse = new ImageCaronaResponse();

            userImageCaronaResponse.setIdCarona(idItem);
            userImageCaronaResponse.setLocalUrl(carona.getObjetoRetorno().getListaImagensCarona().get(i).getUrl());
            userImageCaronaResponse.setSequence(carona.getObjetoRetorno().getListaImagensCarona().get(i).getSequence());
            userImageCaronaResponse.setIdImage(carona.getObjetoRetorno().getListaImagensCarona().get(i).getIdImage());
            userImageCaronaResponse.setFinalUrl(signedUrl);

            caronaImageList.add(userImageCaronaResponse);
        }

        return new ResponseBase<>(caronaImageList);
    }

    public ResponseBase<ImageCaronaResponse> updateCaronaImageSequence(Long caronaImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageCaronaRepository.findById(caronaImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageCaronaRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageCaronaResponse(
                image,
                finalUrl
        ));
    }

    public ImageCaronaResponse deleteCaronaImage(Long caronaImageId) {
        var imagemEncontrada = imageCaronaRepository.findById(caronaImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        imageCaronaRepository.delete(image);

        storageService.remove(imagemEncontrada.get().getUrl());

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageCaronaResponse(
                image,
                finalUrl
        );
    }

    public String concatenarUrl(String url) {
        String urlFinal = "http://localhost:4566/tcc-imagens/" + url;
        return urlFinal;
    }

    private String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
        return amazonS3.generatePresignedUrl("tcc-imagens", fileName, calendar.getTime(), httpMethod).toString();
    }
}
