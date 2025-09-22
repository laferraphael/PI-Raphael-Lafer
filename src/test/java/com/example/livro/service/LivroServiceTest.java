package com.example.livro.service;

import com.example.livro.model.Livro;
import com.example.livro.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @Test
    public void test_listLivrosShouldReturnEmpty() {
        Mockito.when(livroRepository.findAll()).thenReturn(new ArrayList<>());

        List<Livro> livros = livroService.listLivros();

        Assertions.assertNotNull(livros);
        Assertions.assertTrue(livros.isEmpty());
    }

    @Test
    public void test_listLivrosShouldReturnOneBook() {
        Livro livro = new Livro();
        livro.setId(1);
        livro.setTitulo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setGenero("Ficção");
        livro.setAnoPublicacao(1899);

        Mockito.when(livroRepository.findAll()).thenReturn(List.of(livro));

        List<Livro> livros = livroService.listLivros();

        Assertions.assertNotNull(livros);
        Assertions.assertEquals(1, livros.size());
        Assertions.assertEquals("Dom Casmurro", livros.get(0).getTitulo());
    }

    @Test
    public void test_saveLivroShouldAddCreatedDate() {
        Livro livro = new Livro();
        livro.setTitulo("1984");
        livro.setAutor("George Orwell");
        livro.setGenero("Ficção");
        livro.setAnoPublicacao(1949);

        Mockito.when(livroRepository.save(Mockito.any(Livro.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Livro saved = livroService.saveLivro(livro);

        Assertions.assertNotNull(saved.getDataCadastro());
        Assertions.assertEquals("1984", saved.getTitulo());
    }

    @Test
    public void test_deleteLivroShouldCallRepository() {
        Mockito.doNothing().when(livroRepository).deleteById(1);

        livroService.deleteLivro(1);

        Mockito.verify(livroRepository, Mockito.times(1)).deleteById(1);
    }
}
