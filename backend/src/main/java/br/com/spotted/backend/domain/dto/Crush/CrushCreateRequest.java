package br.com.spotted.backend.domain.dto.Crush;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class CrushCreateRequest {
    @NotEmpty
    private String tituloCrush;

    @NotEmpty
    private String descricaoCrush;

    @NotEmpty
    private String localizacaoCrush;

    @NotEmpty
    private String cursoCrush;

    @NotEmpty
    private String periodoCrush;

    private byte[] imagemCrush;

    @NotNull
    private Long idUsuario;
}
