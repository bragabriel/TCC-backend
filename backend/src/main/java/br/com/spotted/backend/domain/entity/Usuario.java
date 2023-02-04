package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "nomeUsuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "sobrenomeUsuario", nullable = false)
    private String sobrenomeUsuario;

    @Column(name = "emailUsuario", nullable = false)
    private String emailUsuario;

    @Column(name = "senhaUsuario", nullable = false)
    private String senhaUsuario;

    @Column(name = "telefoneUsuario", nullable = false)
    private String telefoneUsuario;

    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "descricaoUsuario", nullable = false)
    private String descricaoUsuario;

    @Column(name = "imagemUsuario")
    private byte[] imagemUsuario;

    //Um USUARIO pode ter 1-N COMIDAS
    @OneToMany(mappedBy = "idUsuario", targetEntity = Comida.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Comida> listaComidas = new ArrayList<Comida>();
}
