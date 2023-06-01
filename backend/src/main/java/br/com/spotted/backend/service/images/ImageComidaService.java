package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageComidaResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.ComidaImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.images.ImageComidaRepository;
import br.com.spotted.backend.service.ComidaService;
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
public class ImageComidaService {

    @Autowired
    private ImageComidaRepository imageComidaRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ComidaService comidaService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageComidaResponse>> createComidaImage(MultipartFile[] file, Long idItem) throws IOException {

        var comida = comidaService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageComidaResponse> comidaImageComidaResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            ComidaImage comidaImage = new ComidaImage();

            String finalUrl = concatenarUrl(comidaImage.getUrl());

            comidaImage.setIdComida(idItem);
            comidaImage.setSequence(i);
            comidaImage.setFileName(retorno.get(i));
            comidaImage.setUrl(finalUrl);

            comidaImageComidaResponseList.add(new ImageComidaResponse(comidaImage, retorno.get(i)));

            var comidaImageCreated = imageComidaRepository.save(comidaImage);
        }

        return new ResponseBase<>(comidaImageComidaResponseList);
    }

    public ResponseBase<List<ImageComidaResponse>> findComidaImageByIdComida(Long idItem) {

        var comida = comidaService.pesquisarPorId(idItem);

        if(comida.getObjetoRetorno().getListaImagensComida().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageComidaResponse> comidaImageList = new ArrayList<>();

        for (int i = 0; i < comida.getObjetoRetorno().getListaImagensComida().size(); i++) {

            var key = comida.getObjetoRetorno().getListaImagensComida().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageComidaResponse userImageComidaResponse = new ImageComidaResponse();

            userImageComidaResponse.setIdComida(idItem);
            userImageComidaResponse.setLocalUrl(comida.getObjetoRetorno().getListaImagensComida().get(i).getUrl());
            userImageComidaResponse.setSequence(comida.getObjetoRetorno().getListaImagensComida().get(i).getSequence());
            userImageComidaResponse.setIdImage(comida.getObjetoRetorno().getListaImagensComida().get(i).getIdImage());
            userImageComidaResponse.setFinalUrl(signedUrl);

            comidaImageList.add(userImageComidaResponse);
        }

        return new ResponseBase<>(comidaImageList);
    }

    public ResponseBase<ImageComidaResponse> updateComidaImageSequence(Long comidaImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageComidaRepository.findById(comidaImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageComidaRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageComidaResponse(
                image,
                finalUrl
        ));
    }

    public ImageComidaResponse deleteComidaImage(Long productImageId) {
        var imagemEncontrada = imageComidaRepository.findById(productImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        storageService.remove(imagemEncontrada.get().getFileName());

        imageComidaRepository.delete(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageComidaResponse(
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