/*
 * Copyright 2017 Wcscent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wcscent.commons.event;

import java.util.*;

/**
 * The {@code DomainEvent} class // todo document
 *
 * @author hanpengfei
 */
public final class DomainEventPublisher implements EventPublisher<DomainEvent, DomainEventSubscriber> {

    private static final ThreadLocal<DomainEventPublisher> INSTANCES =
            ThreadLocal.withInitial(DomainEventPublisher::new);

    private Map<SubscriberEntry, List<DomainEventSubscriber>> subscribers = new HashMap<>();
    private boolean publishing = false;

    private DomainEventPublisher() {
        super();
    }

    public static DomainEventPublisher getInstance() {
        return INSTANCES.get();
    }

    @Override
    public void publish(DomainEvent event) {
        if (isPublishing()) {
            publish(event);
        }

        try {
            setPublishing(true);

            if (!getSubscribers().isEmpty()) {

                final String scope = event.scope();
                SubscriberEntry entry;

                // publish to current event's subscribers
                entry = new SubscriberEntry(event.getClass(), scope);
                doPublish(entry, event);

                // publish to domain event's subscribers
                entry = new SubscriberEntry(DomainEvent.class, scope);
                doPublish(entry, event);

                // publish to event's subscribers
                entry = new SubscriberEntry(Event.class, scope);
                doPublish(entry, event);
            }

        } finally {
            setPublishing(false);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void subscribe(DomainEventSubscriber subscriber) {
        if (isPublishing()) {
            subscribe(subscriber);
        }

        try {
            setPublishing(true);

            final Class<? extends DomainEvent> subscribeToEvent =
                    subscriber.subscribeToClass();
            final String scope = subscriber.scope();

            SubscriberEntry entry = new SubscriberEntry(subscribeToEvent, scope);

            List<DomainEventSubscriber> registeredSubscribers = getSubscribers().get(entry);

            if (registeredSubscribers == null) {
                registeredSubscribers = new LinkedList<>();
            }

            registeredSubscribers.add(subscriber);

            subscribers.put(entry, registeredSubscribers);

        } finally {
            setPublishing(false);
        }
    }

    @Override
    public void reset() {
        if (isPublishing()) {
            reset();
        }

        try {
            setPublishing(true);
            subscribers.clear();
        } finally {
            setPublishing(false);
        }
    }

    public Map<SubscriberEntry, List<DomainEventSubscriber>> getSubscribers() {
        return Collections.unmodifiableMap(subscribers);
    }

    private boolean isPublishing() {
        return publishing;
    }

    private void setPublishing(boolean publishing) {
        this.publishing = publishing;
    }

    private void doPublish(SubscriberEntry entry, DomainEvent event) {
        assert entry != null : "Domain event subscriber entry shouldn't be null.";
        assert event != null : "Domain event should't be null";

        List<DomainEventSubscriber> subscribers;

        subscribers = getSubscribers().get(entry);
        doPublish(subscribers, event);
    }

    @SuppressWarnings("unchecked")
    private void doPublish(List<DomainEventSubscriber> subscribers,
                           DomainEvent event) {
        if (subscribers == null || subscribers.isEmpty()) {
            return;
        }
        for (DomainEventSubscriber subscriber : subscribers) {
            subscriber.handle(event);
        }
    }

    private class SubscriberEntry {

        private Class<? extends Event> eventClass;
        private String scope;

        SubscriberEntry(Class<? extends Event> eventClass, String scope) {
            setEventClass(eventClass);
            setScope(scope);
        }

        private void setEventClass(Class<? extends Event> eventClass) {
            assert eventClass != null : "Event class shouldn't be null";
            this.eventClass = eventClass;
        }

        private void setScope(String scope) {
            this.scope = scope;
        }

        @Override
        public boolean equals(Object o) {
            // todo 此处会造成同一域中不能存在多个订阅者同时订阅同一事件
            if (o == this) {
                return true;
            }
            if (!(o instanceof SubscriberEntry)) {
                return false;
            }

            SubscriberEntry entry = (SubscriberEntry) o;
            return eventClass.equals(entry.eventClass)
                    && scope.equals(entry.scope);
        }

        @Override
        public int hashCode() {
            return (233 * 1470) + eventClass.hashCode() + scope.hashCode();
        }
    }
}
