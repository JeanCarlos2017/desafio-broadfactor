# desafio-broadfactor
Este repositório conterá os arquivos referente ao desafio da Broadfactor

# Exemplo de como efetuar cadastro e login no sistema 
## Cadastro
1. acesse a rota: http://localhost:8080/usuario/cadastrar
2. informe o nome, email, cpf e cnpj 
3. fique atento as respostas da API, pois o sistema não aceita nome de usuário e e-mail duplicados  


![exemplo de cadastro via Postman parte 1](https://i.imgur.com/fIT48OZ.jpg)

![exemplo de cadastro via Postman parte 1](https://i.imgur.com/Qb6LAMI.jpg)

## Login 
![exemplo de login via Postman](https://i.imgur.com/NCz2Kdr.jpg)

## Passando o token

Observe o Header, ele tem o campo Authorization e o valor dele é o token obtido no login 

![exemplo de passagem de Token via Postman](https://i.imgur.com/xuD1LbY.jpg)
 
## Swagger 
para saber as demais rotas do projeto utilize o Swagger através da URL: http://localhost:8080/swagger-ui.html#/

# Como executar o projeto? 
Importante dizer que com excessão das rotas de cadastro e login, todas precisam de passar um cabeçalho com o token referente ao 'Authorization', logo é necessário que você cadastre um usário e depois faça o login para que assim tenha acesso a ferramenta 

Caso não saiba como executar uma aplicação Spring Boot com Maven ou um executável Jar clique [aqui](https://www.codeflow.site/pt/article/spring-boot-run-maven-vs-executable-jar)

# Tecnologias usadas no projeto até o momento?

 1. [Spring Tools 4](https://spring.io/tools) integrado a IDE [Eclipse](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-12/R/eclipse-inst-jre-win64.exe)
 
 2. [Spring Boot](https://start.spring.io/) para a configuração do projeto, ele foi usado com o auxílio do Spring Tools 4.
 
 3. [Spring MVC](https://blog.algaworks.com/spring-mvc/) para saber mais 
 
 4. [Maven](https://maven.apache.org/)
 
 6. [Spring Data JPA](https://spring.io/projects/spring-data)
 7. [Spring Security](https://spring.io/projects/spring-security)
 8. [Swagger](https://swagger.io/)
 9. [Postman](https://www.postman.com/)
