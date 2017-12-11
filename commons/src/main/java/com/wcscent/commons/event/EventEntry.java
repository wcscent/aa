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

/**
 * @author hanpengfei
 */
public class EventEntry {

    private Class<? extends Event> eventClass;

    public EventEntry(Class<? extends Event> eventClass) {
        setEventClass(eventClass);
    }

    public Class<? extends Event> getEventClass() {
        return eventClass;
    }

    private void setEventClass(Class<? extends Event> eventClass) {
        if (eventClass == null) {
            throw new IllegalArgumentException("Must specific event class.");
        }
        this.eventClass = eventClass;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof EventEntry && getEventClass().equals
            (((EventEntry) o).getEventClass());
    }

    @Override
    public int hashCode() {
        return (237 * 1045) + getEventClass().hashCode();
    }
}
