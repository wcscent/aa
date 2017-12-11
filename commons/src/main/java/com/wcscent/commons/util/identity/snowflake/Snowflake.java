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

import java.util.Random;

/**
 * Twitter snowflake algorithm.
 *
 * @author hanpengfei
 */
public final class Snowflake implements IdentityGenerator {

    private static Snowflake instance;

    private static final int TIMESTAMP_LEN = 41;
    private static final int MACHINE_ID_LEN = 10;
    private static final int SERIAL_NO_LEN = 12;

    private static final long MAX_SERIAL_NO = 0xfff;
    private static final long MAX_MACHINE_ID = 0x3ff;

    private static long startStamp;

    static {
        startStamp = System.currentTimeMillis() - 1;
    }

    public static Snowflake getInstance(long machineId) {
        if (instance == null) {
            initialization(machineId);
        }
        return instance;
    }

    private static void initialization(long machineId) {
        if (instance == null) {
            synchronized (Snowflake.class) {
                if (instance == null) {
                    instance = new Snowflake(machineId);
                }
            }
        }
    }

    private long lastTimestamp;
    private long machineId;
    private long serialNo;

    private Snowflake(long machineId) {
        setMachineId(machineId);
        lastTimestamp = timestamp();
    }

    @Override
    public String generate() {
        return serialNo()
            + (machineId() << SERIAL_NO_LEN)
            + (lastTimestamp << (MACHINE_ID_LEN + SERIAL_NO_LEN))
            + "";
    }

    private long timestamp() {
        return System.currentTimeMillis() - startStamp;
    }

    private long machineId() {
        return machineId;
    }

    private long serialNo() {
        final long currentTimeMillis = timestamp();

        if (currentTimeMillis == lastTimestamp) {
            if ((serialNo & MAX_SERIAL_NO) == MAX_SERIAL_NO) {
                wait2nextMillis();
            }
        }

        if (currentTimeMillis > lastTimestamp) {
            serialNo = new Random().nextInt(10);
        }

        lastTimestamp = currentTimeMillis;
        return serialNo++;
    }

    private synchronized void wait2nextMillis() {
        while (timestamp() <= lastTimestamp) {
            try {
                this.wait(1);
            } catch (InterruptedException e) {
                wait2nextMillis();
            }
        }
    }

    private static long getStartStamp() {
        return startStamp;
    }

    /**
     * set current's machine identity,
     *
     * @param machineId the machine identity
     * @throws IllegalArgumentException if machineId smaller than one;
     *                                  machineId already be initialization.
     */
    private void setMachineId(long machineId) {
        if (machineId < 1 || machineId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException(String.format(
                "Machine id cannot smaller than 1 or bigger than %s",
                MAX_MACHINE_ID));
        }
        if (this.machineId > 0) {
            throw new IllegalArgumentException("Machine id already be init.");
        }
        this.machineId = machineId;
    }
}
