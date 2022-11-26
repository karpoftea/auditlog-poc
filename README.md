# Common queries

## Application
```shell

# create
curl -v -XPOST -H 'Content-type: application/json' http://localhost:8080 -d '{"isbn": "42", "title": "My 1st book"}'

# get
curl -v -XGET http://localhost:8080/1

# update
curl -v -XPUT -H 'Content-type: application/json' http://localhost:8080/1 -d '{"isbn": "23", "title": "A book"}'

# delete
curl -v -XDELETE http://localhost:8080/1
```

## Kafka-Connect
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
docker run -it --network=host edenhill/kcat:1.7.1 -b localhost -C -t auditlog -o beginning -f '\nKey (%K bytes): %k
  Value (%S bytes): %s
  Timestamp: %T
  Partition: %p
  Offset: %o
  Headers: %h\n'
```

## PostgreSQL
```shell
docker exec -it postgres psql -U 'books-svc' -d 'booksdb' -c 'select * from auditlog;'
```