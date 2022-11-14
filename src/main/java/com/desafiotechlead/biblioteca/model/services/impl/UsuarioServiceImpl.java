package com.desafiotechlead.biblioteca.model.services.impl;

import com.desafiotechlead.biblioteca.model.entities.Usuario;
import com.desafiotechlead.biblioteca.model.repositories.UsuarioRepository;
import com.desafiotechlead.biblioteca.model.services.UsuarioService;
import com.desafiotechlead.biblioteca.exceptions.UsuarioCadastradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario persistir(Usuario usuario) {
        log.info("Persistindo usuario: {}", usuario);

        boolean exists = this.usuarioRepository.existsByEmail(usuario.getEmail());
        if (exists) {
            throw new UsuarioCadastradoException(usuario.getEmail());
        }

        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        log.info("Buscando usuario por email: {}", email);
        return this.usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        log.info("Buscando usuario por id: {}", id);
        return this.usuarioRepository.findById(id);
    }

    @Override
    public void deletar(Long id) {
        log.info("Deletando usuario por id: {}", id);
        this.usuarioRepository.deleteById(id);
    }
}
