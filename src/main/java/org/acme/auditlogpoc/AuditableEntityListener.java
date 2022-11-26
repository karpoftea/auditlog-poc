package org.acme.auditlogpoc;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditableEntityListener {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostPersist
    void onPostPersist(Identifiable entity) {
        log.debug("AuditableEntityListener.onPostPersist(): " + entity);
        publisher.publishEvent(createAuditEvent(entity, "CREATE"));
    }

    @PostUpdate
    void onPostUpdate(Identifiable entity) {
        log.debug("AuditableEntityListener.onPostUpdate(): " + entity);
        publisher.publishEvent(createAuditEvent(entity, "UPDATE"));
    }

    @PostRemove
    void onPostRemove(Identifiable entity) {
        log.debug("AuditableEntityListener.onPostRemove(): " + entity);
        publisher.publishEvent(createAuditEvent(entity, "DELETE"));
    }

    private AuditEvent createAuditEvent(Identifiable entity, String CREATE) {
        AuditEvent event = new AuditEvent(String.valueOf(entity.getId()), "BOOK", CREATE);
        event.getPayload().put("id", entity.getId());
        return event;
    }
}
