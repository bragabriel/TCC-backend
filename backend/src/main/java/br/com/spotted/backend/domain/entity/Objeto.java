package br.com.spotted.backend.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Objeto")
@AllArgsConstructor
@NoArgsConstructor
public class Objeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objeto")
    private Long idObjeto;

    @Column(name = "titulo_objeto", nullable = false)
    private String tituloObjeto;

    @Column(name = "descricao_objeto", nullable = false)
    private String descricaoObjeto;

    @Column(name = "localizacaoAchado_objeto")
    private String localizacaoAchadoObjeto;

    @Column(name = "localizacaoAtual_objeto")
    private String localizacaoAtualObjeto;

    @Column(name = "imagem_objeto")
    private byte[] imagemObjeto;

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false)
    private Usuario usuario;

}
