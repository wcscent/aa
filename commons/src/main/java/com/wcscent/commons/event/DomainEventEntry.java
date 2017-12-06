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
public class DomainEventEntry extends EventEntry {

    private String domain;

    public DomainEventEntry(Class<? extends Event> eventClass, String domain) {
        super(eventClass);
        setDomain(domain);
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DomainEventEntry)) {
            return false;
        }

        DomainEventEntry eventEntry = (DomainEventEntry) o;
        return getEventClass().equals(eventEntry.getEventClass())
            && getDomain().equals(eventEntry.getDomain());
    }

    @Override
    public int hashCode() {
        return (205 * 1654)
            + getEventClass().hashCode()
            + getDomain().hashCode();
    }

    private void setDomain(String domain) {
        if (domain == null) {
            throw new IllegalArgumentException("Must specific domain.");
        }
        this.domain = domain;
    }
}
