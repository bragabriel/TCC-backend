package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.Image.ImageResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.Imagem;
import br.com.spotted.backend.exception.ImagemNotFoundException;
import br.com.spotted.backend.repository.ImageRepository;
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
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ArtefatoService artefatoService;

    @Autowired
    private AmazonS3 amazonS3;

    private String prefixoUrl = "https://7daf-45-172-240-199.ngrok-free.app/";

    public ResponseBase<List<ImageResponse>> createImage(MultipartFile[] file, Long idItem) throws IOException {

        artefatoService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageResponse> imageResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            Imagem imagem = new Imagem();

            String finalUrl = concatenarUrl(retorno.get(i));

            imagem.setIdArtefato(idItem);
            imagem.setSequence(i);
            imagem.setFileName(retorno.get(i));
            imagem.setUrl(finalUrl);

            imageResponseList.add(new ImageResponse(imagem, retorno.get(i)));

            imageRepository.save(imagem);
        }

        return new ResponseBase<>(imageResponseList);
    }

    public ResponseBase<List<ImageResponse>> findImageByIdArtefato(Long idArtefato) {

        var artefato = artefatoService.pesquisarPorId(idArtefato);

        if(artefato.getObjetoRetorno().getListaImagens().isEmpty()){
            throw new ImagemNotFoundException("Este item não tem imagem cadastrada.");
        }

        List<ImageResponse> comidaImageList = new ArrayList<>();

        for (int i = 0; i < artefato.getObjetoRetorno().getListaImagens().size(); i++) {

            var key = artefato.getObjetoRetorno().getListaImagens().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageResponse userImageAlimentoResponse = new ImageResponse();

            userImageAlimentoResponse.setIdArtefato(idArtefato);
            userImageAlimentoResponse.setLocalUrl(artefato.getObjetoRetorno().getListaImagens().get(i).getUrl());
            userImageAlimentoResponse.setSequence(artefato.getObjetoRetorno().getListaImagens().get(i).getSequence());
            userImageAlimentoResponse.setIdImage(artefato.getObjetoRetorno().getListaImagens().get(i).getIdImage());
            userImageAlimentoResponse.setFinalUrl(signedUrl);

            comidaImageList.add(userImageAlimentoResponse);
        }

        return new ResponseBase<>(comidaImageList);
    }

    public ResponseBase<ImageResponse> updateImageSequence(Long comidaImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageRepository.findById(comidaImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        imageRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageResponse(
                image,
                finalUrl
        ));
    }

    public ImageResponse deleteImage(Long productImageId) {
        var imagemEncontrada = imageRepository.findById(productImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        storageService.remove(imagemEncontrada.get().getFileName());

        imageRepository.delete(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageResponse(
                image,
                finalUrl
        );
    }

    public String concatenarUrl(String url) {
        String urlFinal = this.prefixoUrl + "tcc-imagens/" + url;
        return urlFinal;
    }

    private String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
        return amazonS3.generatePresignedUrl("tcc-imagens", fileName, calendar.getTime(), httpMethod).toString();
    }
}