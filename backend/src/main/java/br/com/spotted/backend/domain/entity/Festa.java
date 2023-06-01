package br.com.spotted.backend.domain.entity;

import br.com.spotted.backend.domain.entity.image.EstagioImage;
import br.com.spotted.backend.domain.entity.image.FestaImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "idFesta", targetEntity = FestaImage.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<FestaImage> listaImagensFesta = new ArrayList<FestaImage>();;
}
