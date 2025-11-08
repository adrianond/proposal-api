package br.com.example.proposal.api.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "LOGIN_USUARIO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUsuario {

    @Id
    @Column(name = "usuario")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    private Usuario usuario;
    @Column(name = "senha")
    private String senha;
}
