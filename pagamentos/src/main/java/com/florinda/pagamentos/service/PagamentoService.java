package com.florinda.pagamentos.service;

import com.florinda.pagamentos.domain.dto.PagamentoDTO;

import java.util.List;

public interface PagamentoService {
    List<PagamentoDTO> lista();
PagamentoDTO buscarPorId(Long id);
PagamentoDTO confirma(Long id);
}
