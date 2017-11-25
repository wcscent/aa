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
 * The {@code DomainEvent} is an {@code Event}'s subclass.
 * When one event occurred on one domain or it have limited scope,
 * this event should be published as an {@code DomainEvent}.
 * <p>
 * If an {@code DomainEvent} need to be published to other domain,
 * this event mat should be published as a new {@code Event},
 * and new event shouldn't be {@code DomainEvent}'s subclass.
 *
 * @author hanpengfei
 * @see Event
 */
public interface DomainEvent extends Event {

    /**
     * Current event's scope. This scope is limited.
     * As an agreement, current domain event only should be used at its owner
     * domain.
     * The scope may be current event's domain name or and private code.
     * The private code only be know on this domain.
     * <p>
     * When customer want custom an {@code DomainEvent}, may should verify the
     * event's scope is same as customer's domain or no, before carry out other
     * business code.
     * If {@code scope} is different, customer shouldn't do anything.
     *
     * @return current event's scope.
     */
    String scope();

    /**
     * Convert current event to an {@code ApplicationEvent}.
     * The method as an factory method, may return different {@code ApplicationEvent}
     * instance, when current event had different status.
     * <p>
     * But, in factory, all {@code Event} is an value object.
     * That is to say, when event be constructed, the return value's specific
     * type is be definite.
     * <p>
     * The {@code ApplicationEvent} may be custom at other domain or system.
     * If current event not supported be published outside current domain,
     * this method shouldn't be implement.
     * <p>
     * If current event be converted to an {@code ApplicationEvent},
     * the {@code ApplicationEvent}'s version must be 1.
     *
     * @return null, if current event not supported published outside current
     * domain, it's default value.
     * An {@code ApplicationEvent} instance what will be custom outside current
     * domain.
     * @see ApplicationEvent
     */
    default ApplicationEvent toApplicationEvent() {
        return null;
    }
}
