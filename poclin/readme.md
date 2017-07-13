
SWATLIN
=======

From now on assume `alias gw='./gradlew'`.

## Usage

* Clean: `gw clean`
* Build: `gw installDist`
* Rebuild: `gw clean installDist`
* Run: `gw run`
* Documentation: `gw all`
* Watch: `gw run`

## Development

To run the tests inside the IDE you need to start the `docker-compose.yaml` containers with:
`docker up -d` in the project root directory.

## Install

To generate and execute binaries run:

    gw installDist
    build/install/api/bin/api
    
To package application (intall directory) type: `gw distZip`

To build Docker images for the modules execute: `docker-compose --build`

## Documentation

The documentation can be generated running: `gw all`

The generated reports are in:

* `build/reports/tests`
* `build/reports/jacoco`

## Deployment

See modules' `dockerfile` and `docker-compose.yaml`.

# Architecture

* Input by HTTP and MQ
* Async service
* Query operations along the platform using a tracking ID
* Architecture: microservices
  - Health
  - Register/Discovery
  - Configuration
  - Log aggregation
  - Monitoring

Front + workers (MQ consumers, easy to scale, need circuit breaker!)
Front REST (HTTP/2)

# Platform

JVM
* A lot of libraries
* Inhouse existing libraries and expertise
* Integration with third party libraries

# Language

Any JVM one (can be mixed inside single service)
* Kotlin:
  - Async oriented
  - 100% Java compatibility
  - Multiple backends (JVM, JS, Native)

# Framework

Vert.x:
  - Polyglot
  - Micro services oriented
  - Well documented
  - Eclipse project
  - Supported (check Github stats)
  - Performance (see TechEmpower benchmark)
  - Scaling: actor model based in verticles and the event bus (distributed)
  - Integration (RabbitMQ, MongoDB, Consul...)
  - Metrics

* Template engines: allow pluggable support
* Documentation: API + site
* Platform:
    * Kubernetes
    * Packaging: docker
    * Pods
* Continuous Integration (building): Jenkins, Docker registry
* Servers
    * Templates storage
    * Messages storage
    * Messages BI
* Front end... SPA or Webapp (oauth)

* BI/BD denormalized tables or Elasticsearch (Kibana or Metabase)
