Feature: Gerenciamento de Sensores
  Scenario: Criar um novo sensor
    Given que eu tenho os dados do sensor
    When eu envio uma requisição para criar o sensor
    Then o sensor deve ser criado com sucesso
    And a resposta deve conter os detalhes do sensor criado

  Scenario: Consultar um sensor por ID
    Given que existe um sensor com ID 1
    When eu envio uma requisição para consultar o sensor com ID 1
    Then a resposta deve conter os detalhes do sensor com ID 1

  Scenario: Deletar um sensor pelo ID
    Given que existe um sensor com ID 1
    When eu envio uma requisição para deletar o sensor com ID 1
    Then a resposta deve ter status 204