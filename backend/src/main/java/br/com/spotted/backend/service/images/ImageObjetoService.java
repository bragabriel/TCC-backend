package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageObjetoResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.ObjetoImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.images.ImageObjetoRepository;
import br.com.spotted.backend.service.ObjetoService;
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
public class ImageObjetoService {

    @Autowired
    private ImageObjetoRepository imageObjetoRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ObjetoService objetoService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageObjetoResponse>> createObjetoImage(MultipartFile[] file, Long idItem) throws IOException {

        var objeto = objetoService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageObjetoResponse> imageObjetoResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            ObjetoImage objetoImage = new ObjetoImage();

            String finalUrl = concatenarUrl(objetoImage.getUrl());

            objetoImage.setIdObjeto(idItem);
            objetoImage.setSequence(i);
            objetoImage.setFileName(retorno.get(i));
            objetoImage.setUrl(finalUrl);

            imageObjetoResponseList.add(new ImageObjetoResponse(objetoImage, retorno.get(i)));

            var objetoImageCreated = imageObjetoRepository.save(objetoImage);
        }

        return new ResponseBase<>(imageObjetoResponseList);
    }

    public ResponseBase<List<ImageObjetoResponse>> findObjetoImageByIdObjeto(Long idItem) {

        var objeto = objetoService.pesquisarPorId(idItem);

        if(objeto.getObjetoRetorno().getListaImagensObjeto().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageObjetoResponse> objetoImageList = new ArrayList<>();

        for (int i = 0; i < objeto.getObjetoRetorno().getListaImagensObjeto().size(); i++) {

            var key = objeto.getObjetoRetorno().getListaImagensObjeto().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageObjetoResponse imageObjetoResponse = new ImageObjetoResponse();

            imageObjetoResponse.setIdObjeto(idItem);
            imageObjetoResponse.setLocalUrl(objeto.getObjetoRetorno().getListaImagensObjeto().get(i).getUrl());
            imageObjetoResponse.setSequence(objeto.getObjetoRetorno().getListaImagensObjeto().get(i).getSequence());
            imageObjetoResponse.setIdImage(objeto.getObjetoRetorno().getListaImagensObjeto().get(i).getIdImage());
            imageObjetoResponse.setFinalUrl(signedUrl);

            objetoImageList.add(imageObjetoResponse);
        }
        return new ResponseBase<>(objetoImageList);
    }

    public ResponseBase<ImageObjetoResponse> updateObjetoImageSequence(Long objetoImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageObjetoRepository.findById(objetoImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageObjetoRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageObjetoResponse(
                image,
                finalUrl
        ));
    }

    public ImageObjetoResponse deleteObjetoImage(Long productImageId) {
        var imagemEncontrada = imageObjetoRepository.findById(productImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        storageService.remove(imagemEncontrada.get().getFileName());

        imageObjetoRepository.delete(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageObjetoResponse(
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