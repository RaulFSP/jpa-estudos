/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.Cliente;
import java.util.Optional;

/**
 *
 * @author usuario
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
    Optional<Cliente> findClienteByNome(String nome);
}
