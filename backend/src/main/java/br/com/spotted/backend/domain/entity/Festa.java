package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="Festa")
@AllArgsConstructor
@NoArgsConstructor
public class Festa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_festa")
    private Long idFesta;

    @Column(name = "titulo_festa", nullable = false)
    private String tituloFesta;

    @Column(name = "descricao_festa", nullable = false)
    private String descricaoFesta;

    @Column(name = "localizacao_festa")
    private String localizacaoFesta;

    @Column(name = "imagem_festa")
    private byte[] imagemFesta;

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false)
    private Usuario usuario;

}
