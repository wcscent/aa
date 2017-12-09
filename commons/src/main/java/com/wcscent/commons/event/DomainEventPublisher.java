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

import java.util.LinkedList;
import java.util.List;

/**
 * The {@code DomainEvent} class
 *
 * @author hanpengfei
 */
public final class DomainEventPublisher
    extends AbstractEventPublisher<DomainEvent, EventSubscriber> {

    private static final ThreadLocal<DomainEventPublisher> INSTANCES =
        ThreadLocal.withInitial(DomainEventPublisher::new);

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

            if (!allSubscribers().isEmpty()) {

                final Class<? extends Event> eventClass = event.getClass();
                String domain = event.domain();

                EventEntry entry;

                if (eventClass != DomainEvent.class) {
                    entry = new DomainEventEntry(eventClass, domain);
                    doPublish(entry, event);
                }

                entry = new DomainEventEntry(DomainEvent.class, domain);
                doPublish(entry, event);

                publish2RootEvent(event);
            }
        } finally {
            setPublishing(false);
        }
    }

    @Override
    public void subscribe(EventSubscriber subscriber) {
        if (isPublishing()) {
            subscribe(subscriber);
        }

        try {
            setPublishing(true);

            @SuppressWarnings("unchecked") final Class<? extends DomainEvent>
                subscribeToEvent = subscriber.subscribe2Class();

            String scope = null;

            if (isDomainEvent(subscribeToEvent)) {
                DomainEventSubscriber domainEventSubscriber =
                    (DomainEventSubscriber) subscriber;
                scope = domainEventSubscriber.domain();
            }

            DomainEventEntry entry = new DomainEventEntry(subscribeToEvent,
                scope);

            List<EventSubscriber> registeredSubscribers = getSubscribers()
                .get(entry);

            if (registeredSubscribers == null) {
                registeredSubscribers = new LinkedList<>();
            }

            registeredSubscribers.add(subscriber);

            getSubscribers().put(entry, registeredSubscribers);

        } finally {
            setPublishing(false);
        }
    }

    private boolean isDomainEvent(Class clazz) {
        return clazz == DomainEvent.class
            || instanceOfDomainEvent(clazz.getClasses())
            || instanceOfDomainEvent(clazz.getInterfaces());
    }

    private boolean instanceOfDomainEvent(Class[] classes) {
        for (Class clazz : classes) {
            if (isDomainEvent(clazz)) {
                return true;
            }
        }
        return false;
    }

}
