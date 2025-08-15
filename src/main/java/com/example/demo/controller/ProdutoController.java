package com.example.demo.controller;

import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController @RequestMapping("/api/v1/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ProdutoResponseDto cadastrarProdutoController(@RequestBody ProdutoRequestDto requestDto) {
        return service.cadastrarProduto(requestDto);
    }

    @GetMapping
    public Stream<ProdutoResponseDto> listarTodosProdutosController() {
        return service.listarTodosProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDto buscarPodutoPorIdController(@PathVariable Long id) {
        return service.buscarPodutoPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDto atualizarProdutoPorId(@PathVariable Long id,
                                                    @RequestBody ProdutoRequestDto requestDto) {
        return service.atualizarProdutoPorId(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deletarProdutoPorId(@PathVariable Long id) {
        service.deletarProdutoPorId(id);
    }

}
