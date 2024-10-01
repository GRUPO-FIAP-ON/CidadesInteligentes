# Cidades Inteligentes
Este projeto é uma aplicação voltada para a gestão de sensores e luminárias em ambientes urbanos, com o objetivo de melhorar a eficiência na gestão de recursos e a qualidade de vida nas cidades. A aplicação permite o monitoramento, controle e manutenção de sensores e luminárias de forma integrada.

# Tecnologias Utilizadas
Java: Linguagem de programação utilizada para o desenvolvimento do backend.
Spring Boot: Framework para construção de aplicações Java, que facilita a criação de APIs RESTful.
H2 Database: Banco de dados em memória para armazenamento de dados durante o desenvolvimento e testes.
Gradle: Ferramenta de gerenciamento de projetos e automação de builds.
Postman: Ferramenta utilizada para testar as APIs.

# Funcionalidades
# Sensores
Criar Sensor: Permite adicionar novos sensores ao sistema.
Listar Sensores: Recupera todos os sensores cadastrados.
Buscar Sensor por ID: Obtém informações de um sensor específico através de seu ID.
Atualizar Sensor: Modifica os dados de um sensor existente.
Excluir Sensor: Remove um sensor do sistema.

# Luminárias
Criar Luminária: Permite adicionar novas luminárias ao sistema.
Listar Luminárias: Recupera todas as luminárias cadastradas.
Buscar Luminária por ID: Obtém informações de uma luminária específica através de seu ID.
Atualizar Luminária: Modifica os dados de uma luminária existente.
Excluir Luminária: Remove uma luminária do sistema.

# Estrutura do Projeto
src/main/java/br/com/fiap/CidadesInteligentes/controller: Contém as classes de controle que gerenciam as requisições HTTP.
src/main/java/br/com/fiap/CidadesInteligentes/model: Contém as classes que representam os modelos de dados.
src/main/java/br/com/fiap/CidadesInteligentes/repository: Contém as interfaces de repositório para acesso ao banco de dados.
src/main/java/br/com/fiap/CidadesInteligentes/service: Contém as interfaces e implementações dos serviços que encapsulam a lógica de negócio.

# Como Executar o Projeto
1 - Clone o Repositório:
git clone https://github.com/seuusuario/CidadesInteligentes.git

2 - Navegue até o Diretório do Projeto:
cd CidadesInteligentes

3 - Execute o Projeto:
./gradlew bootRun

4 - Acesse o Console H2:
http://localhost:8080/h2-console.

Use as seguintes configurações:
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password

5 - Testar APIs com Postman:
Abra o Postman e faça requisições para os endpoints da aplicação:
GET /sensors para listar todos os sensores.
POST /sensors para criar um novo sensor.
GET /sensors/{id} para buscar um sensor por ID.
PUT /sensors/{id} para atualizar um sensor.
DELETE /sensors/{id} para excluir um sensor.
