package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.dto.RelatorioVendasDto;
import java.util.List;

public interface RelatorioRepository {

    List<RelatorioVendasDto> relatorioVendasDto();
}
