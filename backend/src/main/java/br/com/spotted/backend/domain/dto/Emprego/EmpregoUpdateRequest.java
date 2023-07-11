package br.com.spotted.backend.domain.dto.Emprego;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmpregoUpdateRequest {
    @NotEmpty(message = "Campo nome é obrigatório!")
    private String tituloArtefato;

    @NotEmpty(message = "Campo 'descrição' é obrigatório!")
    private String descricaoArtefato;

    @NotEmpty
    private String localizacaoEmprego;

    @NotEmpty
    private String requisitosEmprego;

    @NotNull
    private Integer salarioEmprego;

    @NotEmpty
    private String beneficiosEmprego;

    @NotEmpty
    private String contatoEmprego;

    @NotEmpty
    private String linkVagaEmprego;
}
