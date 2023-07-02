package br.com.spotted.backend.domain.dto.Artefato;

import br.com.spotted.backend.domain.dto.Alimento.AlimentoResponse;
import br.com.spotted.backend.domain.dto.Transporte.TransporteResponse;
import br.com.spotted.backend.domain.entity.Artefato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtefatoResponse {

    private Long idArtefato;
    private String tituloArtefato;
    private String descricaoArtefato;
    private String tipoArtefato;
    private Boolean ativo;
    private Date dataCadastro;
    private Date dataAtualizacao;
    private Long idUsuario;
    private AlimentoResponse alimento;
    private TransporteResponse transporte;

    public ArtefatoResponse(Artefato artefato) {
        this.idArtefato = artefato.getIdArtefato();
        this.tituloArtefato = artefato.getTituloArtefato();
        this.descricaoArtefato = artefato.getDescricaoArtefato();
        this.tipoArtefato = artefato.getTipoArtefato();
        this.ativo = artefato.getAtivo();
        this.dataCadastro = artefato.getDataCadastro();
        this.dataAtualizacao = artefato.getDataAtualizacao();
        this.idUsuario = artefato.getIdUsuario();

        if (artefato.getAlimento() != null) {
            this.alimento = new AlimentoResponse(artefato.getAlimento());
        }
        if (artefato.getTransporte() != null) {
            this.transporte = new TransporteResponse(artefato.getTransporte());
        }
    }
}