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
public class ArtefatoUpdateRequest {
    @NotEmpty(message = "Campo titulo é obrigatório!")
    private String tituloArtefato;

    @NotEmpty(message = "Campo descrição é obrigatório!")
    private String descricaoArtefato;

    @NotNull(message = "Campo ativo é obrigatório!")
    private Boolean ativo;

    @NotEmpty(message = "Campo dataAtualizacao é obrigatório!")
    private Date dataAtualizacao;

    public ArtefatoUpdateRequest(Artefato artefato) {
        this.tituloArtefato = artefato.getTituloArtefato();
        this.descricaoArtefato = artefato.getDescricaoArtefato();
        this.ativo = artefato.getAtivo();
        this.dataAtualizacao = artefato.getDataAtualizacao();
    }
}
