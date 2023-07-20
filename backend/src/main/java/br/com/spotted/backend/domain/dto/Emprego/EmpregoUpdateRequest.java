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

    private String localizacaoEmprego;

    private String requisitosEmprego;

    private Integer salarioEmprego;

    private String beneficiosEmprego;

    private String contatoEmprego;

    private String linkVagaEmprego;

    private String empresaEmprego;

    private String cidadeEmprego;

    private String estadoEmprego;

    private String experienciaEmprego;

    private String tipoVagaEmprego;

    private String presencialEmprego;
}
