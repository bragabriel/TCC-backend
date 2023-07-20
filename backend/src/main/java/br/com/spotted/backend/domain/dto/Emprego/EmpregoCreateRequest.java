package br.com.spotted.backend.domain.dto.Emprego;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoCreateRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmpregoCreateRequest {


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

    @NotNull
    private ArtefatoCreateRequest artefato;
}
