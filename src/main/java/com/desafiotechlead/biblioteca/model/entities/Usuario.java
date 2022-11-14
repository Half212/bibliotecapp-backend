package com.desafiotechlead.biblioteca.model.entities;

import com.desafiotechlead.biblioteca.model.enums.PerfilEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    @Column(name = "email",unique = true, nullable = false, length = 200)
    private String email;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @PrePersist
    public void PrePersist(){
        setPerfil(PerfilEnum.CLIENTE);
    }

}
