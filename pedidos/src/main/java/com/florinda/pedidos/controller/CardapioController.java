package com.florinda.pedidos.controller;

import com.florinda.pedidos.domain.dto.ItemCardapioDTO;

import com.florinda.pedidos.service.cardapio.CardapioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/itens-cardapio")
public class CardapioController {

    private CardapioService cardapioService;

    public CardapioController(CardapioService cardapioSerivce) {
        this.cardapioService = cardapioSerivce;
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapioDTO>> lista(){
       return ResponseEntity.ok(cardapioService.listar());
                  }

    @GetMapping("/{id}")
    public  ResponseEntity<ItemCardapioDTO> buscaPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cardapioService.buscarPorId(id));
         }

    @PostMapping
    public ResponseEntity<ItemCardapioDTO> criar(@RequestBody ItemCardapioDTO itemCardapioDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cardapioService.criar(itemCardapioDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioDTO> atualizr(@PathVariable Long id, @RequestBody ItemCardapioDTO itemCardapioDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cardapioService.atualizar(id,itemCardapioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cardapioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
