CQRS Sample
==================================================

This sample code helps get you started with a CQRS + Event Sourcing Patterns

To know about CQRS: https://martinfowler.com/bliki/CQRS.html

To know about Event Sourcing: https://martinfowler.com/eaaDev/EventSourcing.html

Related technologies: 

* Kotlin: https://kotlinlang.org/docs/reference/
* Spring Boot: https://spring.io/projects/spring-boot#overview
* Axon Framework: https://axoniq.io/resources/architectural-concepts
* Docker: https://www.docker.com/resources/what-container
* Apache Kafka: https://kafka.apache.org/intro 
* Zookeeper: https://zookeeper.apache.org/ 
* MongoDB: https://www.mongodb.com/what-is-mongodb

What's Here
-----------

This sample includes:

* branch master - Normal spring boot application with servlet container.
* branch webflux (In progress) - Spring Boot Webflux with Netty server, more functional style (See https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html for details).
* branch distributed-command - This branch uses Netflix Eureka to distribute commands. 

* README.md - this file
* docker-compose.yml - this file is used by Docker Compose (https://docs.docker.com/compose/) to running Kafka, Zookeeper and Mongo
* pom.xml - this file is the Maven Project Object Model for the web service
* command-server - this directory contains your Kotlin Service Command source files
* query-server - this directory contains your Kotlin Service Query source files
* sample-core - this directory contains the shared source code between modules, command-server and query-server 
* service-discovery (branch distributed-command) - this directory contains your Service Discovery (Eureka) to enable distributed commands


Getting Started
---------------

To work on the sample code, you'll need to clone project's repository to your
local computer. If you haven't, do that first.

1. Install maven.  See https://maven.apache.org/install.html for details.

2. Install Docker (to run Kafka, Zookeeper and Mongo). See https://docs.docker.com/install/

3. Build the services.

        $ cd sample-cqrs
        $ mvn clean install

4. Run the Docker Compose.

        $ docker-compose up -d

5. Run the Eureka
        
        $ cd service-discovery 
        $ mvn spring-boot:run 
        
6. Run the Command Server
        
        $ cd command-server
        $ mvn spring-boot:run 

7. Run the Query Server
        
        $ cd query-server
        $ mvn spring-boot:run 

8. Send Command to create the sample data

   action types: 
    
    CREATE, 
                
    CREATE_AND_CANCEL (This type rollback status to CANCELED using Saga)



        $ curl -v -X POST \
            http://localhost:8084/command-sample/api/samples \
            -H 'Content-Type: application/json' \
            -d '{
            "id": 1234,
            "stuff": "Lorem Ipsum",
            "action": "CREATE"
          }'


9. Call the Query endpoint
        
        $ curl -v -X GET http://localhost:8085/query-sample/api/samples/1234  

