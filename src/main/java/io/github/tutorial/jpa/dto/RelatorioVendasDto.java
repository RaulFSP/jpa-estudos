package io.github.tutorial.jpa.dto;

import java.time.LocalDate;

public record RelatorioVendasDto(String nomeProduto, Long quantidade, LocalDate dataRecente) {

}
