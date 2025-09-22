package com.example.livro.service;

import com.example.livro.model.Livro;
import com.example.livro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listLivros() {
        return livroRepository.findAll();
    }

    public Livro saveLivro(Livro livro) {
        livro.setDataCadastro(LocalDate.now());
        return livroRepository.save(livro);
    }

    public void deleteLivro(Integer id) {
        livroRepository.deleteById(id);
    }
}
