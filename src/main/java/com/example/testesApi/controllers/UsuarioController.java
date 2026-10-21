
package com.example.testesApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testesApi.models.Usuario;
import com.example.testesApi.services.UsuarioService;

import jakarta.validation.Valid;
//indica que esta classe recebe requisições HTTP e retorna respostas
@RestController

//define o endereço inicial dos endpoints desta classe
@RequestMapping("/api/usuarios")
public class UsuarioController {

    //Armazena a referencia pelo onstrutor atraves da injeção de dependencia
    private final UsuarioService usuarioService;

    //recebe o serviço pelo construtor atraves da injeção de dependencia
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    //cadastra um usuario atraves da requisição POST
    @PostMapping
    public ResponseEntity<Usuario> salvar(
            @Valid @RequestBody Usuario usuario) {

        //envia o usuario para ser salvo pelo serviço
        Usuario usuarioSalvo = usuarioService.salvar(usuario);

        //retorna o usuario cadastrado com o status HTTP 201
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }
    //retorna lista de todos os usuario cafastrados
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {

        //solicita ao serviço todos os usuarios 
        List<Usuario> usuarios = usuarioService.listarTodos();

        //retorna a lista com o status HTTP 200
        return ResponseEntity.ok(usuarios);
    }

    //busca usuario informado pelo ID na URL
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        //solocita ao serviço que procure o usuario pelo ID
        Usuario usuario = usuarioService.buscarPorId(id);

        //retorna o usuario encontrado com o status HTTP 200
        return ResponseEntity.ok(usuario);
    }

    // Exclui um usuário pelo ID informado na URL.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        // Solicita ao serviço que exclua o usuário.
        usuarioService.excluir(id);

        // Retorna o status HTTP 204, sem conteúdo na resposta.
        return ResponseEntity.noContent().build();
    }
    
}
