package br.com.spotted.backend.domain.entity;


import br.com.spotted.backend.domain.entity.image.FestaImage;
import br.com.spotted.backend.domain.entity.image.ObjetoImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "idObjeto", targetEntity = ObjetoImage.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<ObjetoImage> listaImagensObjeto = new ArrayList<ObjetoImage>();;
}
