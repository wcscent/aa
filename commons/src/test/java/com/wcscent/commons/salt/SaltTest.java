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

package com.wcscent.commons.salt;

import com.wcscent.commons.BaseTest;
import org.junit.After;
import org.junit.Test;

/**
 * @author hanpengfei
 */
public class SaltTest extends BaseTest {

    private Salt salt;
    private char separator = ':';
    private boolean throwExc = false;

    @Test
    public void randomSalt() {
        salt = Salt.generate(separator);
    }

    @Test
    public void randomSalt1() {
        int len = 100;

        salt = Salt.generate(len, separator);

        assertEquals(len, salt.getSalt().length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void randomSalt2() {
        throwExc = true;

        salt = Salt.generate(0, separator);
    }

    @After
    public void tearDown() {
        if (throwExc) return;

        char[] cs = salt.getSalt();
        String str = salt.getSaltAsString();

        assertTrue(cs.length > 0);
        assertFalse(str.contains("" + separator));
        assertEquals(String.valueOf(cs), str);
    }
}