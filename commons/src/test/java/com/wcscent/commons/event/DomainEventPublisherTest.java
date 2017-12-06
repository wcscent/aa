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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author hanpengfei
 */
public class DomainEventPublisherTest extends Assert {

    private static final String SCOPE = "test";

    private DomainEventPublisher publisher;

    @Before
    public void setUp() {
        publisher = DomainEventPublisher.getInstance();
    }

    @Test
    public void getInstance() {
        assertNotNull(publisher);
    }

    @Test
    public void testPublish() {
        publishEvent();
    }

    @Test
    public void testSubscribe() {
        final boolean[] handled = {false};
        final String[] scope = new String[1];

        publisher.subscribe(new DomainEventSubscriber<DomainEventTest>() {
            @Override
            public String domain() {
                return SCOPE;
            }

            @Override
            public Class<DomainEventTest> subscribeToClass() {
                return DomainEventTest.class;
            }

            @Override
            public void handle(DomainEventTest event) {
                handled[0] = true;
                scope[0] = event.domain();
            }
        });

        publishEvent();

        assertTrue(handled[0]);
        assertEquals(scope[0], SCOPE);
    }

    @Test
    public void testReset() {
        int subscribersCount = 10;

        subscribeEvents(subscribersCount);

        assertEquals(publisher.allSubscribers().size(), subscribersCount);

        publisher.reset();

        assertTrue(publisher.allSubscribers().isEmpty());
    }

    private void publishEvent() {
        publisher.publish(new DomainEventTest(SCOPE));
    }

    private void subscribeEvent() {
        DomainEventSubscriber subscriber = new
            DomainEventSubscriber<DomainEventTest>() {
                @Override
                public String domain() {
                    return SCOPE;
                }

                @Override
                public Class<DomainEventTest> subscribeToClass() {
                    return DomainEventTest.class;
                }

                @Override
                public void handle(DomainEventTest event) {
                }
            };

        DomainEventPublisher.getInstance().subscribe(subscriber);
    }

    private void subscribeEvents(int count) {
        for (int i = 0; i < count; i++) {
            subscribeEvent();
        }
    }
}