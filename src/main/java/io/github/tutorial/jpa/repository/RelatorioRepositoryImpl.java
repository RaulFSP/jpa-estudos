/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.dto.RelatorioVendasDto;
import io.github.tutorial.jpa.util.JPAUtil;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author usuario
 */
public class RelatorioRepositoryImpl implements RelatorioRepository {

    @Override
    public List<RelatorioVendasDto> relatorioVendasDto() {
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()) {
            String jpql = "select prod.nome, sum(item.quantidade), max(pe.date) from Pedido pe inner join pe.items item inner join item.produto prod group by prod.nome order by item.quantidade desc";
            TypedQuery<RelatorioVendasDto> query = em.createQuery(jpql, RelatorioVendasDto.class);
            return query.getResultList();
        }
    }

}
