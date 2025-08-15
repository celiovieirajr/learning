package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProdutoRequestDto {

    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
}
