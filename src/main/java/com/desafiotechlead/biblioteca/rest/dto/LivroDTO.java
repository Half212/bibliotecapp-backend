package com.desafiotechlead.biblioteca.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LivroDTO {

    private Long id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.autor.obrigatorio}")
    private String autor;

    private String usuarioEmail;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
}
