/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.ItemPedido;
import io.github.tutorial.jpa.entities.Pedido;
import io.github.tutorial.jpa.util.JPAUtil;
import jakarta.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author usuario
 */
public class PedidoRepositoryImpl extends CrudRepositoryImpl<Pedido, Long> implements PedidoRepository{
    
    public PedidoRepositoryImpl() {
        super(Pedido.class);
    }

    @Override
    public Optional<Pedido> findByIdWithItems(Long id) {
        String jpql = "select p from Pedido p left join fetch p.items where p.id =:id";
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()){
        TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
        return query.setParameter("id", id).setMaxResults(1).getResultStream().findFirst();
        
            
        }
    }

    @Override
    public Pedido saveWithItems(Collection<ItemPedido> items, Pedido pedido) {
        String jpql = "select p from Pedido p left join fetch p.items where p.id =:id";
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            var pedidoItems = query.setParameter("id", pedido.getId()).setMaxResults(1).getResultStream().findFirst().orElseThrow();
            pedidoItems.addAllItems(items);
            pedidoItems.calcularTotal();
            em.merge(pedidoItems);
            em.getTransaction().commit();
            return pedidoItems;
        }
    }
    
    
    
}
