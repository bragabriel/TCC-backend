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

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false)
    private Usuario usuario;

}
