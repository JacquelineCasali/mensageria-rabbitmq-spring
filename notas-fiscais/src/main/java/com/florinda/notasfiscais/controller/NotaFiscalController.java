package com.florinda.notasfiscais.controller;

import com.florinda.notasfiscais.service.NotaFiscalService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {

private final NotaFiscalService notaFiscalService;

    public NotaFiscalController(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }
    @GetMapping("/pedidos/{pedidoId}")
    public String notaDoPedidoId(@PathVariable("pedidoId") Long pedidoId, @RequestParam(value = "valor", defaultValue = "0.0") BigDecimal valor) {
        return notaFiscalService.geraNotaFiscal(pedidoId, valor);
    }
}
