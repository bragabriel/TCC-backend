package br.com.spotted.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "emailUsuario", nullable = false)
    private String emailUsuario;

    @Column(name = "senhaUsuario", nullable = false)
    private String senhaUsuario;

    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

//    //Um 'User' pode ter N 'Comidas'
//    @OneToMany(mappedBy = "idResponsavel", targetEntity = Video.class, orphanRemoval = true)
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
//    private List<Video> listaVideos = new ArrayList<Video>();
}
