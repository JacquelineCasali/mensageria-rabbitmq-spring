package com.florinda.pedidos.service.cardapio;



import com.florinda.pedidos.domain.dto.ItemCardapioDTO;

import com.florinda.pedidos.domain.enums.CategoriaCardapio;
import com.florinda.pedidos.domain.model.ItemCardapio;
import com.florinda.pedidos.exception.NotFoundException;

import com.florinda.pedidos.repository.ItemCardapioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CardapioServiceImpl implements CardapioService {

private final ItemCardapioRepository itemCardapioRepository;

    public CardapioServiceImpl(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    @Override
    public List<ItemCardapioDTO> listar() {
        return itemCardapioRepository.findAll().stream().map(ItemCardapioDTO::new).toList();
    }
    @Override
    public ItemCardapioDTO buscarPorId(Long id) {
        return itemCardapioRepository.findById(id).map(ItemCardapioDTO::new)
                .orElseThrow(()-> new NotFoundException("Item no cardapio não encontrado: " + id ));
    }


    @Override
    public ItemCardapioDTO criar(ItemCardapioDTO itemCardapioDTO) {
        ItemCardapio item =new ItemCardapio(itemCardapioDTO);
        return new ItemCardapioDTO(itemCardapioRepository.save(item));
    }

    @Override
    @Transactional
    public ItemCardapioDTO atualizar(Long id, ItemCardapioDTO dto) {
        return itemCardapioRepository.findById(id)
                .map(itemExistente -> {
                    itemExistente.setNome(dto.getNome());
                    itemExistente.setDescricao(dto.getDescricao());
                    itemExistente.setPreco(dto.getPreco());
                    itemExistente.setPrecoPromocional(dto.getPrecoPromocional());
                    if (dto.getCategoria() != null) {
                        itemExistente.setCategoria(CategoriaCardapio.valueOf(dto.getCategoria()));
                    }
                   ItemCardapio salvo = itemCardapioRepository.save(itemExistente);
                   return new ItemCardapioDTO(salvo);
                })
                .orElseThrow(() -> new NotFoundException("Item do cardápio não encontrado: " + id));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        ItemCardapio item = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não encontrado: " + id));
        itemCardapioRepository.delete(item);
        log.info("Item {} deletado com sucesso", id);

    }


}
