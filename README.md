# General
This is an auditlog PoC application. Main purpose of application is to show how it is possible to emit audit records
when different events occur (jpa-entity change, business-event occurred etc).
Application implemented using SpringBoot, Debezium and Kafka. To reliably register auditlog records 
[transactional outbox pattern](https://microservices.io/patterns/data/transactional-outbox.html) is implemented.

# Installation
For an installation proceed following steps:
```shell
# bootstrap environment
docker compose -f src/main/docker/docker-compose.yaml up --build

# build application
./gradlew clean build

# start application
java -jar build/libs/auditlog-poc-1.0-SNAPSHOT.jar

# register auditlog connector
curl -XPUT -H 'Content-type: application/json' \
 http://localhost:8083/connectors/auditlog-connector/config -d@src/main/connect/register-auditlog.json
```
, then generate some events using service REST-API and see results in auditlog Kafka topic. 
See [Common queries](#Common-queries) for details.

# Common queries

## Application REST-API
```shell

# create book
curl -v -XPOST -H 'Content-type: application/json' http://localhost:8080 -d '{"isbn": "42", "title": "My 1st book"}'

# get book
curl -v -XGET http://localhost:8080/1

# update book
curl -v -XPUT -H 'Content-type: application/json' http://localhost:8080/1 -d '{"isbn": "23", "title": "A book"}'

# delete book
curl -v -XDELETE http://localhost:8080/1

# create author
curl -v -XPOST -H 'Content-type: application/json' http://localhost:8080/authors -d '{"name": "Gogol"}'
```

## Kafka-Connect REST-API
```shell
# list connectors
curl -H "Accept:application/json" localhost:8083/connectors/

# register auditlog connector
curl -XPUT -H 'Content-type: application/json' \
 http://localhost:8083/connectors/auditlog-connector/config -d@src/main/connect/register-auditlog.json
 
# delete connector
curl -XDELETE http://localhost:8083/connectors/auditlog-connector
```

## KafkaCat
```shell
# consume events from auditlog topic
docker run -it --network=host edenhill/kcat:1.7.1 -b localhost -C -t auditlog -o beginning -f '\nKey (%K bytes): %k
  Value (%S bytes): %s
  Timestamp: %T
  Partition: %p
  Offset: %o
  Headers: %h\n'
```

## PostgreSQL
```shell
# list auditlog table
docker exec -it postgres psql -U 'books-svc' -d 'booksdb' -c 'select * from auditlog;'
```