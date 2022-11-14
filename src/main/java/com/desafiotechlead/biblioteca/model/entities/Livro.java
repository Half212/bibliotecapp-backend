package com.desafiotechlead.biblioteca.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "autor", nullable = false, length = 200)
    private String autor;

    @Column(name = "data_cadastro", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "usuario_email")
    private String usuarioEmail;

    @PrePersist
    public void PrePersist(){
        setDataCadastro(LocalDate.now());
    }
}
