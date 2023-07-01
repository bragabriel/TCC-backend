package br.com.spotted.backend.domain.entity;

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

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false) //Fk Usuario na tabela Artefato
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name="id_artefato", referencedColumnName = "id_artefato", updatable = false, insertable = false) //Fk Alimento na tabela Artefato
    @PrimaryKeyJoinColumn
    private Alimento alimento;

    @OneToOne
    @JoinColumn(name="id_artefato", referencedColumnName = "id_artefato", updatable = false, insertable = false) //Fk Alimento na tabela Artefato
    @PrimaryKeyJoinColumn
    private Transporte transporte;
}
