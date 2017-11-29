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

package com.wcscent.commons.model;

/**
 * The class {@code Enablement} represent implementor can be
 * set to enable and disable.
 *
 * @author hanpengfei
 */
public interface Enablement {

    /**
     * Implementor is enable or disable.
     *
     * @return true, implementor is enable; false, implementor is disable
     */
    boolean isEnable();

    /**
     * Make implementor to enable.
     */
    void enable();

    /**
     * Make implementor to disable.
     *
     * @param reason why want make implementor to disable.
     */
    void disable(String reason);
}