package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.ComidaImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.ImageRepository;
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
    private ImageRepository imageRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ComidaService comidaService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageResponse>> createComidaImage(MultipartFile[] file, Long idItem) throws IOException {

        var comida = comidaService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageResponse> comidaImageResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            ComidaImage comidaImage = new ComidaImage();

            String finalUrl = concatenarUrl(comidaImage.getUrl());

            comidaImage.setIdComida(idItem);
            comidaImage.setSequence(i);
            comidaImage.setFileName(retorno.get(i));
            comidaImage.setUrl(finalUrl);

            comidaImageResponseList.add(new ImageResponse(comidaImage, retorno.get(i)));

            var comidaImageCreated = imageRepository.save(comidaImage);
        }

        return new ResponseBase<>(comidaImageResponseList);
    }

    public ResponseBase<List<ImageResponse>> findComidaImageByIdComida(Long idItem) {

        var comida = comidaService.pesquisarPorId(idItem);

        if(comida.getObjetoRetorno().getListaImagensComida().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageResponse> comidaImageList = new ArrayList<>();

        for (int i = 0; i < comida.getObjetoRetorno().getListaImagensComida().size(); i++) {

            var key = comida.getObjetoRetorno().getListaImagensComida().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageResponse userImageResponse = new ImageResponse();

            userImageResponse.setIdComida(idItem);
            userImageResponse.setLocalUrl(comida.getObjetoRetorno().getListaImagensComida().get(i).getUrl());
            userImageResponse.setSequence(comida.getObjetoRetorno().getListaImagensComida().get(i).getSequence());
            userImageResponse.setIdImage(comida.getObjetoRetorno().getListaImagensComida().get(i).getIdImage());
            userImageResponse.setFinalUrl(signedUrl);

            comidaImageList.add(userImageResponse);
        }

        return new ResponseBase<>(comidaImageList);
    }

    public ResponseBase<ImageResponse> updateComidaImageSequence(Long comidaImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageRepository.findById(comidaImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageResponse(
                image,
                finalUrl
        ));
    }

    public ImageResponse deleteComidaImage(Long productImageId) {
        var imagemEncontrada = imageRepository.findById(productImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        imageRepository.delete(image);

        storageService.remove(imagemEncontrada.get().getUrl());

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageResponse(
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