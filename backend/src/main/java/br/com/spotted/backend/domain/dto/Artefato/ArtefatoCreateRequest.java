package br.com.spotted.backend.domain.dto.Artefato;

import br.com.spotted.backend.domain.entity.TipoArtefato;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ArtefatoCreateRequest {

    @NotEmpty
    private String tituloArtefato;

    @NotEmpty
    private String descricaoArtefato;

    @NotNull(message = "Não existe um artefato deste tipo!")
    private TipoArtefato tipoArtefato;

    @NotNull
    private Boolean ativo;

    @NotNull
    private Date dataCadastro;

    @NotNull
    private Long idUsuario;
}
