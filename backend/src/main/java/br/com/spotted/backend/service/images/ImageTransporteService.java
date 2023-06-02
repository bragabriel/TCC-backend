package br.com.spotted.backend.service.images;

import br.com.spotted.backend.domain.dto.Image.ImageTransporteResponse;
import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.entity.image.TransporteImage;
import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
import br.com.spotted.backend.repository.images.ImageTransporteRepository;
import br.com.spotted.backend.service.TransporteService;
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
public class ImageTransporteService {

    @Autowired
    private ImageTransporteRepository imageTransporteRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private TransporteService transporteService;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseBase<List<ImageTransporteResponse>> createTransporteImage(MultipartFile[] file, Long idItem) throws IOException {

        var transporte = transporteService.pesquisarPorId(idItem);

        var retorno = storageService.uploadFile(file, idItem);

        List<ImageTransporteResponse> transporteImageTransporteResponseList = new ArrayList<>();

        for (int i = 0; i < retorno.size(); i++) {
            TransporteImage transporteImage = new TransporteImage();

            String finalUrl = concatenarUrl(transporteImage.getUrl());

            transporteImage.setIdTransporte(idItem);
            transporteImage.setSequence(i);
            transporteImage.setFileName(retorno.get(i));
            transporteImage.setUrl(finalUrl);

            transporteImageTransporteResponseList.add(new ImageTransporteResponse(transporteImage, retorno.get(i)));

            var transporteImageCreated = imageTransporteRepository.save(transporteImage);
        }

        return new ResponseBase<>(transporteImageTransporteResponseList);
    }

    public ResponseBase<List<ImageTransporteResponse>> findTransporteImageByIdTransporte(Long idItem) {

        var transporte = transporteService.pesquisarPorId(idItem);

        if(transporte.getObjetoRetorno().getListaImagensTransporte().isEmpty()){
            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
        }

        List<ImageTransporteResponse> transporteImageList = new ArrayList<>();

        for (int i = 0; i < transporte.getObjetoRetorno().getListaImagensTransporte().size(); i++) {

            var key = transporte.getObjetoRetorno().getListaImagensTransporte().get(i).getUrl();

            String signedUrl = generateUrl(key, HttpMethod.GET);

            ImageTransporteResponse userImageTransporteResponse = new ImageTransporteResponse();

            userImageTransporteResponse.setIdTransporte(idItem);
            userImageTransporteResponse.setLocalUrl(transporte.getObjetoRetorno().getListaImagensTransporte().get(i).getUrl());
            userImageTransporteResponse.setSequence(transporte.getObjetoRetorno().getListaImagensTransporte().get(i).getSequence());
            userImageTransporteResponse.setIdImage(transporte.getObjetoRetorno().getListaImagensTransporte().get(i).getIdImage());
            userImageTransporteResponse.setFinalUrl(signedUrl);

            transporteImageList.add(userImageTransporteResponse);
        }

        return new ResponseBase<>(transporteImageList);
    }

    public ResponseBase<ImageTransporteResponse> updateTransporteImageSequence(Long transporteImageId, ImageUpdateRequest imageUpdateRequest) {

        var imagemEncontrada = imageTransporteRepository.findById(transporteImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        image.setSequence(imageUpdateRequest.getSequence());

        var imagemSalva = imageTransporteRepository.save(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ResponseBase<>(new ImageTransporteResponse(
                image,
                finalUrl
        ));
    }

    public ImageTransporteResponse deleteTransporteImage(Long transporteImageId) {
        var imagemEncontrada = imageTransporteRepository.findById(transporteImageId);

        if (imagemEncontrada.isEmpty()) {
            throw new ImageNotFoundException("Imagem não encontrada");
        }

        var image = imagemEncontrada.get();

        storageService.remove(imagemEncontrada.get().getFileName());

        imageTransporteRepository.delete(image);

        String finalUrl = concatenarUrl(image.getUrl());

        return new ImageTransporteResponse(
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
