package org.acme.auditlogpoc.event;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class EntityUpdatedEvent<T> implements ResolvableTypeProvider {

    private T source;
    private T target;

    public EntityUpdatedEvent(T source, T target) {
        this.source = source;
        this.target = target;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getSource()));
    }

    @Override
    public String toString() {
        return "EntityUpdatedEvent{" +
               "source=" + source +
               ", target=" + target +
               '}';
    }
}
