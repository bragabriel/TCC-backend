package br.com.spotted.backend.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Crush")
@AllArgsConstructor
@NoArgsConstructor
public class Crush {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_crush")
    private Long idCrush;

    @Column(name = "titulo_crush", nullable = false)
    private String tituloCrush;

    @Column(name = "descricao_crush", nullable = false)
    private String descricaoCrush;

    @Column(name = "localizacao_crush")
    private String localizacaoCrush;

    @Column(name = "curso_crush")
    private String cursoCrush;

    @Column(name = "periodo_crush")
    private String periodoCrush;

    @Column(name = "imagem_crush")
    private byte[] imagemCrush;

    @Column(name= "idUsuario", insertable = false, updatable = false)
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="idUsuario", referencedColumnName = "id_usuario", nullable = false) //Fk IdUsuario na tabela Crush
    private Usuario usuario;

}
