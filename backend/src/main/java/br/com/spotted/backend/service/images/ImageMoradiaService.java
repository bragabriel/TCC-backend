package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageMoradiaResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.MoradiaImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.images.ImageMoradiaRepository;
import br.com.spotted.backend.service.MoradiaService;
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
public class ImageMoradiaService {

    @Autowired
    private ImageMoradiaRepository imageMoradiaRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private MoradiaService moradiaService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageMoradiaResponse>> createMoradiaImage(MultipartFile[] file, Long idItem) throws IOException {

        var moradia = moradiaService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageMoradiaResponse> moradiaImageMoradiaResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            MoradiaImage moradiaImage = new MoradiaImage();

            String finalUrl = concatenarUrl(moradiaImage.getUrl());

            moradiaImage.setIdMoradia(idItem);
            moradiaImage.setSequence(i);
            moradiaImage.setFileName(retorno.get(i));
            moradiaImage.setUrl(finalUrl);

            moradiaImageMoradiaResponseList.add(new ImageMoradiaResponse(moradiaImage, retorno.get(i)));

            var moradiaImageCreated = imageMoradiaRepository.save(moradiaImage);
        }

        return new ResponseBase<>(moradiaImageMoradiaResponseList);
    }

    public ResponseBase<List<ImageMoradiaResponse>> findMoradiaImageByIdMoradia(Long idItem) {

        var moradia = moradiaService.pesquisarPorId(idItem);

        if(moradia.getObjetoRetorno().getListaImagensMoradia().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageMoradiaResponse> moradiaImageList = new ArrayList<>();

        for (int i = 0; i < moradia.getObjetoRetorno().getListaImagensMoradia().size(); i++) {

            var key = moradia.getObjetoRetorno().getListaImagensMoradia().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageMoradiaResponse userImageMoradiaResponse = new ImageMoradiaResponse();

            userImageMoradiaResponse.setIdMoradia(idItem);
            userImageMoradiaResponse.setLocalUrl(moradia.getObjetoRetorno().getListaImagensMoradia().get(i).getUrl());
            userImageMoradiaResponse.setSequence(moradia.getObjetoRetorno().getListaImagensMoradia().get(i).getSequence());
            userImageMoradiaResponse.setIdImage(moradia.getObjetoRetorno().getListaImagensMoradia().get(i).getIdImage());
            userImageMoradiaResponse.setFinalUrl(signedUrl);

            moradiaImageList.add(userImageMoradiaResponse);
        }

        return new ResponseBase<>(moradiaImageList);
    }

    public ResponseBase<ImageMoradiaResponse> updateMoradiaImageSequence(Long moradiaImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageMoradiaRepository.findById(moradiaImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageMoradiaRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageMoradiaResponse(
                image,
                finalUrl
        ));
    }

    public ImageMoradiaResponse deleteMoradiaImage(Long productImageId) {
        var imagemEncontrada = imageMoradiaRepository.findById(productImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        storageService.remove(imagemEncontrada.get().getFileName());

        imageMoradiaRepository.delete(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageMoradiaResponse(
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
