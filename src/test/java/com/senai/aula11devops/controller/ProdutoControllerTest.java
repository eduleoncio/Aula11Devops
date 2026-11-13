package com.senai.aula11devops.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testarCadastrarProduto() throws Exception {
        String produto = """
                {
                  "id": 1,
                  "nome": "Notebook",
                  "preco": 3500
                }
                """;

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produto))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Notebook"))
                .andExpect(jsonPath("$.preco").value(3500));
    }

    @Test
    void testarListarProdutos() throws Exception {
        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk());
    }
}
