package br.com.spotted.backend.domain.dto.Objeto;

import br.com.spotted.backend.domain.entity.Objeto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ObjetoCreateRequest {

    @NotEmpty
    private String tituloObjeto;

    @NotEmpty
    private String descricaoObjeto;

    @NotEmpty
    private String localizacaoAchadoObjeto;

    @NotEmpty
    private String localizacaoAtualObjeto;

    @NotNull
    private Long idUsuario;
}
