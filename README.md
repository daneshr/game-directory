# Directory of Gamers

In Directory of Gamers, we need to model gamers and their favorite games to auto match for a centralized app of all games.


### Assumptions
- Gamers: Each gamer can enrolling to the platform with their general information and 5 favorite games and their levels for each game. Gamer also have a default credit as 0 at enrollment time. 

- Credits: Each gamer has only one credit based on all games and this credit could be updated by calling API in the future.

- Search API: For searching API, geography and level can be optional. There's also an optional limit for number of results.

- Get the Gamer with maximum credits API: Since each gamer has one credit, this API will return the gamers with maximum credit for each game-level. 


### Technologies

I used Java, Spring Boot,Spring web, Spring Data JPA, Postgresql, H2, Docker,Flyway, Lombok and Swagger. 

 
### How to Run

The following Maven command, builds the artifact:

```
mvn clean install
```

The following Docker compose command will build and run the application:

```
docker-compose up
```
The UI would be available at the following address:

```
http://localhost:8081/
```

If you want to open this project in any IDE, you need to have Lombok plug-in installed.

##### Rest API Documentation:


```
http://localhost:8081/swagger-ui/index.html#/
```



