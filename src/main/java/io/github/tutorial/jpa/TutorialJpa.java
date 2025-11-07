/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package io.github.tutorial.jpa;

import io.github.tutorial.jpa.entities.Categoria;
import io.github.tutorial.jpa.entities.Produto;
import io.github.tutorial.jpa.repository.CategoriaRepository;
import io.github.tutorial.jpa.repository.CategoriaRepositoryImpl;
import io.github.tutorial.jpa.repository.ProdutoRepository;
import io.github.tutorial.jpa.repository.ProdutoRepositoryImpl;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author usuario
 */
public class TutorialJpa {

    public static void main(String[] args) {

        insertCategoria();
        insertProduto();
    }

    private static void insertCategoria() {
        CategoriaRepository categoriaRepository = new CategoriaRepositoryImpl();
        var categorias = List.of(new Categoria(null, "celular"),
                new Categoria(null, "informática"),
                new Categoria(null, "lazer"),
                new Categoria(null, "livro"));

        categoriaRepository.saveAll(categorias);

    }

    private static void insertProduto() {
        CategoriaRepository categoriaRepository = new CategoriaRepositoryImpl();
        ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();
        var categorias = categoriaRepository.findAll();
        var produtos = List.of(
                new Produto(null, "GPU A",
                "gpu de entrada",
                BigDecimal.valueOf(1200.0), categoriaRepository.findByNome("informática").orElseThrow()),
                
                new Produto(null, "como programar", "livro de algoritmos da programação",
                        BigDecimal.valueOf(120.0), categoriaRepository
                        .findByNome("livro").orElseThrow()),
                
                new Produto(null, "Computador de escritório", "computador 8gb, cpu quad core e gpu 2gb",
                        BigDecimal.valueOf(800.0), categoriaRepository
                        .findByNome("informática").orElseThrow()),
                
                new Produto(null, "MotoG trincado", "celular padrão tupiniquim",
                        BigDecimal.valueOf(299.0), categoriaRepository
                        .findByNome("celular").orElseThrow()));
        produtoRepository.saveAll(produtos);
        
    }
}
