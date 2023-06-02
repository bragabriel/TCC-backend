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

    @Column(name = "descricao_usuario")
    private String descricaoUsuario;

    @Column(name = "url_imageUsuario")
    private String url;

    @Column(name = "sequence_imageUsuario")
    private Long sequence;

    @Column(name = "fileName_imageUsuario")
    private String fileName;

    //Um USUARIO pode ter 1-N COMIDAS
    @OneToMany(mappedBy = "idUsuario", targetEntity = Alimento.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Alimento> listaAlimentos = new ArrayList<Alimento>();

    @OneToMany(mappedBy = "idUsuario", targetEntity = Moradia.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Moradia> listaMoradias = new ArrayList<Moradia>();

    @OneToMany(mappedBy = "idUsuario", targetEntity = Carona.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Carona> listaCaronas = new ArrayList<Carona>();

    @OneToMany(mappedBy = "idUsuario", targetEntity = Estagio.class, orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Estagio> listaEstagios = new ArrayList<Estagio>();
}
