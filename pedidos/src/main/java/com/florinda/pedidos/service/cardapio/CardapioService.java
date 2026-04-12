package com.florinda.pedidos.service.cardapio;


import com.florinda.pedidos.domain.dto.ItemCardapioDTO;


import java.util.List;

public interface CardapioService {

    ItemCardapioDTO buscarPorId(Long id);
    ItemCardapioDTO criar(ItemCardapioDTO itemCardapioDTO);
    ItemCardapioDTO atualizar(Long id, ItemCardapioDTO itemCardapioDTO);
    void excluir(Long id);
    List<ItemCardapioDTO> listar();;
}
