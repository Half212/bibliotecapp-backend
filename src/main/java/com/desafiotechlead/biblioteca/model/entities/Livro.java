package com.desafiotechlead.biblioteca.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Livro livro = (Livro) o;
        return id != null && Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
