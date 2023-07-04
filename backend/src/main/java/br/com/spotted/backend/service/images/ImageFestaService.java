//package br.com.spotted.backend.service.images;
//
//import br.com.spotted.backend.domain.dto.Image.ImageFestaResponse;
//import br.com.spotted.backend.domain.dto.Image.ImageUpdateRequest;
//import br.com.spotted.backend.domain.dto.ResponseBase;
//import br.com.spotted.backend.domain.entity.image.FestaImage;
//import br.com.spotted.backend.exception.ImagemNaoEncontradaException;
//import br.com.spotted.backend.repository.images.ImageFestaRepository;
//import br.com.spotted.backend.service.FestaService;
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
//public class ImageFestaService {
//
//    @Autowired
//    private ImageFestaRepository imageFestaRepository;
//
//    @Autowired
//    private StorageService storageService;
//
//    @Autowired
//    private FestaService festaService;
//
//    @Autowired
//    private AmazonS3 amazonS3;
//
//    public ResponseBase<List<ImageFestaResponse>> createFestaImage(MultipartFile[] file, Long idItem) throws IOException {
//
//        var festa = festaService.pesquisarPorId(idItem);
//
//        var retorno = storageService.uploadFile(file, idItem);
//
//        List<ImageFestaResponse> imageFestaResponseList = new ArrayList<>();
//
//        for (int i = 0; i < retorno.size(); i++) {
//            FestaImage festaImage = new FestaImage();
//
//            String finalUrl = concatenarUrl(festaImage.getUrl());
//
//            festaImage.setIdFesta(idItem);
//            festaImage.setSequence(i);
//            festaImage.setFileName(retorno.get(i));
//            festaImage.setUrl(finalUrl);
//
//            imageFestaResponseList.add(new ImageFestaResponse(festaImage, retorno.get(i)));
//
//            var festaImageCreated = imageFestaRepository.save(festaImage);
//        }
//
//        return new ResponseBase<>(imageFestaResponseList);
//    }
//
//    public ResponseBase<List<ImageFestaResponse>> findFestaImageByIdFesta(Long idItem) {
//
//        var festa = festaService.pesquisarPorId(idItem);
//
//        if(festa.getObjetoRetorno().getListaImagensFesta().isEmpty()){
//            throw new ImagemNaoEncontradaException("Este item não tem imagem cadastrada.");
//        }
//
//        List<ImageFestaResponse> festaImageList = new ArrayList<>();
//
//        for (int i = 0; i < festa.getObjetoRetorno().getListaImagensFesta().size(); i++) {
//
//            var key = festa.getObjetoRetorno().getListaImagensFesta().get(i).getUrl();
//
//            String signedUrl = generateUrl(key, HttpMethod.GET);
//
//            ImageFestaResponse imageFestaResponse = new ImageFestaResponse();
//
//            imageFestaResponse.setIdFesta(idItem);
//            imageFestaResponse.setLocalUrl(festa.getObjetoRetorno().getListaImagensFesta().get(i).getUrl());
//            imageFestaResponse.setSequence(festa.getObjetoRetorno().getListaImagensFesta().get(i).getSequence());
//            imageFestaResponse.setIdImage(festa.getObjetoRetorno().getListaImagensFesta().get(i).getIdImage());
//            imageFestaResponse.setFinalUrl(signedUrl);
//
//            festaImageList.add(imageFestaResponse);
//        }
//
//        return new ResponseBase<>(festaImageList);
//    }
//
//    public ResponseBase<ImageFestaResponse> updateFestaImageSequence(Long festaImageId, ImageUpdateRequest imageUpdateRequest) {
//
//        var imagemEncontrada = imageFestaRepository.findById(festaImageId);
//
//        if (imagemEncontrada.isEmpty()) {
//            throw new ImageNotFoundException("Imagem não encontrada");
//        }
//
//        var image = imagemEncontrada.get();
//
//        image.setSequence(imageUpdateRequest.getSequence());
//
//        var imagemSalva = imageFestaRepository.save(image);
//
//        String finalUrl = concatenarUrl(image.getUrl());
//
//        return new ResponseBase<>(new ImageFestaResponse(
//                image,
//                finalUrl
//        ));
//    }
//
//    public ImageFestaResponse deleteFestaImage(Long productImageId) {
//        var imagemEncontrada = imageFestaRepository.findById(productImageId);
//
//        if (imagemEncontrada.isEmpty()) {
//            throw new ImageNotFoundException("Imagem não encontrada");
//        }
//
//        var image = imagemEncontrada.get();
//
//        storageService.remove(imagemEncontrada.get().getFileName());
//
//        imageFestaRepository.delete(image);
//
//        String finalUrl = concatenarUrl(image.getUrl());
//
//        return new ImageFestaResponse(
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