package br.com.spotted.backend.domain.dto.Artefato;

import br.com.spotted.backend.domain.dto.Alimento.AlimentoResponse;
import br.com.spotted.backend.domain.dto.Emprego.EmpregoResponse;
import br.com.spotted.backend.domain.dto.Evento.EventoResponse;
import br.com.spotted.backend.domain.dto.Moradia.MoradiaResponse;
import br.com.spotted.backend.domain.dto.Objeto.ObjetoResponse;
import br.com.spotted.backend.domain.dto.Transporte.TransporteResponse;
import br.com.spotted.backend.domain.entity.Artefato;
import br.com.spotted.backend.domain.entity.Imagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtefatoResponse {

    private Long idArtefato;
    private String tituloArtefato;
    private String descricaoArtefato;
    private Boolean ativo;
    private String tipoArtefato;
    private Date dataCadastro;
    private Date dataAtualizacao;
    private Long idUsuario;
    private AlimentoResponse alimento;
    private EmpregoResponse emprego;
    private EventoResponse festa;
    private MoradiaResponse moradia;
    private ObjetoResponse objeto;
    private TransporteResponse transporte;
    private List<Imagem> listaImagens;

    public ArtefatoResponse(Artefato artefato) {
        this.idArtefato = artefato.getIdArtefato();
        this.tituloArtefato = artefato.getTituloArtefato();
        this.descricaoArtefato = artefato.getDescricaoArtefato();
        this.ativo = artefato.getAtivo();
        this.tipoArtefato = artefato.getTipoArtefato().toString();
        this.dataCadastro = artefato.getDataCadastro();
        this.dataAtualizacao = artefato.getDataAtualizacao();
        this.idUsuario = artefato.getIdUsuario();
        this.listaImagens = artefato.getListaImagens();

        if (artefato.getAlimento() != null) {
            this.alimento = new AlimentoResponse(artefato.getAlimento());
        }
        if (artefato.getEmprego() != null) {
            this.emprego = new EmpregoResponse(artefato.getEmprego());
        }
        if (artefato.getEvento() != null) {
            this.festa = new EventoResponse(artefato.getEvento());
        }
        if (artefato.getMoradia() != null) {
            this.moradia = new MoradiaResponse(artefato.getMoradia());
        }
        if (artefato.getObjeto() != null) {
            this.objeto = new ObjetoResponse(artefato.getObjeto());
        }
        if (artefato.getTransporte() != null) {
            this.transporte = new TransporteResponse(artefato.getTransporte());
        }
    }
}