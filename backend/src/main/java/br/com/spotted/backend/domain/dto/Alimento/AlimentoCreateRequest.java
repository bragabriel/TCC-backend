package br.com.spotted.backend.domain.dto.Alimento;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AlimentoCreateRequest {

    @NotEmpty
    private String tipoAlimento;

    @NotEmpty
    private String marcaAlimento;

    @NotEmpty
    private String saborAlimento;

    @NotEmpty
    private String unidadeAlimento;

    @NotNull
    private Double precoAlimento;

    @NotEmpty
    private String ofertaAlimento;

    @NotNull
    private ArtefatoCreateRequest artefato;
}
