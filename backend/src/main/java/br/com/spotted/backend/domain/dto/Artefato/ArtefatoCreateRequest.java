package br.com.spotted.backend.domain.dto.Artefato;

import br.com.spotted.backend.domain.entity.TipoArtefato;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ArtefatoCreateRequest {

    @NotEmpty
    private String tituloArtefato;

    @NotEmpty
    private String descricaoArtefato;

    @NotNull
    private TipoArtefato tipoArtefato;

    @NotNull
    private Long idUsuario;
}
