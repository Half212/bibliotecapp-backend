package com.desafiotechlead.biblioteca.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    private String perfil;

    @NotEmpty(message = "{campo.email.obrigatorio}")
    @Email(message = "{campo.email.invalido}")
    private String email;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

}
