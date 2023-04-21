package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageEstagioResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.EstagioImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.images.ImageEstagioRepository;
import br.com.spotted.backend.service.EstagioService;
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
public class ImageEstagioService {

    @Autowired
    private ImageEstagioRepository imageEstagioRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private EstagioService estagioService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageEstagioResponse>> createEstagioImage(MultipartFile[] file, Long idItem) throws IOException {

        var estagio = estagioService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageEstagioResponse> estagioImageEstagioResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            EstagioImage estagioImage = new EstagioImage();

            String finalUrl = concatenarUrl(estagioImage.getUrl());

            estagioImage.setIdEstagio(idItem);
            estagioImage.setSequence(i);
            estagioImage.setFileName(retorno.get(i));
            estagioImage.setUrl(finalUrl);

            estagioImageEstagioResponseList.add(new ImageEstagioResponse(estagioImage, retorno.get(i)));

            var estagioImageCreated = imageEstagioRepository.save(estagioImage);
        }

        return new ResponseBase<>(estagioImageEstagioResponseList);
    }

    public ResponseBase<List<ImageEstagioResponse>> findEstagioImageByIdEstagio(Long idItem) {

        var estagio = estagioService.pesquisarPorId(idItem);

        if(estagio.getObjetoRetorno().getListaImagensEstagio().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageEstagioResponse> estagioImageList = new ArrayList<>();

        for (int i = 0; i < estagio.getObjetoRetorno().getListaImagensEstagio().size(); i++) {

            var key = estagio.getObjetoRetorno().getListaImagensEstagio().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageEstagioResponse userImageEstagioResponse = new ImageEstagioResponse();

            userImageEstagioResponse.setIdEstagio(idItem);
            userImageEstagioResponse.setLocalUrl(estagio.getObjetoRetorno().getListaImagensEstagio().get(i).getUrl());
            userImageEstagioResponse.setSequence(estagio.getObjetoRetorno().getListaImagensEstagio().get(i).getSequence());
            userImageEstagioResponse.setIdImage(estagio.getObjetoRetorno().getListaImagensEstagio().get(i).getIdImage());
            userImageEstagioResponse.setFinalUrl(signedUrl);

            estagioImageList.add(userImageEstagioResponse);
        }

        return new ResponseBase<>(estagioImageList);
    }

    public ResponseBase<ImageEstagioResponse> updateEstagioImageSequence(Long estagioImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageEstagioRepository.findById(estagioImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageEstagioRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageEstagioResponse(
                image,
                finalUrl
        ));
    }

    public ImageEstagioResponse deleteEstagioImage(Long productImageId) {
        var imagemEncontrada = imageEstagioRepository.findById(productImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        imageEstagioRepository.delete(image);

        storageService.remove(imagemEncontrada.get().getUrl());

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageEstagioResponse(
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
