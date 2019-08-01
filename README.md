CQRS Sample
==================================================

This sample code helps get you started with a CQRS Pattern

To know about CQRS: https://martinfowler.com/bliki/CQRS.html

Related technologies: Kotlin, Spring Boot, Axon Framework, Kafka, Zookeeper and Mongo

What's Here
-----------

This sample includes:

* README.md - this file
* docker-compose.yml - this file is used by Docker Compose (https://docs.docker.com/compose/) to running Kafka, Zookeeper and Mongo
* pom.xml - this file is the Maven Project Object Model for the web service
* command-server - this directory contains your Kotlin Service Command source files
* query-server - this directory contains your Kotlin Service Query source files
* service-discovery - this directory contains your Kotlin Service Discovery (Eureka)


Getting Started
---------------

To work on the sample code, you'll need to clone your project's repository to your
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

        $ curl -v -X POST \
            http://localhost:8084/api/commands/samples \
            -H 'Content-Type: application/json' \
            -d '{
            "id": 1234,
            "stuff": "Lorem Ipsum"
          }'

9. Call the Query endpoint
        
        $ curl -v -X GET http://localhost:8085/api/queries/samples/1234  

