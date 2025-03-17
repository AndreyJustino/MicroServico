# Micro Serviço de Envio de E-mail

Este projeto consiste em um micro serviço de envio de e-mails utilizando **Spring Boot**. Ele é composto por duas APIs:

1. **API de Cadastro de Usuário**: Responsável pelo cadastro de usuários.
2. **API de Envio de E-mail**: Envia um e-mail de agradecimento ao usuário cadastrado.

A comunicação entre essas APIs é feita por meio do **RabbitMQ**, utilizando o **CloudAMQP** como broker de mensagens. Cada API possui seu próprio banco de dados **PostgreSQL**, gerenciado via **Docker**.

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **RabbitMQ (CloudAMQP)**
- **Docker e Docker Compose**

## Configuração

Para executar o projeto corretamente, é necessário configurar algumas credenciais no arquivo `application.properties` de cada API.

### Configurações Necessárias:

```properties
# Configuração do Gmail para envio de e-mails
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=SEU_EMAIL@gmail.com
spring.mail.password=SUA_SENHA_DE_APP
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Configuração do RabbitMQ (CloudAMQP)
spring.rabbitmq.addresses=URL_DA_INSTANCIA_CLOUDAMQP
```

> **Nota**: A senha do Gmail deve ser gerada como **senha de aplicativo**, pois senhas normais não são aceitas para autenticação SMTP.

## Como Rodar a Aplicação

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/AndreyJustino/MicroServico.git
   cd MicroServico
   ```

2. **Certifique-se de ter o Docker e o Docker Compose instalados.**

3. **Suba os containers do PostgreSQL:**
   ```sh
   docker-compose up -d
   ```

4. **Execute cada API separadamente:**
   ```sh
   mvn spring-boot:run
   ```

## Endpoints

### API de Cadastro de Usuário
- **`POST /user`** - Cadastra um novo usuário e envia uma mensagem para o RabbitMQ.

### API de Envio de E-mail
- **Ouve mensagens do RabbitMQ** e envia um e-mail de agradecimento para o usuário cadastrado.

## Contribuição

Se quiser contribuir com melhorias, fique à vontade para abrir um Pull Request ou reportar problemas na aba de Issues.

## Licença

Este projeto está licenciado sob a **MIT License**. Veja o arquivo `LICENSE` para mais detalhes.

---

Feito com ❤️ por AndreyJustino
