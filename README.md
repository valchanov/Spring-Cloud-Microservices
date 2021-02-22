# Spring-Cloud-Microservices

Used technologies:
- Spring Config server for dynamic configuration
- Netflix Eureka server for discover/register microservices
- Netflix Zuul for Proxy/Gateway/Edge Server to avoid the need to manage CORS and services' authentication concerns
- Ribbon for client's side Load balancing
- Hystrix Dashboard for monitoring services
- Hystrix for Circuit breaker pattern, Fallback pattern

## Project Architecture
![](./img.png?raw=true "Project Architecture")

- **config-service**: a module that uses Spring Cloud Config Server for dynamically running configuration server. Configurations are set in application-properties folder.
- **discovery-service**: a module that uses Spring Cloud Netflix Eureka as an embedded discovery/register server.
- **gateway-service**: a module that Spring Cloud Netflix Zuul for running Spring Boot application that acts as a proxy/gateway in our architecture.
- **book-info-service**: a microservice that allows to perform some basic CRUD operation on in-memory repository (H2)
- **book-rating-service**: a microservice that allows to perform some basic CRUD operation on in-memory repository (H2)
- **book-catalog-service**: a microservice that communicates with book-rating-service and book-info-service. 

## TODO:
- Add heartbeat ping -> spring-boot-starter-actuator and GET localhost:8080/health
- Add Spring Seurity OAuth2

## Running the Project
Copy the appliction-config folder to c:\Users{username}\ on Windows or /home/{username}/ on *nix. Then open a git bash terminal in application-config and run:

$ git init

$ git add .

$ git commit -m "Init commit"

start the config server

start the discovery server

start all the other servers in any order (gateway, book-info, book-catalog, book-rating)

