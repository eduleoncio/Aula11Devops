package com.senai.aula11devops.controller;

import com.senai.aula11devops.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final List<Produto> produtos = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        produtos.add(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return List.copyOf(produtos);
    }
}
