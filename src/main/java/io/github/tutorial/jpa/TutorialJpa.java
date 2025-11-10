/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package io.github.tutorial.jpa;

import io.github.tutorial.jpa.entities.Categoria;
import io.github.tutorial.jpa.entities.Cliente;
import io.github.tutorial.jpa.entities.ItemPedido;
import io.github.tutorial.jpa.entities.Pedido;
import io.github.tutorial.jpa.entities.Produto;
import io.github.tutorial.jpa.repository.CategoriaRepository;
import io.github.tutorial.jpa.repository.CategoriaRepositoryImpl;
import io.github.tutorial.jpa.repository.ClienteRepository;
import io.github.tutorial.jpa.repository.ClienteRepositoryImpl;
import io.github.tutorial.jpa.repository.PedidoRepository;
import io.github.tutorial.jpa.repository.PedidoRepositoryImpl;
import io.github.tutorial.jpa.repository.ProdutoRepository;
import io.github.tutorial.jpa.repository.ProdutoRepositoryImpl;
import jakarta.transaction.Transactional;
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
        insertCliente();
        insertPedido();
        insertItems();
    }

    private static void insertCliente() {
        ClienteRepository clienteRepository = new ClienteRepositoryImpl();
        var clientes = List.of(new Cliente(null, "fulano", "12345"), new Cliente(null, "beltrano", "1234"));
        clienteRepository.saveAll(clientes);
    }

    private static void insertPedido() {
        ClienteRepository clienteRepository = new ClienteRepositoryImpl();
        PedidoRepository pedidoRepository = new PedidoRepositoryImpl();
        var clienteA = clienteRepository.findById(1L).orElseThrow();
        var clienteB = clienteRepository.findById(2L).orElseThrow();
        var pedidos = List.of(new Pedido(null, BigDecimal.ZERO, clienteA),
                new Pedido(null, BigDecimal.ZERO, clienteB));
        pedidoRepository.saveAll(pedidos);

    }

    @Transactional
    private static void insertItems() {
        PedidoRepository pedidoRepository = new PedidoRepositoryImpl();
        ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();

        var pedidoA = pedidoRepository.findByIdWithItems(1L).orElseThrow();
        var produtoA = produtoRepository.findById(1L).orElseThrow();
        var produtoB = produtoRepository.findById(2L).orElseThrow();
        var produtoC = produtoRepository.findById(3L).orElseThrow();
        var items = List.of(
                new ItemPedido(null, produtoA.getPreco(), 2, produtoA, pedidoA),
                new ItemPedido(null, produtoB.getPreco(), 1, produtoB, pedidoA),
                new ItemPedido(null, produtoC.getPreco(), 5, produtoC, pedidoA));
        
        pedidoA =pedidoRepository.saveWithItems(items,pedidoA);
        System.out.println(pedidoA.toString());
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
