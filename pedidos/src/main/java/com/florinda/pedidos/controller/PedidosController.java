package com.florinda.pedidos.controller;


import com.florinda.pedidos.domain.dto.PedidoDTO;

import com.florinda.pedidos.service.pedido.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private final PedidoService pedidoService;

    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> lista(){
        return ResponseEntity.ok(pedidoService.listar());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<PedidoDTO> buscaPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPorId(id));
    }
}
