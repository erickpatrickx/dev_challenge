# Considerações do projeto

Por se tratar de uma arquitetura baseada em microserviços optei por utilizar as seguintes tecnologias; **Spring Boot + MongoDB + Spring Cloud ( Zuul e Eureka)** alem disto incorporei algumas outras coisas que julgo como importantes em um projeto de arquitetura distribuidas como:  **Autententicação baseada em JWT,  Documentação de API utilizando o Swagger 2 , testes unitarios** além da utilização do **Docker** para facilitar a implantação e o escalonamento do sistema.
    
### Estrutura do projeto ###

	├── devchalenge			#Maven pom project
	│   ├── discovery		#Maven module project para gerenciar o status e a localização dos Microservices
	│   ├── gateway			#Maven module microservice load balancing
	│   ├── auth		   		#Maven module microservice project AUth
	│   ├── empresa			#Maven module microservice project Empresa
	│   └── reclamação 	#Maven module microservice project Reclamação
	│
	└── docker-compose.yml	#Docker compose

### Installation - DOCKER

Faça o clone do projeto para seu computador acesse a pasta raiz do projeto via CMD e execute o seguinte comando.

Obs:

Você deve possuir o maven e o Docker instalados.

```sh
 mvn clean install -DskipTests
 docker-compose up
```
Após a conclusão do processo o  sistema podera ser acessado em:

- http://localhost:8762/auth/swagger-ui.html

		ADMIN - usuário: admin  senha: 123456
		USER     - usuário: user     senha: 123456


- http://localhost:8762/empresa/swagger-ui.html

- http://localhost:8762/reclamacao/swagger-ui.html


### Installation - DESENV

Faça o clone do projeto para seu computador garanta que o MongoDB esteja rodando, altere os caminhos para localhost nas properties (Obs. Melhoria a se fazer no sistema), neste momento você podera iniciar todos os microserviços individualmente e rodar seus testes unitarios que rodam diretamente em um banco de testes do MongoDB.



# dev_challenge


### Problem ###

We need to research about locales where consumer complains are made. That complains should have at least the attributes described bellow:

 - Title
 - Description
 - Locale
 - Company

Can you provide some services to ingest complains and get some data about its geolocation? For example, to find how many complains a specific company has in specific city?


### Recommendations ###
 - Use Restful instead Rest
 - Use microservice design if possible
 - Use a NoSql Database (if you use a database in your purpose)
 - We need to scale your services, decouple your modules if possible
 - Use devops mindset
 - Use your preferred language and patterns

### Definition Of Done ###
 - A repository with read access to laercio.filho@reclameaqui.com.br, michel@reclameaqui.com.br, guilherme.branco@reclameaqui.com.br, willian.miranda@reclameaqui.com.br ( feel free to choose your provider )
 - Documented, clean and testable/tested code
 - Documented strategy to deploy and run your code ( on cloud if possible )
