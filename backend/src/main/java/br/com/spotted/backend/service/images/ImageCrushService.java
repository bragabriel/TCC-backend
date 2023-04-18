package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageCrushResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.CrushImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.images.ImageCrushRepository;
import br.com.spotted.backend.service.CrushService;
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
public class ImageCrushService {

    @Autowired
    private ImageCrushRepository imageCrushRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CrushService crushService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageCrushResponse>> createCrushImage(MultipartFile[] file, Long idItem) throws IOException {

        var crush = crushService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageCrushResponse> imageCrushResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {

            CrushImage crushImage = new CrushImage();

            String finalUrl = concatenarUrl(crushImage.getUrl());

            crushImage.setIdCrush(idItem);
            crushImage.setSequence(i);
            crushImage.setFileName(retorno.get(i));
            crushImage.setUrl(finalUrl);

            imageCrushResponseList.add(new ImageCrushResponse(crushImage, retorno.get(i)));

            var imageCreated = imageCrushRepository.save(crushImage);
        }

        return new ResponseBase<>(imageCrushResponseList);
    }

    public ResponseBase<List<ImageCrushResponse>> findCrushImageByIdCrush(Long idItem) {

        var crush = crushService.pesquisarPorId(idItem);

        if(crush.getObjetoRetorno().getListaImagensCrush().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageCrushResponse> crushImageList = new ArrayList<>();

        for (int i = 0; i < crush.getObjetoRetorno().getListaImagensCrush().size(); i++) {

            var key = crush.getObjetoRetorno().getListaImagensCrush().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageCrushResponse userImageCrushResponse = new ImageCrushResponse();

            userImageCrushResponse.setIdCrush(idItem);
            userImageCrushResponse.setLocalUrl(crush.getObjetoRetorno().getListaImagensCrush().get(i).getUrl());
            userImageCrushResponse.setSequence(crush.getObjetoRetorno().getListaImagensCrush().get(i).getSequence());
            userImageCrushResponse.setIdImage(crush.getObjetoRetorno().getListaImagensCrush().get(i).getIdImage());
            userImageCrushResponse.setFinalUrl(signedUrl);

            crushImageList.add(userImageCrushResponse);
        }

        return new ResponseBase<>(crushImageList);
    }

    public ResponseBase<ImageCrushResponse> updateCrushImageSequence(Long crushImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageCrushRepository.findById(crushImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageCrushRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageCrushResponse(
                image,
                finalUrl
        ));
    }

    public ImageCrushResponse deleteCrushImage(Long productImageId) {
        var imagemEncontrada = imageCrushRepository.findById(productImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        imageCrushRepository.delete(image);

        storageService.remove(imagemEncontrada.get().getUrl());

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageCrushResponse(
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

