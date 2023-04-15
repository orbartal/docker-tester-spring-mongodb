# docker-tester-spring-mongodb

### Description

A simple example of compose and 3 services: springboot server, redis db and an e2e tester.
- The docker compose contain 3 mongodb dockers that work as a single service.
- A mongo-express that act as a gui, web client, for the mongodb.
- A backend that I wrote in java spring that provide crud rest api. 
- A tester, also in java, that run e2e test on the backend with detail result.

### Use cases and spring profiles

There are currently 3 spring profiles that define the mongo db that the backend server will use:
1. test: use embeded mongodb docker run with [testcontainer](https://www.testcontainers.org). No changes are needed in the config. Good for auto testing.
2. dev: use a local mongo server. Fill in details for local DB in application-dev.properties. Good for manual testing locally.
3. comp: use a set of mongodb dockers that work together as a single service. Fill in detail in the compose file. Good for e2e testing with docker compose.

There are currently 4 mods to testing the backend:
1. Running integration test without running the real backend server. Using spring profile test.
2. Running the backend server as local app togther with a local mongodb. Using spring profile dev.
3. Running the backend server as a docker directly and connect to another independent mongodb service.
4. Running the backend server as a docker using compose. It uses the compose mongodb dockers.

### Instructions on how to build/run the backend locally 

How to build, test, run and use the project server

Download the project from its github [repo](https://github.com/orbartal/docker-tester-spring-mongodb)
You can downalod it as zip and extract it. Or use git clone.

Install java 17 from [oracle](https://www.oracle.com/java/technologies/downloads/#java17) 

Install maven, on any OS, using [baeldung](https://www.baeldung.com/install-maven-on-windows-linux-mac) guide. 

In the terminal (or cmd) cd into dir "..\docker-tester-spring-mongodb\backend" and run:

mvn clean install

mvn spring-boot:run

Open url in browser and use the [swagger-ui](http://localhost:8080/swagger-ui/index.html)

### Instructions on how to build/run a single docker 

Install docker on your host.

In the terminal (or cmd) cd into dir "..\docker-tester-spring-mongodb\backend" and run 3 commands:
- mvn clean install -Dmaven.test.skip=true
- docker build -t orbartal/docker-tester-spring-mongodb .
- docker run -p 8080:8080 orbartal/docker-tester-spring-mongodb -d 

- TODO: how to run mongodb locally

### Instructions on how to build and run a all dockers

- TODO: how to run the dockers without docker compose

#### Instructions on how to login into commander and manage redis tables

All the below details are define, and can be modify, in the ./docker-compose.yml file.

1 Open url in browser and use the [mongo-express](http://localhost:28081)

2 By defualt there is no need to login. But you can config a login for both mongodb and mongo-express.

3 Select the demodb in mongo-express

4 View all values

5 You can now create, modify or delete any of the item inside demodb.

### All urls

Remember that the ports number depend on the .env values use by docker-compose
- [[mongo-express-ui](http://localhost:28081/)
- [demo-backend-ui](http://localhost:8080/swagger-ui/index.html)
- [demo-tester-ui](http://localhost:8090/swagger-ui/index.html)