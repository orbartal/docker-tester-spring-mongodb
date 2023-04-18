# docker-tester-spring-mongodb

### Description

A simple example of compose and 3 services: springboot server, mongodb db and an e2e tester.
- The docker compose contain 3 mongodb dockers that work as a single service.
- A mongo-express that act as a gui, web client, for the mongodb.
- A backend that I wrote in java spring that provide crud rest api. 
- A tester, also in java, that run e2e test on the backend with detail result.

### Use cases and spring profiles

There are currently 3 spring profiles that define the mongo db that the backend server will use:
1. test: use embeded mongodb docker run with [testcontainer](https://www.testcontainers.org). No changes are needed in the config. Good for auto testing.
2. dev: use a local mongo server. Fill in details for local DB in application-dev.properties. Good for manual testing locally.
3. comp: use a set of mongodb dockers that work together as a single service. Fill in detail in the compose file. Good for e2e testing.

There are currently 4 mods to testing the backend:
1. Running integration test without running the real backend server. Using spring profile test.
2. Running the backend server as local app togther with a local mongodb. Using spring profile dev.
3. Running the backend server as a docker directly and connect to another independent mongodb service.
4. Running the backend server as a docker using compose. It uses the compose mongodb dockers.

### Instructions on how to build the backend and run its test

1. Download the project from its github [repo](https://github.com/orbartal/docker-tester-spring-mongodb)
You can downalod it as zip and extract it. Or use git clone.

2. Install java 17 from [oracle](https://www.oracle.com/java/technologies/downloads/#java17) 

3. Install maven, on any OS, using [baeldung](https://www.baeldung.com/install-maven-on-windows-linux-mac) guide. 

4. Intsall docker and docker compose on your OS (Linux, Mac, Windows...).

Note: you can run the backend without docker but must have a docker to run its integration test and the docker-compose.

5. In the terminal (or cmd) cd into dir "..\docker-tester-spring-mongodb\backend" and run:
- mvn clean install  -DskipTests

6. Try to run the backend server. It should run but complain there is no mongodb.
- mvn spring-boot:run
- Open url in browser and use the [swagger-ui](http://localhost:8080/swagger-ui/index.html)
- Stop the server before the next stage.


7. You can now run one or more test from terminal using maven
- mvn test
- mvn surefire:test -Dtest=MongoClientTest
- mvn surefire:test -Dtest=DemoMongoRepositoryTest
- mvn surefire:test -Dtest=FirstDemoIntegrationTest
...

Note: see about maven test from terminal: [maven-surefire-plugin](https://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html).

### Instructions on how to run and use dev mod locally

1. Run mongodb docker for local usage. Open terminal/cmd/shell
- cd .\docker-tester-spring-mongodb\compose\t3
- docker compose up

2. Run the java spring projects
- Open your IDE (Eclipse/IntelliJ/NetBeans..) and run backend in debug mod
- Open your IDE (Eclipse/IntelliJ/NetBeans..) and run tester in debug mod

3. Open the we client and start using it
- [mongo-express-ui](http://localhost:28081/)
- [demo-backend-ui](http://localhost:8080/swagger-ui/index.html)
- [demo-tester-ui](http://localhost:8090/swagger-ui/index.html)

4. You can now put break point or even modify the java code in the backend and tester code to see how the code behave. 

Note: Instead of docker-compose you may also tey to install and run a local mongodb app on your OS.

### Instructions on how to build and run a all dockers from docker-compose

The project has 2 different docker-compose options you can use: t1 and t2.
They both have the same outcome but use different method to init the mongodb set:
- t1 use a temp docker, mongodb-rs-init, to run the init script.
- t2 use additional external script (bat in Windows and sh in Linux) ir order to run both docker-compose and the init script.

The first is in compose/t1. Open terminal and run 2 commands:
- cd .\docker-tester-spring-mongodb\compose\t1
- docker compose up

The second is in compose/t2. Open terminal and run 2 commands:
- cd .\docker-tester-spring-mongodb\compose\t1
- .\start.sh

Note: If your host OS is Windows and not Linux please use  .\start.bat instead of .\start.sh

#### Instructions on how to login into express ui and manage mongo collections

All the below details are define, and can be modify, in the ./docker-compose.yml file.

1 Open url in browser and use the [mongo-express](http://localhost:28081)

2 By defualt there is no need to login. But you can config a login for both mongodb and mongo-express.

3 Select the demodb in mongo-express

4 View all values

5 You can now create, modify or delete any of the item inside demodb.

### All urls

Remember that the ports number depend on the .env values use by docker-compose
- [mongo-express-ui](http://localhost:28081/)
- [demo-backend-ui](http://localhost:8080/swagger-ui/index.html)
- [demo-tester-ui](http://localhost:8090/swagger-ui/index.html)