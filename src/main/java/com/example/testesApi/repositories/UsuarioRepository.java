
package com.example.testesApi.repositories;

//importa a interface que fornece operações prontas para o banco
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testesApi.models.Usuario;

/*Define o repositorio responsavel pelos dados dos usuarios.
  Usuario indica a entidade que sera manipulada.
  Long indica o tipo de id dessa entidade.*/
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
