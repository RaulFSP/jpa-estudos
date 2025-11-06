/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.Categoria;

/**
 *
 * @author usuario
 */
public class CategoriaRepositoryImpl extends CrudRepositoryImpl<Categoria, Long> implements CategoriaRepository {

    public CategoriaRepositoryImpl() {
        super(Categoria.class);
    }
    
}
