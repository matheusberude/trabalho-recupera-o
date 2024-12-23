package com.exemplo.produto.model;

import com.exemplo.produto.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto criarProduto(Produto produto) {
        produto.setId(idCounter++);
        produtos.add(produto);
        return produto;
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = buscarProdutoPorId(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        return produto;
    }

    public boolean deletarProduto(Long id) {
        return produtos.removeIf(p -> p.getId().equals(id));
    }
}