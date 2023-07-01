package br.com.spotted.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "sobrenome_usuario", nullable = false)
    private String sobrenomeUsuario;

    @Column(name = "email_usuario", nullable = false)
    private String emailUsuario;

    @Column(name = "senha_usuario", nullable = false)
    private String senhaUsuario;

    @Column(name = "telefone_usuario", nullable = false)
    private String telefoneUsuario;

    @Column(name = "dataNascimento_usuario", nullable = false)
    private Date dataNascimento;

    @Column(name = "url_imageUsuario")
    private String url;

    @Column(name = "sequence_imageUsuario")
    private Long sequence;

    @Column(name = "fileName_imageUsuario")
    private String fileName;

    //Um USUARIO pode ter 1-N ARTEFATOS; um ARTEFATO pode pertencer a 1-1 USUARIO
    @OneToMany(mappedBy = "usuario", targetEntity = Artefato.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Artefato> listaArtefatos = new ArrayList<Artefato>();
}
