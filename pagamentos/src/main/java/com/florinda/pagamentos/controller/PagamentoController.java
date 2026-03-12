package com.florinda.pagamentos.controller;

import com.florinda.pagamentos.dto.PagamentoDTO;
import com.florinda.pagamentos.model.Pagamento;
import com.florinda.pagamentos.repository.PagamentoRepository;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoRepository repository;

    public PagamentoController(PagamentoRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public List<PagamentoDTO> lista(){
       List<Pagamento> pagamentos =repository.findAll();
        return pagamentos.stream().map(PagamentoDTO::new).toList();
    }
@GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscaPorId(@PathVariable Long id){

    Pagamento pagamento = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    return ResponseEntity.ok(new PagamentoDTO(pagamento));

}

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<PagamentoDTO> confirma(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(pagamento -> {
                    pagamento.confirma();
                    return ResponseEntity.ok(new PagamentoDTO(pagamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
