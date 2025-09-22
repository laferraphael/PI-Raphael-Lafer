package com.example.livro.controller;

import com.example.livro.model.Livro;
import com.example.livro.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LivroControllerTest {

    @InjectMocks
    private LivroController livroController;

    @Mock
    private LivroService livroService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(livroController).build();
    }

    @Test
    public void test_listLivrosShouldReturnOneBook() throws Exception {
        Livro livro = new Livro();
        livro.setId(1);
        livro.setTitulo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setGenero("Ficção");
        livro.setAnoPublicacao(1899);
        livro.setDataCadastro(LocalDate.now());

        Mockito.when(livroService.listLivros()).thenReturn(List.of(livro));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/livros"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Dom Casmurro"));
    }

    @Test
    public void test_saveLivroShouldReturnCreatedBook() throws Exception {
        Livro livro = new Livro();
        livro.setId(1);
        livro.setTitulo("1984");
        livro.setAutor("George Orwell");
        livro.setGenero("Ficção");
        livro.setAnoPublicacao(1949);
        livro.setDataCadastro(LocalDate.now());

        Mockito.when(livroService.saveLivro(Mockito.any(Livro.class))).thenReturn(livro);

        String json = """
            {
                "titulo": "1984",
                "autor": "George Orwell",
                "genero": "Ficção",
                "anoPublicacao": 1949
            }
            """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("1984"));
    }

    @Test
    public void test_deleteLivroShouldReturnNoContent() throws Exception {
        Mockito.doNothing().when(livroService).deleteLivro(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/livros/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(livroService, Mockito.times(1)).deleteLivro(1);
    }
}
