package com.example.livro.repository;

import com.example.livro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
