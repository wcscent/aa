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

import java.util.Random;

/**
 * @author hanpengfei
 */
public class CharacterUtils {

    private static final Random RANDOM = new Random();

    public static char randomCharacter() {
        // generate char by int, max value is 126, min is 33;
        return (char) (RANDOM.nextInt(93) + 33);
    }

    public static boolean isSeparatorChars(char c, char... limits) {
        if (limits == null) {
            return false;
        }
        for (char cc : limits) {
            if (cc == c)
                return true;
        }
        return false;
    }
}
