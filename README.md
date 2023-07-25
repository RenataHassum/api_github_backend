### Renata Hassum - [Linkedin](https://www.linkedin.com/in/renataviottohassumdev) | renatahassum@gmail.com

# Sobre o projeto GitHubProxy [![NPM](https://img.shields.io/npm/l/react)](https://github.com/RenataHassum/githubproxy_backend/blob/main/LICENSE)

GitHubProxy é um projeto desenvolvido utilizando o framework Spring Boot, que atua como um proxy, consumindo a API externa do GitHub para obter informações relevantes sobre usuários e repositórios. Este projeto foi inspirado em um teste prático proposto a um colega de estudos em uma entrevista de emprego. Decidi implementá-lo como um projeto adicional de estudo.

Através desta API, é possível realizar consultas e obter informações como a lista de usuários, detalhes de um usuário e a lista de repositórios de um usuário específico. O projeto adota o padrão de arquitetura RESTful, com uma estrutura modular que separa as responsabilidades entre serviços e controladores. Além disso, utiliza o RestTemplate para realizar as requisições HTTP para a API do GitHub.

Também são incluídos testes automatizados, tanto unitários quanto de integração, que abrangem as camadas de serviço e controlador. Esses testes garantem a qualidade e a robustez do código, permitindo validar o correto funcionamento dos endpoints e assegurar que a API esteja em conformidade com as expectativas.

# Teste as requisições no Postman Collection
#### Projeto disponível na plataforma de hospedagem Railway - Requisições automáticas prontas para uso, sem necessidade de clonar o projeto
#### Siga o passo a passo:
1) Clique no botão "Run in Postman"
2) Acesse a opção "View collection"
3) Teste as requisições conforme orientações abaixo
   
      ## [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/22138191-bfc770be-e78f-4acf-9c47-ec369f9b393a?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D22138191-bfc770be-e78f-4acf-9c47-ec369f9b393a%26entityType%3Dcollection%26workspaceId%3Dcf0ac9b5-cd6d-4169-9407-6f00eb39b64f)

## GitHubProxy | Users endpoints
#### Não é necessário realizar a configuração de ambiente do projeto
`GET Users paged proxy`
- Retorna uma lista paginada de usuários do GitHub, permitindo a navegação entre as páginas de resultados. Ao final de cada página, haverá um link para a próxima página, exemplo:
  - "next": "http://localhost:8080/api/users?since=46"

`GET User details proxy`
- Retorna informações detalhadas de um usuário específico do GitHub

`GET User repositories proxy`
- Retorna uma lista completa com os repositórios de um usuário específico do GitHub 

# Tecnologias | Implantação em produção
- Java, Spring Boot, API REST, JPA, Maven, H2 Database, Git, JUnit5, Mockito e MockBean, Rest Template
- Implantação backend: Railway

# Técnicas | Competências
- Arquitetura em Camadas: Utiliza uma estrutura organizada em camadas, proporcionando separação clara de responsabilidades e facilitando a manutenibilidade do sistema.
- Web Services RESTful: Implementa web services RESTful, seguindo o padrão DTO para transferência de dados.
- Testes Automatizados: Compreende testes unitários e de integração que abrangem as camadas de serviço e controlador. Utiliza JUnit para testar repositórios, serviços e recursos, além de simular dependências com Mockito e MockBean, garantindo verificações precisas dos resultados.
- Realização de chamadas a APIs externas, tratamento de respostas e erros para integração eficiente com serviços externos utilizando RestTemplate.

## Padrão camadas
![Padrão camadas](https://github.com/RenataHassum/assets/blob/main/padrao_camadas.jpg?raw=true)

# Agradecimentos
<a href="https://devsuperior.com.br/cursos"><img align="center" height="16" alt="DevSuperior" src="https://devsuperior.com.br/_next/static/images/logo-white-10059e26f600604a7b5bd7782ed7550c.svg"></a> - Professor Nélio Alves

# Autora
### Renata Hassum - [Linkedin](https://www.linkedin.com/in/renataviottohassumdev) | renatahassum@gmail.com
Fico à disposição para qualquer esclarecimento, não hesite em me contatar
