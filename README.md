# Cities Api
O desafio foi desenvolver uma API Rest de consulta de cidades do Brasil com dados comparativos. Naveguei pelas boas práticas de Java e do Spring, o banco de dados Postgresql e criei um serviço para o cálculo de distância entre cidades.

## Requisitos
- Linux
- Git
- Java 8
- Docker
- IntelliJ Community
- Heroku CLI

## Licença
Distribuido sob a licença MIT License. Veja `LICENSE` para mais informações.

## DataBase

### Postgres
- [Postgres Docker Hub](https://hub.docker.com/_/postgres)

```shell script
docker run --name cities-db -d -p 5432:5432 -e POSTGRES_USER=postgres_user_city -e POSTGRES_PASSWORD=super_password -e POSTGRES_DB=cities postgres
```

### Populate
- [Faça o clone das tabelas](https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL)

Entre na pasta do repositório clonado:
>cd ~/workspace/sql-paises-estados-cidades/PostgreSQL <br>

Execute esse comando:
>docker run -it --rm --net=host -v $PWD:/tmp postgres /bin/bash <br>

Buscar as tabelas:
>psql -h localhost -U postgres_user_city cities -f /tmp/pais.sql <br>
>psql -h localhost -U postgres_user_city cities -f /tmp/estado.sql <br>
>psql -h localhost -U postgres_user_city cities -f /tmp/cidade.sql <br>
>psql -h localhost -U postgres_user_city cities <br>

Cria extensões para fazer query:
>CREATE EXTENSION cube; <br>
>CREATE EXTENSION earthdistance;

* [Postgres Earth distance](https://www.postgresql.org/docs/current/earthdistance.html)
* [earthdistance--1.0--1.1.sql](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.0--1.1.sql)
* [OPERATOR <@>](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.1.sql)
* [postgrescheatsheet](https://postgrescheatsheet.com/#/tables)
* [datatype-geometric](https://www.postgresql.org/docs/current/datatype-geometric.html)

### Acesso

```shell script
docker exec -it cities-db /bin/bash

psql -U postgres_user_city cities
```

### Query Earth Distance

Point
```roomsql
select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;
```

Cube
```roomsql
select earth_distance(
    ll_to_earth(-21.95840072631836,-47.98820114135742), 
    ll_to_earth(-22.01740074157715,-47.88600158691406)
) as distance;
```

## Spring Boot

- [https://start.spring.io/](https://start.spring.io/)
- Java 8 (no momento o Heroku só aceita projeto com o Java 8)
- Gradle Project
- Spring Boot 2.2.7 
- Jar
- Dependências: Spring Web, Spring Data JPA, PostgreSQL Driver

### Spring Data

* [jpa.query-methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)

### Properties

* [appendix-application-properties](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html)
* [jdbc-database-connectio](https://www.codejava.net/java-se/jdbc/jdbc-database-connection-url-for-common-databases)

### Types

* [JsonTypes](https://github.com/vladmihalcea/hibernate-types)
* [UserType](https://docs.jboss.org/hibernate/orm/3.5/api/org/hibernate/usertype/UserType.html)

## Heroku

* [DevCenter](https://devcenter.heroku.com/articles/getting-started-with-gradle-on-heroku)

## Code Quality

### PMD

+ https://pmd.github.io/pmd-6.8.0/index.html

### Checkstyle

+ https://checkstyle.org/

+ https://checkstyle.org/google_style.html

+ http://google.github.io/styleguide/javaguide.html

```shell script
wget https://raw.githubusercontent.com/checkstyle/checkstyle/master/src/main/resources/google_checks.xml
```

## Entendendo o código
`build.gradle`: build do projeto, é o local onde fica os plugins, as dependências, etc. <br>
`application.properties`: configuração dos databases. <br>
`CountryResource.java`: faz os métodos get dos países. <br>
`Country.java`: a entidade mapeia os países do banco de dados. <br>
`CountryRepository.java`: é o repository dos países. <br>
`StatesResource.java`: faz os métodos get dos estados. <br>
`State.java`: a entidade que mapeia os estados do banco de dados, transforma o Json em Integer. <br>
`StateRepository.java`: é o repository dos estados. <br>
`implementation 'com.vladmihalcea:hibernate-types-52:2.9.8'`: dependência adicionada no build.gradle para o Hibernate conseguir transformar o Json em Integer, porque o ddd dos estados estão em json. <br>
`PointType.java`: é uma classe que transforma o PointType em point do Spring. <br>
`DistanceResource.java`: faz os métodos da distância. <br>
`DistanceService.java`: esse serviço pega as requisições no controller, executar os processos no banco de dados e devolver o controller, com isso faz toda a regra de negócios para o cálculo da distância. O primeiro método calcula a distância por milhas e o segundo método que calcula a distância em cubes usando métros. <br>
`City.java`: a entidade mapeia as cidades dos países. <br>
`CityResource.java`: faz os métodos get das cidades. <br>
`CityRepository.java`: faz as querys do cálculo de distância por points e por cube. <br>
