package br.com.spotted.backend.domain.dto.Emprego;

import lombok.Data;

@Data
public class EmpregoUpdateRequest {
    private String tituloArtefato;

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
