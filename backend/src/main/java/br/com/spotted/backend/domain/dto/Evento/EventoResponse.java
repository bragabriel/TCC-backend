package br.com.spotted.backend.domain.dto.Evento;

import br.com.spotted.backend.domain.entity.Evento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventoResponse {

    private Long idArtefato;

    private String localizacaoEvento;

    public EventoResponse(Evento evento) {
        this.idArtefato = evento.getIdArtefato();
        this.localizacaoEvento = evento.getLocalizacaoEvento();
    }
}
