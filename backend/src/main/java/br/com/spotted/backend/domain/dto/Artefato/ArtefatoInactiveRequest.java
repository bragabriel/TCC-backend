package br.com.spotted.backend.domain.dto.Artefato;

import br.com.spotted.backend.domain.entity.Artefato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtefatoInactiveRequest {
    @NotEmpty(message = "Campo titulo é obrigatório!")
    private String tituloArtefato;

    @NotEmpty(message = "Campo descrição é obrigatório!")
    private String descricaoArtefato;

    @NotEmpty(message = "Campo tipo é obrigatório!")
    private String tipoArtefato;

    @NotNull(message = "Campo ativo é obrigatório!")
    private Boolean ativo;

    @NotEmpty(message = "Campo dataAtualizacao é obrigatório!")
    private Date dataInativo;

    public ArtefatoInactiveRequest(Artefato artefato) {
        this.tituloArtefato = artefato.getTituloArtefato();
        this.descricaoArtefato = artefato.getDescricaoArtefato();
        this.tipoArtefato = artefato.getTipoArtefato();
        this.ativo = artefato.getAtivo();
        this.dataInativo = artefato.getDataInativo();
    }
}

