# Aula 11 — DevOps com Spring Boot

Atividade prática com uma API REST, teste automatizado usando JUnit 5 e MockMvc, empacotamento Maven e integração contínua no GitHub Actions.

## Requisitos

- Java 21
- Maven 3.9 ou superior

## Executar os testes

```bash
mvn test
```

## Gerar e executar o JAR

```bash
mvn clean package
java -jar target/aula11devops-0.0.1-SNAPSHOT.jar
```

A API será iniciada em `http://localhost:8081`.

## Testar a API

Cadastrar um produto:

```bash
curl -X POST http://localhost:8081/produtos \
  -H "Content-Type: application/json" \
  -d '{"id":1,"nome":"Notebook","preco":3500}'
```

Listar os produtos cadastrados:

```bash
curl http://localhost:8081/produtos
```

Os dados ficam apenas na memória e são apagados quando a aplicação é encerrada. Isso é suficiente para demonstrar o teste da resposta HTTP, mas não representa persistência em banco de dados.

## Pipeline de integração contínua

O arquivo `.github/workflows/teste.yml` configura o GitHub Actions para:

1. baixar o código do repositório;
2. configurar o Java 21;
3. executar os testes e empacotar a aplicação com `mvn clean package`;
4. disponibilizar o JAR no artefato `aplicacao-springboot`.

## Respostas dos exercícios

### 1. Qual é a diferença entre `mvn test` e `mvn package`?

`mvn test` compila e executa os testes automatizados. `mvn package` percorre também as fases anteriores, inclusive os testes, e depois empacota a aplicação no formato definido pelo projeto — neste caso, um arquivo JAR.

### 2. Qual é a finalidade do arquivo `pom.xml`?

O `pom.xml` é o arquivo de configuração do Maven. Ele identifica o projeto e declara suas dependências, a versão do Java, plugins e regras de compilação, teste e empacotamento.

### 3. Por que os testes são executados antes do empacotamento?

Para impedir que uma versão com comportamento incorreto seja considerada válida e distribuída. Por padrão, se algum teste falhar, o Maven interrompe o processo e não conclui o empacotamento.

### 4. Qual é a função do arquivo JAR?

O JAR reúne as classes compiladas e os recursos da aplicação em um único pacote. No Spring Boot, ele também pode incluir as dependências e o servidor embutido, permitindo iniciar a aplicação com `java -jar`.

### 5. Como o GitHub Actions contribui para automatizar esse processo?

Ele executa o fluxo definido no arquivo YAML sempre que ocorre um evento configurado, como um `push` ou `pull request`. Assim, os testes e o empacotamento são repetidos de maneira automática e padronizada, e o JAR pode ser armazenado como artefato da execução.

### 6. Qual é a diferença entre gerar um JAR e realizar o deploy de uma aplicação?

Gerar o JAR significa criar o pacote executável da aplicação. Fazer deploy significa colocar esse pacote em um ambiente de execução, como um servidor ou serviço de nuvem, configurá-lo e iniciá-lo para que a aplicação fique disponível aos usuários. A pipeline desta atividade gera e armazena o JAR, mas não faz deploy.
