package org.acme.auditlogpoc.listener;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.acme.auditlogpoc.AuditEvent;
import org.acme.auditlogpoc.AuditLogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class AuditEventListener {

    @Autowired
    EntityManager entityManager;

    @EventListener
    @Transactional(propagation = Propagation.REQUIRED)
    public void onAuditEvent(AuditEvent event) {
        log.debug("audit event:" + event);
        try {
            AuditLogRecord record = new AuditLogRecord();
            record.setEventType(event.getEventType());
            record.setEntityType(event.getEntityType());
            record.setPayload(new ObjectMapper().writeValueAsString(event.getPayload()));
            entityManager.persist(record);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
