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

/**
 * @author hanpengfei
 */
public class StringUtils {

    private static final int DEFAULT_RANDOM_LENGTH = 64;


    public static String random() {
        return random(DEFAULT_RANDOM_LENGTH);
    }

    public static String random(int len) {
        //noinspection ConfusingArgumentToVarargsMethod
        return random(len, null);
    }

    public static String random(int len, char... limits) {
        if (len <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        while (sb.length() < len) {
            char c = CharacterUtils.randomCharacter();
            if (!CharacterUtils.isSeparatorChars(c, limits))
                sb.append(c);
        }
        return sb.toString();
    }

}
