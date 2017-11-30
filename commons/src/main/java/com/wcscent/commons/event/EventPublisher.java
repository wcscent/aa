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
 * Event publisher
 *
 * @author hanpengfei
 */
public interface EventPublisher<T extends Event, S extends EventSubscriber> {

    /**
     * Publish an event.
     *
     * @param event the event what will be publish
     */
    void publish(T event);

    /**
     * Subscribe event subscriber
     *
     * @param subscriber event subscribers
     */
    void subscribe(S subscriber);

    /**
     * Clear all subscribers to empty
     */
    void reset();
}
