package br.com.spotted.backend.domain.dto.Evento;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoIndividualResponse;
import br.com.spotted.backend.domain.entity.Evento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventoArtefatoResponse extends ArtefatoIndividualResponse {

    private Long idArtefato;

    private String localizacaoFesta;

    public EventoArtefatoResponse(Evento evento) {
        super();
        this.idArtefato = evento.getIdArtefato();
        this.localizacaoFesta = evento.getLocalizacaoEvento();
        this.setTituloArtefato(evento.getArtefato().getTituloArtefato());
        this.setDescricaoArtefato(evento.getArtefato().getDescricaoArtefato());
        this.setAtivo(evento.getArtefato().getAtivo());
        this.setTipoArtefato(evento.getArtefato().getTipoArtefato());
        this.setDataCadastro(evento.getArtefato().getDataCadastro().toString());

        if (evento.getArtefato() != null && evento.getArtefato().getDataAtualizacao() != null) {
            this.setDataAtualizacao(evento.getArtefato().getDataAtualizacao().toString());
        } else {
            this.setDataAtualizacao("");
        }

        this.setIdUsuario(evento.getArtefato().getIdUsuario());
        this.setTelefoneUsuario(evento.getArtefato().getUsuario().getTelefoneUsuario());
        this.setListaImagens(evento.getArtefato().getListaImagens());
    }
}
