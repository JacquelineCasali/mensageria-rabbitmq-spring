package com.florinda.pedidos.domain.dto;

import com.florinda.pedidos.domain.model.ItemCardapio;

import java.math.BigDecimal;

public class ItemCardapioDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private BigDecimal preco;
    private BigDecimal precoPromocional;

    public ItemCardapioDTO() {
    }

    public ItemCardapioDTO(ItemCardapio itemCardapio) {
        this.id = itemCardapio.getId();
        this.nome = itemCardapio.getNome();
        this.descricao = itemCardapio.getDescricao();
        this.categoria = itemCardapio.getCategoria().name();
        this.preco = itemCardapio.getPreco();
        this.precoPromocional = itemCardapio.getPrecoPromocional();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }


}
