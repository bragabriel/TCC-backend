//package br.com.spotted.backend.service.images;
//
//import br.com.spotted.backend.domain.dto.Image.ImageAlimentoResponse;
//import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
//import br.com.spotted.backend.domain.dto.ResponseBase;
//import br.com.spotted.backend.domain.entity.image.AlimentoImage;
//import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
//import br.com.spotted.backend.repository.images.ImageAlimentoRepository;
//import br.com.spotted.backend.service.AlimentoService;
//import br.com.spotted.backend.service.StorageService;
//import com.amazonaws.HttpMethod;
//import com.amazonaws.services.ecr.model.ImageNotFoundException;
//import com.amazonaws.services.s3.AmazonS3;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class ImageAlimentoService {
//
//    @Autowired
//    private ImageAlimentoRepository imageAlimentoRepository;
//
//    @Autowired
//    private StorageService storageService;
//
//    @Autowired
//    private AlimentoService alimentoService;
//
//    @Autowired
//    private AmazonS3 amazonS3;
//
//    public ResponseBase<List<ImageAlimentoResponse>> createAlimentoImage(MultipartFile[] file, Long idItem) throws IOException {
//
//        var comida = alimentoService.pesquisarPorId(idItem);
//
//        var retorno = storageService.uploadFile(file, idItem);
//
//        List<ImageAlimentoResponse> comidaImageAlimentoResponseList = new ArrayList<>();
//
//        for (int i = 0; i < retorno.size(); i++) {
//            AlimentoImage alimentoImage = new AlimentoImage();
//
//            String finalUrl = concatenarUrl(alimentoImage.getUrl());
//
//            alimentoImage.setIdAlimento(idItem);
//            alimentoImage.setSequence(i);
//            alimentoImage.setFileName(retorno.get(i));
//            alimentoImage.setUrl(finalUrl);
//
//            comidaImageAlimentoResponseList.add(new ImageAlimentoResponse(alimentoImage, retorno.get(i)));
//
//            var comidaImageCreated = imageAlimentoRepository.save(alimentoImage);
//        }
//
//        return new ResponseBase<>(comidaImageAlimentoResponseList);
//    }
//
//    public ResponseBase<List<ImageAlimentoResponse>> findAlimentoImageByIdAlimento(Long idItem) {
//
//        var comida = alimentoService.pesquisarPorId(idItem);
//
//        if(comida.getObjetoRetorno().getListaImagensAlimento().isEmpty()){
//            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
//        }
//
//        List<ImageAlimentoResponse> comidaImageList = new ArrayList<>();
//
//        for (int i = 0; i < comida.getObjetoRetorno().getListaImagensAlimento().size(); i++) {
//
//            var key = comida.getObjetoRetorno().getListaImagensAlimento().get(i).getUrl();
//
//            String signedUrl = generateUrl(key, HttpMethod.GET);
//
//            ImageAlimentoResponse userImageAlimentoResponse = new ImageAlimentoResponse();
//
//            userImageAlimentoResponse.setIdAlimento(idItem);
//            userImageAlimentoResponse.setLocalUrl(comida.getObjetoRetorno().getListaImagensAlimento().get(i).getUrl());
//            userImageAlimentoResponse.setSequence(comida.getObjetoRetorno().getListaImagensAlimento().get(i).getSequence());
//            userImageAlimentoResponse.setIdImage(comida.getObjetoRetorno().getListaImagensAlimento().get(i).getIdImage());
//            userImageAlimentoResponse.setFinalUrl(signedUrl);
//
//            comidaImageList.add(userImageAlimentoResponse);
//        }
//
//        return new ResponseBase<>(comidaImageList);
//    }
//
//    public ResponseBase<ImageAlimentoResponse> updateAlimentoImageSequence(Long comidaImageId, ImageUpdateRequest imageUpdateRequest) {
//
//        var imagemEncontrada = imageAlimentoRepository.findById(comidaImageId);
//
//        if (imagemEncontrada.isEmpty()) {
//            throw new ImageNotFoundException("Imagem não encontrada");
//        }
//
//        var image = imagemEncontrada.get();
//
//        image.setSequence(imageUpdateRequest.getSequence());
//
//        var imagemSalva = imageAlimentoRepository.save(image);
//
//        String finalUrl = concatenarUrl(image.getUrl());
//
//        return new ResponseBase<>(new ImageAlimentoResponse(
//                image,
//                finalUrl
//        ));
//    }
//
//    public ImageAlimentoResponse deleteAlimentoImage(Long productImageId) {
//        var imagemEncontrada = imageAlimentoRepository.findById(productImageId);
//
//        if (imagemEncontrada.isEmpty()) {
//            throw new ImageNotFoundException("Imagem não encontrada");
//        }
//
//        var image = imagemEncontrada.get();
//
//        storageService.remove(imagemEncontrada.get().getFileName());
//
//        imageAlimentoRepository.delete(image);
//
//        String finalUrl = concatenarUrl(image.getUrl());
//
//        return new ImageAlimentoResponse(
//                image,
//                finalUrl
//        );
//    }
//
//    public String concatenarUrl(String url) {
//        String urlFinal = "http://localhost:4566/tcc-imagens/" + url;
//        return urlFinal;
//    }
//
//    private String generateUrl(String fileName, HttpMethod httpMethod) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
//        return amazonS3.generatePresignedUrl("tcc-imagens", fileName, calendar.getTime(), httpMethod).toString();
//    }
//}