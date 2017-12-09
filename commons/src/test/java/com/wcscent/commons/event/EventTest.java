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

import com.wcscent.commons.BaseTest;

/**
 * @author hanpengfei
 */
@SuppressWarnings({"unchecked", "WeakerAccess"})
public abstract class EventTest extends BaseTest {

    protected EventPublisher publisher;
    protected EventSubscriber subscriber;
    protected Event event;

    protected void publishEvent() {
        assert event != null : "Event must be specified.";
        assert publisher != null : "Subscriber must be specified.";
        publisher.publish(event);
    }

    protected void subscribeEvent() {
        assert subscriber != null : "Subscriber must be specified.";
        assert publisher != null : "Publisher must be specified.";
        publisher.subscribe(subscriber);
    }

    protected void subscribeEvents(int count) {
        for (int i = 0; i < count; i++) {
            subscribeEvent();
        }
    }
}
