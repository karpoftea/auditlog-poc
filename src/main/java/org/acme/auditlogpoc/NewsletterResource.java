package org.acme.auditlogpoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsletterResource {

    @Autowired
    ApplicationEventPublisher publisher;

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody NewsletterSubscription subscription) {
        AuditEvent auditEvent = new AuditEvent(null, "NEWSLETTER", "SUBSCRIBE");
        auditEvent.getPayload().put("email", subscription.getEmail());
        publisher.publishEvent(auditEvent);
    }
}
