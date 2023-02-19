package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Estagio")
@AllArgsConstructor
@NoArgsConstructor
public class Estagio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estagio")
    private Long idEstagio;

    @Column(name = "titulo_estagio", nullable = false)
    private String tituloEstagio;

    @Column(name = "descricao_estagio", nullable = false)
    private String descricaoComida;

    @Column(name = "localizacao_estagio", nullable = false)
    private String localizacaoEstagio;

    @Column(name = "requisitos_estagio")
    private String requisitosEstagio;

    @Column(name = "salario_estagio")
    private Integer salarioEstagio;

    @Column(name = "beneficios_estagio")
    private String beneficiosEstagio;

    @Column(name = "imagem_estagio")
    private byte[] imagemEstagio;

    @Column(name= "idUsuario", insertable = false, updatable = false)
    private Long idUsuario;

    @ManyToOne //Muitos posts de ESTAGIO podem pertencer à apenas 1 USUARIO
    @JoinColumn(name="idUsuario", referencedColumnName = "id_usuario", nullable = false) //Fk IdUsuario na tabela Comida
    private Usuario usuario;

}
