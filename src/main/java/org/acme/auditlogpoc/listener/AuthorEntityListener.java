package org.acme.auditlogpoc.listener;

import lombok.extern.slf4j.Slf4j;
import org.acme.auditlogpoc.AuthorDto;
import org.acme.auditlogpoc.event.EntityUpdatedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class AuthorEntityListener {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendUpdateEvent(EntityUpdatedEvent<AuthorDto> event) {
        log.info("sendUpdateEvent:{}", event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendAuditEvent(EntityUpdatedEvent<AuthorDto> event) {
        log.info("sendAuditEvent:{}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void sendUpdateFailedEvent(EntityUpdatedEvent<AuthorDto> event) {
        log.info("sendUpdateFailedEvent:{}", event);
    }
}
