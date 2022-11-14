package com.desafiotechlead.biblioteca.rest.controllers;

import com.desafiotechlead.biblioteca.model.entities.Livro;
import com.desafiotechlead.biblioteca.model.entities.Usuario;
import com.desafiotechlead.biblioteca.model.services.LivroService;
import com.desafiotechlead.biblioteca.model.services.UsuarioService;
import com.desafiotechlead.biblioteca.rest.dto.LivroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private static final Logger log = LoggerFactory.getLogger(LivroController.class);

    @Autowired
    private LivroService livroService;

    @Autowired
    private UsuarioService usuarioService;

    public LivroController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro salvar(@RequestBody @Valid LivroDTO livroDTO){

        log.info("Adicionando livro: {}", livroDTO.toString());

        Livro livro = converterDtoParaLivro(livroDTO);

        return livroService.persistir(livro);
    }

    @GetMapping
    public List<Livro> listarTodos() {

        log.info("Listando todos os livros: {}");

        return livroService.buscarTodos();
    }

    @GetMapping("/{id}")
    public LivroDTO buscarPorId(@PathVariable Long id) {

        LivroDTO livroDTO = new LivroDTO();

        log.info("Buscando livro por id: {}", id);
        livroService.findById(id)
                .map(livro -> {
                    livroDTO.setId(livro.getId());
                    livroDTO.setNome(livro.getNome());
                    livroDTO.setAutor(livro.getAutor());
                    livroDTO.setDataCadastro(livro.getDataCadastro());
                    livroDTO.setUsuarioEmail(livro.getUsuarioEmail());
                    return livroDTO;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        return livroDTO;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid LivroDTO livroAtualizado) {

        livroService.findById(id)
                .map(livro -> {
                    livro.setNome(livroAtualizado.getNome());
                    livro.setAutor(livroAtualizado.getAutor());
                    return livroService.persistir(livro);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        livroService.findById(id)
                .map(livro -> {
                    livroService.deletar(id);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * converte livroDTO para livro
     *
     * @param livroDTO
     * @return livro
     */

    private Livro converterDtoParaLivro(LivroDTO livroDTO){

        Livro livro = new Livro();
        livro.setNome(livroDTO.getNome());
        livro.setAutor(livroDTO.getAutor());
        livro.setUsuarioEmail(livroDTO.getUsuarioEmail());

        return livro;
    }


}
