package com.example.livro.controller;

import com.example.livro.model.Livro;
import com.example.livro.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listLivros() {
        return livroService.listLivros();
    }

    @PostMapping
    public Livro saveLivro(@RequestBody Livro livro) {
        return livroService.saveLivro(livro);
    }

    @DeleteMapping("/{id}")
    public void deleteLivro(@PathVariable Integer id) {
        livroService.deleteLivro(id);
    }
}
