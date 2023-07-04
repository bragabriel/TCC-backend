package br.com.spotted.backend.domain.dto.Festa;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FestaCreateRequest {

    @NotEmpty
    private String localizacaoFesta;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
