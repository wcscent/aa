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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hanpengfei
 */
public abstract class AbstractEventPublisher
    <T extends Event, S extends EventSubscriber>
    implements EventPublisher<T, S> {

    private Map<EventEntry, List<EventSubscriber>> subscribers = new
        HashMap<>();
    private boolean publishing = false;

    @Override
    public void reset() {
        if (isPublishing()) {
            reset();
        }

        try {
            setPublishing(true);
            getSubscribers().clear();
        } finally {
            setPublishing(false);
        }
    }

    protected void doPublish(EventEntry entry, T event) {
        assert entry != null : "Must specific entry.";
        assert event != null : "Must specific event.";

        List<EventSubscriber> registerSubscribers;

        registerSubscribers = subscribers.get(entry);
        doPublish(registerSubscribers, event);
    }

    protected void doPublish(List<EventSubscriber> subscribers, T event) {
        if (subscribers == null || subscribers.isEmpty()) {
            return;
        }
        for (EventSubscriber subscriber : subscribers) {
            //noinspection unchecked
            subscriber.handle(event);
        }
    }

    protected void publish2RootEvent(T event) {
        EventEntry entry = new EventEntry(Event.class);
        doPublish(entry, event);
    }

    protected Map<EventEntry, List<EventSubscriber>> getSubscribers() {
        return subscribers;
    }

    protected boolean isPublishing() {
        return publishing;
    }

    protected void setPublishing(boolean publishing) {
        this.publishing = publishing;
    }
}
