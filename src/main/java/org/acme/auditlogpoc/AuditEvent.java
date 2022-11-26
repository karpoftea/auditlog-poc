package org.acme.auditlogpoc;

import java.util.HashMap;
import java.util.Map;

public class AuditEvent {

    private String entityId;
    private String entityType;
    private String eventType;
    private Map<String, Object> payload = new HashMap<>();

    public AuditEvent(String entityId, String entityType, String eventType) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.eventType = eventType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "AuditEvent{" +
               "entityId='" + entityId + '\'' +
               ", entityType='" + entityType + '\'' +
               ", eventType='" + eventType + '\'' +
               ", payload=" + payload +
               '}';
    }
}
