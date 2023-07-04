package br.com.spotted.backend.domain.entity;

import br.com.spotted.backend.domain.dto.Artefato.ArtefatoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="Artefato")
@AllArgsConstructor
@NoArgsConstructor
public class Artefato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artefato")
    private Long idArtefato;

    @Column(name = "titulo_artefato", nullable = false)
    private String tituloArtefato;

    @Column(name = "descricao_artefato", nullable = false)
    private String descricaoArtefato;

    @Column(name = "tipo_artefato", nullable = false)
    private String tipoArtefato;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;

    @Column(name = "data_inativo", nullable = false)
    private Date dataInativo;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false) //Fk Usuario na tabela Artefato
    private Usuario usuario;

    @OneToOne(mappedBy = "artefato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true) //Fk Alimento na tabela Artefato
    private Alimento alimento;

    @OneToOne(mappedBy = "artefato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true) //Fk Emprego na tabela Artefato
    private Emprego emprego;

    @OneToOne(mappedBy = "artefato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true) //Fk Festa na tabela Artefato
    private Festa festa;

    @OneToOne(mappedBy = "artefato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true) //Fk Moradia na tabela Artefato
    private Moradia moradia;

    @OneToOne(mappedBy = "artefato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true) //Fk Objeto na tabela Artefato
    private Objeto objeto;

    @OneToOne(mappedBy = "artefato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true) //Fk Transporte na tabela Artefato
    private Transporte transporte;

    //    @OneToMany(mappedBy = "idAlimento", targetEntity = AlimentoImage.class, orphanRemoval = true) //idAlimento relacionamento na outra table
    //    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    //    private List<AlimentoImage> listaImagensAlimento = new ArrayList<AlimentoImage>();

    public Artefato(ArtefatoResponse artefatoResponse) {
        this.idArtefato = artefatoResponse.getIdArtefato();
        this.tituloArtefato = artefatoResponse.getTituloArtefato();
        this.descricaoArtefato = artefatoResponse.getDescricaoArtefato();
        this.tipoArtefato = artefatoResponse.getTipoArtefato();
        this.ativo = artefatoResponse.getAtivo();
        this.dataCadastro = artefatoResponse.getDataCadastro();
        this.idUsuario = artefatoResponse.getIdUsuario();
    }
}
