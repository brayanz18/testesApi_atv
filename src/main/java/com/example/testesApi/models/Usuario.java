package com.example.testesApi.models;
//Importa as anotações para criar uma entidade no banco de dados 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // informa ao JPA que essa classe representa uma entidade que 
// pode ser armazenada no banco de dados 
public class Usuario {

    @Id// indica qual atributo identifica cada usuario
    @GeneratedValue(strategy = GenerationType.IDENTITY) //permite que o banco gere o id automaticamente 
    private Long id;

    @NotBlank(message = "O nome é obrigatório") //impede que o nome fique vazio ou contenha apenas espaços
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")//limita o nome até 100 caracteres
    private String nome;
    //construtor vazio exigido pelo JPA para criar objetos 
    public Usuario() {
    }
    //construtor que permite criar um usuario informando o noem
    public Usuario(String nome) {
        this.nome = nome;
    }
    //retorna o id do usuario
    public Long getId() {
        return id;
    }
    //retorna o nome do usuario 
    public String getNome() {
        return nome;
    }
    //altera o nome do usuário
    public void setNome(String nome) {
        this.nome = nome;
    }
}