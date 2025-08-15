package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoResponseDto {
    private long id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

}
