/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.ItemPedido;
import io.github.tutorial.jpa.entities.Pedido;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author usuario
 */
public interface PedidoRepository extends CrudRepository<Pedido, Long>{
    
    Optional<Pedido> findByIdWithItems(Long id);
    Pedido saveWithItems(Collection<ItemPedido> items,Pedido pedido);
}
