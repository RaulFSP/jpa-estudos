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

/**
 *
 * @author usuario
 */
public class TutorialJpa {

    

    public static void main(String[] args) {
        
        var categoria = new Categoria(null, "celular");
        CategoriaRepository categoriaRepository = new CategoriaRepositoryImpl();
        categoria = categoriaRepository.save(categoria);
        System.out.println("passou");
        var produto = new Produto(null, "batata doce", "batata doce descascada", BigDecimal.valueOf(10.0), categoria);
        ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();
        produto = produtoRepository.save(produto);
        
        
        

        
    }
}
