package com.florinda.pedidos.service.pedido;


import com.florinda.pedidos.domain.dto.PedidoDTO;
import com.florinda.pedidos.exception.NotFoundException;
import com.florinda.pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidoServiceImpl implements PedidoService {

private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<PedidoDTO> listar() {
        return pedidoRepository.findAll().stream().map(PedidoDTO::new).toList();
    }
    @Override
    public PedidoDTO buscarPorId(Long id) {
        return pedidoRepository.findById(id).map(PedidoDTO::new)
                .orElseThrow(()-> new NotFoundException("Pedido não encontrado: " + id ));
    }


}
