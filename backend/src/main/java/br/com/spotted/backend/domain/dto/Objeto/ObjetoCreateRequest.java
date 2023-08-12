package br.com.spotted.backend.domain.dto.Objeto;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ObjetoCreateRequest {

    @NotEmpty
    private String localizacaoAchadoObjeto;

    @NotEmpty
    private String localizacaoAtualObjeto;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
