package br.com.spotted.backend.service;

import br.com.spotted.backend.domain.dto.ResponseBase;
import br.com.spotted.backend.domain.dto.Usuario.UsuarioResponse;
import br.com.spotted.backend.exception.UsuarioNotFoundException;
import br.com.spotted.backend.repository.ImageRepository;
import br.com.spotted.backend.repository.UsuarioRepository;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Service
public class ImagemUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AmazonS3 amazonS3;

    private String prefixoUrl = "https://3e2b-45-172-240-25.ngrok-free.app/";

    public ResponseBase<UsuarioResponse> createUsuarioImage(MultipartFile[] file, Long idItem) throws IOException {

        var usuarioEncontrado = usuarioRepository.findById(idItem);

        if(usuarioEncontrado.isEmpty()){
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }

        var usuario = usuarioEncontrado.get();

        var retorno = storageService.uploadFile(file, idItem);

        String finalUrl = concatenarUrl(retorno.get(0));

        usuario.setFileName(retorno.get(0));
        usuario.setUrl(finalUrl);

        var usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResponse usuarioResponse = new UsuarioResponse(usuarioSalvo);

        return new ResponseBase<>(usuarioResponse);
    }

    public ResponseBase<UsuarioResponse> deleteUsuarioImage(Long usuarioId) {

        var usuarioEncontrado = usuarioRepository.findById(usuarioId);

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioNotFoundException("Usuário não encontrada");
        }

        var usuario = usuarioEncontrado.get();

        storageService.remove(usuarioEncontrado.get().getFileName());

        usuario.setFileName(null);
        usuario.setUrl(null);

        var usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResponse usuarioResponse = new UsuarioResponse(usuarioSalvo);

        return new ResponseBase<>(usuarioResponse);
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
