package com.florinda.pedidos.service.pedido;


import com.florinda.pedidos.domain.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoDTO> listar();
    PedidoDTO buscarPorId(Long id);


}
