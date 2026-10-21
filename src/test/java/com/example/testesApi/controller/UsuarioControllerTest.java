package com.example.testesApi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

        @Autowired
        private MockMvc mockMvc;

        // Testa o cadastro de um usuário
        @Test
        void deveCadastrarUsuario() throws Exception {
                mockMvc.perform(post("/api/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"nome\":\"Brayan\"}"))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.nome").value("Brayan"))
                                .andExpect(
                                                org.springframework.test.web.servlet.result.MockMvcResultMatchers
                                                                .content().contentTypeCompatibleWith(
                                                                                MediaType.APPLICATION_JSON));
        }

        // Testa a listagem de usuários
        @Test
        void deveListarUsuarios() throws Exception {
                mockMvc.perform(get("/api/usuarios"))
                                .andExpect(status().isOk());
        }

        // Testa a exclusão de um usuário cadastrado
        @Test
        void deveExcluirUsuario() throws Exception {

                // Cadastra um usuário
                mockMvc.perform(post("/api/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"nome\":\"Brayan\"}"))
                                .andExpect(status().isCreated());

                // Busca a lista de usuários e verifica se foi retornada corretamente
                String resposta = mockMvc.perform(get("/api/usuarios"))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                // Extrai o ID do usuário usando uma expressão regular
                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\"id\"\\s*:\\s*(\\d+)")
                                .matcher(resposta);

                if (!matcher.find()) {
                        throw new AssertionError("ID do usuário não encontrado na resposta.");
                }

                long id = Long.parseLong(matcher.group(1));

                // Exclui o usuário pelo ID
                mockMvc.perform(delete("/api/usuarios/" + id))
                                .andExpect(status().isNoContent());
        }

        // Testa a busca de um usuário pelo ID
        @Test
        void deveBuscarUsuarioPorId() throws Exception {

                // Cadastra um usuário
                String resposta = mockMvc.perform(post("/api/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"nome\":\"Brayan\"}"))
                                .andExpect(status().isCreated())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                // Extrai o ID da resposta
                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\"id\"\\s*:\\s*(\\d+)")
                                .matcher(resposta);

                if (!matcher.find()) {
                        throw new AssertionError("ID do usuário não encontrado.");
                }

                long id = Long.parseLong(matcher.group(1));

                // Busca o usuário pelo ID
                mockMvc.perform(get("/api/usuarios/" + id))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.nome").value("Brayan"));
        }

        // Testa se a API rejeita um usuário com nome vazio
        @Test
        void deveRejeitarUsuarioComNomeVazio() throws Exception {
                mockMvc.perform(post("/api/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"nome\":\"\"}"))
                                .andExpect(status().isBadRequest());
        }

        // Testa a busca de um usuário que não existe

        // Testa a busca de um usuário que não existe
        @Test
        void deveRetornarErroQuandoUsuarioNaoExiste() throws Exception {
                mockMvc.perform(get("/api/usuarios/999999"))
                                .andExpect(status().isNotFound());
        }

}