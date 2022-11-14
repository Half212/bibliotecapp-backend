package com.desafiotechlead.biblioteca.model.services.impl;

import com.desafiotechlead.biblioteca.model.entities.Livro;
import com.desafiotechlead.biblioteca.model.repositories.LivroRepository;
import com.desafiotechlead.biblioteca.model.services.LivroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {

    private static final Logger log = LoggerFactory.getLogger(LivroServiceImpl.class);

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public Livro persistir(Livro livro) {
        log.info("Persistindo livro: {}", livro);
        return this.livroRepository.save(livro);
    }

    @Override
    public Optional<Livro> findByName(String nome) {
        log.info("Buscando livro por nome: {}", nome);
        return Optional.ofNullable(this.livroRepository.findByNome(nome));
    }

    @Override
    public Optional<Livro> findById(Long id) {
        log.info("Buscando livro por id: {}", id);
        return this.livroRepository.findById(id);
    }

    @Override
    public List<Livro> buscarPorUsuarioEmail(String usuarioEmail) {
        log.info("Buscando livros para usuario ID {} ", usuarioEmail);
        return this.livroRepository.findByUsuarioEmail(usuarioEmail);
    }

    @Override
    public List<Livro> buscarTodos() {
        log.info("Listando todos os livros");
        return this.livroRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        log.info("Deletando usuario por id: {}", id);
        this.livroRepository.deleteById(id);
    }
}
