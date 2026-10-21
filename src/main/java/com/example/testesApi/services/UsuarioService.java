package com.example.testesApi.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.testesApi.exceptions.UsuarioNaoEncontradoException;
import com.example.testesApi.models.Usuario;
import com.example.testesApi.repositories.UsuarioRepository;

// Informa ao Spring que esta classe contém a lógica do sistema.
@Service
public class UsuarioService {

    // Guarda a referência ao repositório que acessa o banco.
    private final UsuarioRepository usuarioRepository;

    // Recebe o repositório pelo construtor (injeção de dependência).
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Recebe um usuário e solicita que o repositório o salve.
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Busca e retorna todos os usuários cadastrados.
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Procura um usuário pelo ID informado.
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                // Se não encontrar o usuário, lança nossa exceção personalizada.
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    // Exclui um usuário depois de confirmar que ele existe.
    public void excluir(Long id) {

        // Primeiro, busca o usuário pelo ID.
        Usuario usuario = buscarPorId(id);

        // Depois, solicita a exclusão ao repositório.
        usuarioRepository.delete(usuario);
    }
}
