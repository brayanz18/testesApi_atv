
package com.example.testesApi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.testesApi.exceptions.UsuarioNaoEncontradoException;
import com.example.testesApi.models.Usuario;
import com.example.testesApi.repositories.UsuarioRepository;

// Habilita o Mockito durante a execução dos testes.
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    // Cria uma versão simulada do repositório, sem acessar o banco.
    @Mock
    private UsuarioRepository usuarioRepository;

    // Cria o Service e injeta nele o repositório simulado.
    @InjectMocks
    private UsuarioService usuarioService;

    // Guarda o usuário que será utilizado nos testes.
    private Usuario usuario;

    // Executa antes de cada teste para preparar os dados.
    @BeforeEach
    void configurar() {
        usuario = new Usuario("Brayan");
    }

    // Verifica se o Service consegue salvar um usuário.
    @Test
    void deveSalvarUsuario() {

        // Configura o repositório simulado para devolver o usuário.
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Executa o método que está sendo testado.
        Usuario resultado = usuarioService.salvar(usuario);

        // Confirma que o nome retornado é o esperado.
        assertEquals("Brayan", resultado.getNome());

        // Confirma que o método save foi chamado com esse usuário.
        verify(usuarioRepository).save(usuario);
    }

    // Verifica se o Service consegue buscar um usuário pelo ID.
    @Test
    void deveBuscarUsuarioPorId() {

        // Simula a busca encontrando um usuário com o ID informado.
        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        // Executa a busca pelo ID 1.
        Usuario resultado = usuarioService.buscarPorId(1L);

        // Confirma que o usuário encontrado tem o nome esperado.
        assertEquals("Brayan", resultado.getNome());
    }

    // Verifica o comportamento quando o usuário não existe.
    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {

        // Simula uma busca sem resultado.
        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Confirma que a busca lança a exceção esperada.
        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.buscarPorId(1L)
        );
    }
}
