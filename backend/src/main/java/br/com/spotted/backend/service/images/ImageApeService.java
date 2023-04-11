package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageApeResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.ApeImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.images.ImageApeRepository;
import br.com.spotted.backend.service.ApeService;
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
public class ImageApeService {

    @Autowired
    private ImageApeRepository imageApeRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ApeService apeService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageApeResponse>> createApeImage(MultipartFile[] file, Long idItem) throws IOException {

        var ape = apeService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageApeResponse> apeImageApeResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            ApeImage apeImage = new ApeImage();

            String finalUrl = concatenarUrl(apeImage.getUrl());

            apeImage.setIdApe(idItem);
            apeImage.setSequence(i);
            apeImage.setFileName(retorno.get(i));
            apeImage.setUrl(finalUrl);

            apeImageApeResponseList.add(new ImageApeResponse(apeImage, retorno.get(i)));

            var apeImageCreated = imageApeRepository.save(apeImage);
        }

        return new ResponseBase<>(apeImageApeResponseList);
    }

    public ResponseBase<List<ImageApeResponse>> findApeImageByIdApe(Long idItem) {

        var ape = apeService.pesquisarPorId(idItem);

        if(ape.getObjetoRetorno().getListaImagensApe().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageApeResponse> apeImageList = new ArrayList<>();

        for (int i = 0; i < ape.getObjetoRetorno().getListaImagensApe().size(); i++) {

            var key = ape.getObjetoRetorno().getListaImagensApe().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageApeResponse userImageApeResponse = new ImageApeResponse();

            userImageApeResponse.setIdApe(idItem);
            userImageApeResponse.setLocalUrl(ape.getObjetoRetorno().getListaImagensApe().get(i).getUrl());
            userImageApeResponse.setSequence(ape.getObjetoRetorno().getListaImagensApe().get(i).getSequence());
            userImageApeResponse.setIdImage(ape.getObjetoRetorno().getListaImagensApe().get(i).getIdImage());
            userImageApeResponse.setFinalUrl(signedUrl);

            apeImageList.add(userImageApeResponse);
        }

        return new ResponseBase<>(apeImageList);
    }

    public ResponseBase<ImageApeResponse> updateApeImageSequence(Long apeImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageApeRepository.findById(apeImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageApeRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageApeResponse(
                image,
                finalUrl
        ));
    }

    public ImageApeResponse deleteApeImage(Long productImageId) {
        var imagemEncontrada = imageApeRepository.findById(productImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        imageApeRepository.delete(image);

        storageService.remove(imagemEncontrada.get().getUrl());

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageApeResponse(
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
