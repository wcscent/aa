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

package com.wcscent.commons.util;

import java.nio.ByteBuffer;

/**
 * @author hanpengfei
 */
public class ByteArrayUtils {

    public static byte[] valueOf(long value) {
        return valueOf(value, 8);
    }

    public static byte[] valueOf(long value, int capacity) {
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        buffer.putLong(value);
        return buffer.array();
    }
}
