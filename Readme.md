testesApi — Testes em APIs REST com Java e Spring Boot

1. Descrição do projeto
O objetivo dessa atividade foi desenvolver uma API REST utilizando java e spring boot e aplicar testes automatizados para verificar se a mesma estava funcionando corretamente, identificar possíveis erros e analisar a cobertura do codigo 

2. Tecnologias utilizadas
As tecnologias utilizadas foram Java, Spring boot, Spring data JPA, Banco de Dados H2, JUnit 5, Mockito, MockMvc, Maven e JaCoCo.

3. Desenvolvimento da API
foi desenvolvido o projeto testesApi, padronizado com classes com responsabilidades diferentes, cada uma exercendo uma função:

  1- Usuario: representa os usuários e as suas validações
  2- UsuarioRepository: realiza operações de acesso aos dados
  3- UsuarioService: contém a lógica do negócio 
  4- UsuarioController: disponibiliza endpoints REST
  5- UsuarioNaoEncontradoException: trata busxa de usuarios inexistentes

  A API permite cadatrar, listar, buscar e excluir usuários.

4. Testes automatizados 
Foram criados testes unitários com JUnit 5 e Mockito para verificar o salvamento de usuarios, a busca por ID e o tratamento de usuarios inexistentes.

5. Correções e erros
Durante o desenvolvimento precisei ajustar o tratamento de exceções para que uma busca por usuarios inexistentes retornasse HTTP 404, tambem organizei corretamente os arquivos de implementação e de testes para corrigir alguns erros de compilação.

6. Cobertura de testes 
Foi utilizada a ferramenta JaCoCo para analisar a cobertura do código. O relatório apresentou 91% de cobertura de instruções, com 100% nos pacotes de controllers. services e exceptions.

7. Execução dos testes
Para compilar o projeto e executar os testes, utilizei o comando

".\mvnw.cmd clean verify"

Após os ajustes, o terminal apareceu build sucess, indicando que a execução foi concluída com sucesso 

8. Conceitos teóricos 
A atividade também envolve conceitos importantes 

 1- Erro, defeito e falha: esses são conceitos relacionados as causa e manfestações de problemas de software.
    
    exemplo de erro (Humano): O programador esquece de configurar o tratamento para quando um usuário não é encontrado.

    exemplo de defeito(Codigo): A aplicação lança a exceção UsuarioNaoEncontradoException, mas não está configurada para retornar o status HTTP esperado.

    exemplo de falha(durante a execução): Ao buscar um usuário inexistente, a requisição não retorna a resposta HTTP 404 esperada e acaba gerando um erro durante o teste.

 2- Pirâmide de testes: Organização dos testes unitarios, tanto de integração quanto de ponta a ponta

 3- Caixa preta e caixa branca: Abordagens para testar o comportamento externo e e aspectos internos do código

 4- CI/CD: São práticas de automação da integração, entrega e impantação de sofrware

 5- JaCoCo: É a ferramenta utilizada para medir a cobertura do código pelos testes.

9. Conclusão

A atividade permitiu desenvolver uma API REST e aplicar na prática os testes automatizados para verificar suas funcionalidades e respostas vê-las funcionando na prática. O uso do JUnit 5, Mockito e JaCoCO ajudou a avaliar o comportamento da aplicação, crrigir problemas e analisar a cobertura do código, que atingiu 91% ma execução registrada.


