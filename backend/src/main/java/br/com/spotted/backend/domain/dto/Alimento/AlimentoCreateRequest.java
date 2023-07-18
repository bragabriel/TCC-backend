package br.com.spotted.backend.domain.dto.Alimento;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AlimentoCreateRequest {

    private TipoAlimento tipoAlimento;

    private String marcaAlimento;

    private String saborAlimento;

    private String unidadeAlimento;

    private Double precoAlimento;

    private String ofertaAlimento;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
