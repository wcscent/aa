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

package com.wcscent.commons.util.identity.snowflake;

import com.wcscent.commons.util.identity.IdentityGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hanpengfei
 */
public class SnowflakeFactory {

    private static final SnowflakeFactory INSTANCE = new SnowflakeFactory();
    private static final Map<Long, Snowflake> SNOWFLAKES = new HashMap<>();

    public static SnowflakeFactory getInstance() {
        return INSTANCE;
    }

    private SnowflakeFactory() {
    }

    public IdentityGenerator get(long machineId) {
        Snowflake snowflake = SNOWFLAKES.get(machineId);
        if (snowflake == null) {
            snowflake = Snowflake.getInstance(machineId);
            SNOWFLAKES.put(machineId, snowflake);
        }
        return snowflake;
    }

    public IdentityGenerator get() {
        return get(1);
    }
}
