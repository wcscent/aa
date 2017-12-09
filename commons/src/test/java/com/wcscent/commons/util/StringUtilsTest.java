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

import com.wcscent.commons.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * @author hanpengfei
 */
public class StringUtilsTest extends BaseTest {

    private String str;

    @Test
    public void testRandom() {
        str = StringUtils.random();

        Assert.assertFalse(str.trim().isEmpty());
    }

    @Test
    public void testRandom1() {
        int len = new Random().nextInt(1000) + 1;

        str = StringUtils.random(len);

        assertEquals(str.length(), len);
    }

    @Test
    public void testRandom2() {
        int len = new Random().nextInt(1000) + 1;

        char[] chars = {
            'a', 'b', 'c'
        };

        str = StringUtils.random(len, chars);

        assertEquals(str.length(), len);
        assertFalse(contains(str, chars));
    }

    @After
    public void tearDown() {
        assertNotNull(str);
    }

    private boolean contains(String str, char... chars) {
        char[] cs = str.toCharArray();

        for (char c1 : cs)
            for (char c2 : chars)
                if (c1 == c2)
                    return true;
        return false;

    }
}