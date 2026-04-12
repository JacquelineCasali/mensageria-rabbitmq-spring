package com.florinda.pedidos.repository;


import com.florinda.pedidos.domain.model.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
}
