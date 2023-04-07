package br.com.spotted.backend.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class StorageService {

    @Autowired
    AmazonS3 amazonS3;

    public List<String> uploadFile(MultipartFile[] multipartFile, Long productId) throws IOException {

        LocalTime time = LocalTime.now();
        DateTimeFormatter data = DateTimeFormatter.ofPattern("HH:mm:ss");
        var dataFormatada = time.format(data);

        List<String> listaImagens= new ArrayList<>();

        for(int i=0; i<multipartFile.length; i++){
            //horário atual (timestamp) + o id do produto + o nome do arquivo = garantindo que não sobrescreveremos imagens com o mesmo nome,
            String fileName = dataFormatada + productId.toString() + multipartFile[i].getOriginalFilename();

            amazonS3.putObject(new PutObjectRequest("tcc-imagens", fileName, multipartFile[i].getInputStream(), new ObjectMetadata()));

            listaImagens.add(fileName);
        }

        return listaImagens;
    };

    public void remove(String key){
        amazonS3.deleteObject("tcc-imagens", key);
    }

    private String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
        return amazonS3.generatePresignedUrl("tcc-imagens", fileName, calendar.getTime(), httpMethod).toString();
    }
}
