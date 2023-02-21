package br.com.spotted.backend.domain.dto.Objeto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ObjetoUpdateRequest {

    @NotEmpty
    private String tituloObjeto;

    @NotEmpty
    private String descricaoObjeto;

    @NotEmpty
    private String localizacaoAchadoObjeto;

    @NotEmpty
    private String localizacaoAtualObjeto;

    private byte[] imagemObjeto;

    @NotNull
    private Long idUsuario;
}
