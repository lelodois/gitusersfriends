## Serviço Rest que retorna Json com os top 5 amigos de um usuário no git.

##### Com base nos usuários que favoritaram ou seguidores.

![Fluxo](https://github.com/lelodois/gitusersfriends/blob/master/back-fluxo.png)

### Pré-requisitos

- Java 8 
- Maven
- Docker (kafka, zookeper)

### Instalação

 - git clone https://github.com/lelodois/gitusersfriends.git

##### Instalação do kafka-zookeper no docker

```
  docker run -p 2181:2181 -p 9092:9092 -e ADVERTISED_HOST=127.0.0.1  johnnypark/kafka-zookeeper 
```

##### Crie os topicos
Localize o e entre na imagem do docker
```
  docker ps 
  docker exec -it {container-id} bash
```

Execute os scripts do arquivo abaixo
```
  https://github.com/lelodois/gitusersfriends/blob/master/create-topics.sh
```

##### Start da aplicação

```
  mvn install
  java -jar target/gitusersfriends-1.0.0.jar
  Acesse http://localhost:9090/swagger-ui.html#/
```

Execute as urls abaixo:

```
  http://localhost:9090/gitfriends/git/friends/ {gitLogin}
  http://localhost:9090/gitfriends/local/users/ {gitLogin}
  http://localhost:9090/gitfriends/local/top-friends/ {gitLogin}
```


## Tecnologias

* Java 8 (rest)
* Maven
* Kafka
* Spring boot
* Intellij

## Autor

* **Leo costa** - *Initial work* - [GitUsersFriends](https://github.com/lelodois/gitusersfriends)
