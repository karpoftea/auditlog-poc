# plan
-d create 1 db entity (crud)
-d create 1 rest resource that served by db entity
-d hibernate listener that listens create, update, delete events
-d emit audit event per entity change and create application event listener that listens for them
-d emit audit event for non-entity events
- add security context
-d create audit table
-d save audit events to audit table
- enrich audit events with user data
-d add kafka in docker-compose
-d add debezium outbox router to kafka topic
- celebrate victory

# enhancements
- make column auditlog.created numeric