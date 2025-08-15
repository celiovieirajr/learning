package com.example.demo;

import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

	@Mock
	private ProdutoRepository repository;

	@InjectMocks
	private ProdutoService service;


	@Test
	void testCriandoProduto() {
		ProdutoRequestDto requestDto = new ProdutoRequestDto("Célio", new BigDecimal("10.50"), 1);

		Produto produtoMock = new Produto(1L, "Célio", new BigDecimal("10.50"), 1);
		when(repository.save(any(Produto.class))).thenReturn(produtoMock);

		ProdutoResponseDto responseDto = service.cadastrarProduto(requestDto);

		assertNotNull(responseDto);
		assertEquals(1L, responseDto.getId());
		assertEquals("Célio", responseDto.getNome());
		assertEquals(new BigDecimal("10.50"), requestDto.getPreco());
		assertEquals(1, responseDto.getQuantidade());
	}

	@Test
	void testBuscarProdutoPorIdComSucesso() {
		Produto produtoMock = new Produto(1L, "Célio", new BigDecimal("10.50"), 1);
		when(repository.findById(1L)).thenReturn(Optional.of(produtoMock));

		ProdutoResponseDto responseDto = service.buscarPodutoPorId(1L);

		assertNotNull(responseDto);
		assertEquals(1L, responseDto.getId());
		assertEquals("Célio", responseDto.getNome());
	}


	@Test
	void testBuscarProdutoPorIdComErro() {
		when(repository.findById(99L)).thenReturn(java.util.Optional.empty());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			service.buscarPodutoPorId(99L);
		});

		assertEquals("404 NOT_FOUND \"ID NAO ENCONTRADO\"", exception.getMessage());
	}


	@Test
	void testAtualizarProdutoComSucesso() {
		Produto produtoExistente = new Produto(1L, "Antigo", new BigDecimal("5.00"), 2);
		Produto produtoAtualizado = new Produto(1L, "Novo", new BigDecimal("10.00"), 5);

		ProdutoRequestDto dto = new ProdutoRequestDto("Novo", new BigDecimal("10.00"), 5);

		when(repository.findById(1L)).thenReturn(java.util.Optional.of(produtoExistente));
		when(repository.save(any(Produto.class))).thenReturn(produtoAtualizado);

		ProdutoResponseDto response = service.atualizarProdutoPorId(1L, dto);

		assertEquals("Novo", response.getNome());
		assertEquals(new BigDecimal("10.00"), response.getPreco());
		assertEquals(5, response.getQuantidade());
	}


	@Test
	void testDeletarProdutoComSucesso() {
		Produto produtoMock = new Produto(1L, "Célio", new BigDecimal("10.50"), 1);
		when(repository.findById(1L)).thenReturn(java.util.Optional.of(produtoMock));

		service.deletarProdutoPorId(1L);

		verify(repository).delete(produtoMock);
	}
}
