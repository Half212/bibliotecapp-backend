package com.desafiotechlead.biblioteca.rest.controllers;

import com.desafiotechlead.biblioteca.exceptions.UsuarioCadastradoException;
import com.desafiotechlead.biblioteca.model.entities.Usuario;
import com.desafiotechlead.biblioteca.model.enums.PerfilEnum;
import com.desafiotechlead.biblioteca.model.services.UsuarioService;
import com.desafiotechlead.biblioteca.rest.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) {

        try {
            usuarioService.persistir(converterDTOParaUsuario(usuarioDTO));
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


        return usuarioDTO;
    }

    @GetMapping("{id}")
    public UsuarioDTO buscarPorId(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        return usuarioService
                .findById(id)
                .map(usuario -> {
                    usuarioDTO.setId(usuario.getId());
                    usuarioDTO.setNome(usuario.getNome());
                    usuarioDTO.setEmail(usuario.getEmail());
                    usuarioDTO.setSenha(usuario.getSenha());
                    usuarioDTO.setPerfil("ROLE_CLIENTE");
                    return usuarioDTO;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usuario/{email}")
    public UsuarioDTO buscarPorEmail(@PathVariable String email) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        return usuarioService
                .findByEmail(email)
                .map(usuario -> {
                    usuarioDTO.setId(usuario.getId());
                    usuarioDTO.setNome(usuario.getNome());
                    usuarioDTO.setEmail(usuario.getEmail());
                    usuarioDTO.setSenha(usuario.getSenha());
                    usuarioDTO.setPerfil(usuario.getPerfil().toString());
                    return usuarioDTO;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        usuarioService.findById(id)
                .map(usuario -> {
                    usuarioService.deletar(id);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid Usuario usuarioAtualizado) {
        usuarioService.findById(id)
                .map(usuario -> {
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setPerfil(usuarioAtualizado.getPerfil());
                    usuario.setSenha(usuarioAtualizado.getSenha());
                    return usuarioService.persistir(usuario);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Usuario converterDTOParaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPerfil(PerfilEnum.valueOf(usuarioDTO.getPerfil()));

        return usuario;
    }
}
