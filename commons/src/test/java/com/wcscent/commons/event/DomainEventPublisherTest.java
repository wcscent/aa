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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author hanpengfei
 */
public class DomainEventPublisherTest extends DomainEventTest {

    private boolean handled = false;
    private String domain;

    @Before
    public void setUp() {
        publisher = DomainEventPublisher.getInstance();
        event = getEventCase();
        subscriber = getSubscriberCase();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(publisher);
    }

    @Test
    public void testPublish() {
        publishEvent();
    }

    @Test
    public void testSubscribe() {
        //noinspection unchecked
        publisher.subscribe(new DomainEventSubscriber4Test() {
            @Override
            public void handle(DomainEvent4Test event) {
                handled = true;
                domain = event.domain();
            }
        });

        publishEvent();

        assertTrue(handled);
        assertEquals(domain, DOMAIN);
    }

    @Test
    public void testReset() {
        int count = 10;
        subscribeEvents(count);
        assertEquals(publisher.allSubscribers().size(), count);

        publisher.reset();
        assertTrue(publisher.allSubscribers().isEmpty());
    }

    @After
    public void tearDown() {
        handled = false;
    }
}