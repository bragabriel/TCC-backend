package br.com.spotted.backend.domain.dto.Evento;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EventoCreateRequest {

    private String localizacaoEvento;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
