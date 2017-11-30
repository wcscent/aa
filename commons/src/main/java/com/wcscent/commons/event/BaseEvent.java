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

import java.util.Date;

/**
 * The class {@code BaseEvent}.
 * This abstract class only is {@code Event}'s default implement.
 * Should be used to an new event.
 *
 * @author hanpengfei
 * @see Event
 * @see DomainEvent
 */
public abstract class BaseEvent implements Event {

    private static final long serialVersionUID = 6120774456808911390L;

    private int version;
    private Date occurredOn;

    protected BaseEvent() {
        this.version = 1;
        this.occurredOn = new Date();
    }

    @Override
    public int version() {
        return version;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }
}
