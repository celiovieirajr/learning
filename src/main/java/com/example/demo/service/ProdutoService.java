package com.example.demo.service;

import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoResponseDto cadastrarProduto(ProdutoRequestDto requestDto) {
        Produto model = toModel(requestDto);
        Produto modelSave = repository.save(model);

        return toResponse(modelSave);
    }

    public Stream<ProdutoResponseDto> listarTodosProdutos() {
        return repository.findAll().stream().map(this::toResponse);
    }

    public ProdutoResponseDto buscarPodutoPorId(Long id) {
        Produto model = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NAO ENCONTRADO"));

        return toResponse(model);
    }

    public ProdutoResponseDto atualizarProdutoPorId(Long id, ProdutoRequestDto requestDto) {
        Produto model = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NAO ENCONTRADO"));

        model.setNome(requestDto.getNome());
        model.setQuantidade(requestDto.getQuantidade());
        model.setPreco(requestDto.getPreco());

        Produto modelSave = repository.save(model);
        return toResponse(modelSave);
    }

    public void deletarProdutoPorId(Long id) {
        Produto model = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NAO ENCONTRADO"));

        repository.delete(model);
    }


    private Produto toModel(ProdutoRequestDto requestDto) {
        Produto model = new Produto();
        model.setNome(requestDto.getNome());
        model.setPreco(requestDto.getPreco());
        model.setQuantidade(requestDto.getQuantidade());

        return model;
    }

    private ProdutoResponseDto toResponse(Produto model) {
        ProdutoResponseDto responseDto = new ProdutoResponseDto();
        responseDto.setId(model.getId());
        responseDto.setNome(model.getNome());
        responseDto.setPreco(model.getPreco());
        responseDto.setQuantidade(model.getQuantidade());

        return responseDto;
    }
}
