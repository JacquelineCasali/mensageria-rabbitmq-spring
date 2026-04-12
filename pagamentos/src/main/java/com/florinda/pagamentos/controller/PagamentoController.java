package com.florinda.pagamentos.controller;

import com.florinda.pagamentos.domain.dto.PagamentoDTO;

import com.florinda.pagamentos.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> lista(){
        return ResponseEntity.ok(service.lista());
    }
@GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscaPorId(@PathVariable Long id){
return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
}

    @PutMapping("/{id}")
   public  ResponseEntity<PagamentoDTO> confirma(@PathVariable("id") Long id) {
      return ResponseEntity.ok(service.confirma(id));
    }
}
