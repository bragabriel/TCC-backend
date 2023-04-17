package br.com.spotted.backend.domain.entity;

import br.com.spotted.backend.domain.entity.image.ApeImage;
import br.com.spotted.backend.domain.entity.image.EstagioImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name= "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", updatable = false, insertable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "idEstagio", targetEntity = EstagioImage.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<EstagioImage> listaImagensEstagio = new ArrayList<EstagioImage>();;
}
