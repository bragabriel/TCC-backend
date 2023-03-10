package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Image.ImageResponse;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Image;
import br.com.spotted.backend.repository.ImageRepository;
import com.amazonaws.HttpMethod;
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
public class ImageService {

    //@Autowired
    //private ProductRepository productRepository;

    @Autowired
    private ImageRepository productImageRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageResponse>> createUsuarioImage(MultipartFile[] file, Long idItem) throws IOException {

        var usuario = usuarioService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageResponse> productImageResponseList = new ArrayList<>();

        for(int i = 0; i<retorno.size(); i++){
            Image image = new Image();
            image.setIdItem(idItem);
            image.setSequence(i);
            image.setUrl(retorno.get(i));

            String finalUrl = concatenarUrl(image.getUrl());

            productImageResponseList.add(new ImageResponse(image, finalUrl));

            var productImageCreated = productImageRepository.save(image);
        }

        return new ResponseBase<>(productImageResponseList);
    }

    public ResponseBase<List<ImageResponse>> findProductImageByIdUser(Long idItem){

        var result = usuarioService.pesquisarPorId(idItem);

        List<ImageResponse> productImageList = new ArrayList<>();

        for(int i=0; i<result.getObjetoRetorno().getListaImagens().size(); i++){

            var key = result.getObjetoRetorno().getListaImagens().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageResponse userImageResponse = new ImageResponse();

            userImageResponse.setIdItem(idItem);
            userImageResponse.setUrl(result.getObjetoRetorno().getListaImagens().get(i).getUrl());
            userImageResponse.setSequence(result.getObjetoRetorno().getListaImagens().get(i).getSequence());
            userImageResponse.setIdImage(result.getObjetoRetorno().getListaImagens().get(i).getIdImage());
            userImageResponse.setFinalUrl(signedUrl);

            productImageList.add(userImageResponse);
        }

        return new ResponseBase<>(productImageList);
    }

    public ResponseBase<ImageResponse> updateImageSequence(Long productImageId, ProductImageUpdateRequest productImageUpdateRequest){

        var productImageEncontrado = productImageRepository.findById(productImageId);

        if(productImageEncontrado.isEmpty()){
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = productImageEncontrado.get();

        image.setSequence(productImageUpdateRequest.getSequence());

        var imagemSalva = productImageRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageResponse(
                image,
                finalUrl
        ));
    }

    public ImageResponse deleteImage(Long productImageId){
        var imagemEncontrada = productImageRepository.findById(productImageId);

        if(imagemEncontrada.isEmpty()){
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        productImageRepository.delete(image);

        storageService.remove(imagemEncontrada.get().getUrl());

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageResponse(
                image,
                finalUrl
        );
    }

    public String concatenarUrl(String url){
        String urlFinal = "http://localhost:4566/minishop-imagens/" + url;
        return urlFinal;
    }

    private String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
        return amazonS3.generatePresignedUrl("minishop-imagens", fileName, calendar.getTime(), httpMethod).toString();
    }