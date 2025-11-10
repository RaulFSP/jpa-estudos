/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.Cliente;

/**
 *
 * @author usuario
 */
public class ClienteRepositoryImpl extends CrudRepositoryImpl<Cliente, Long> implements ClienteRepository{
    public ClienteRepositoryImpl(){
        super(Cliente.class);
    }
}
