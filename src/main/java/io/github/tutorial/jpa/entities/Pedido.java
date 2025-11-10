/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.tutorial.jpa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDate date;
    private BigDecimal valorTotal;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemPedido> items = new HashSet<>();

    public Pedido() {
    }

    public Pedido(Long id, BigDecimal valorTotal, Cliente cliente) {
        this.id = id;

        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    public void addItem(ItemPedido item) {
        this.items.add(item);
        item.setPedido(this);
    }

    public void addAllItems(Collection<ItemPedido> items) {
        this.items.addAll(items);
        items.stream().forEach(item -> item.setPedido(this));

    }

    public void calcularTotal() {
        var total = this.items.stream().map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.valorTotal = total;
    }

    public Set<ItemPedido> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", date=" + date + ", valorTotal=" + valorTotal + ", cliente=" + cliente + ", items=" + items + '}';
    }

}
