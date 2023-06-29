package br.com.spotted.backend.domain.dto;

import br.com.spotted.backend.domain.entity.Artefato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtefatoResponse {

    private Long idArtefato;
    private String tituloArtefato;
    private String descricaoArtefato;
    private String tipoArtefato;
    private String ativo;
    private String dataCadastro;
    private Long idUsuario;

    public ArtefatoResponse(Artefato artefato) {
        this.idArtefato = artefato.getIdArtefato();
        this.tituloArtefato = artefato.getTituloArtefato();
        this.descricaoArtefato = artefato.getDescricaoArtefato();
        this.tipoArtefato = artefato.getTipoArtefato();
        this.ativo = artefato.getAtivo();
        this.dataCadastro = artefato.getDataCadastro();
        this.idUsuario = artefato.getIdUsuario();
    }
}