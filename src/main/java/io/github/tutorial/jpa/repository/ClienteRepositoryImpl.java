/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.Cliente;
import io.github.tutorial.jpa.util.JPAUtil;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

/**
 *
 * @author usuario
 */
public class ClienteRepositoryImpl extends CrudRepositoryImpl<Cliente, Long> implements ClienteRepository{
    public ClienteRepositoryImpl(){
        super(Cliente.class);
    }

    @Override
    public Optional<Cliente> findClienteByNome(String nome) {
        try(var em = JPAUtil.getInstance().getEmf().createEntityManager()){
            String jpql = "select c from Cliente c where (:nome is null or c.nome like :nome)";
            String nomeParam = (nome == null || nome.isBlank()) ? null : "%"+nome.trim()+"%";
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("nome", nomeParam);
            return query.setMaxResults(1).getResultStream().findFirst();
        }
    }
}
