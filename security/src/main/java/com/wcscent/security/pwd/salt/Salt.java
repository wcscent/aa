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

package com.wcscent.security.pwd.salt;

import com.wcscent.commons.util.StringUtils;

/**
 * The hash value of salt.
 *
 * @author hanpengfei
 */
public final class Salt {

    private static final int DEFAULT_LENGTH = 64;

    public static Salt generate(char separator) {
        return generate(DEFAULT_LENGTH, separator);
    }

    public static Salt generate(int len, char separator) {
        if (len <= 0)
            throw new IllegalArgumentException(
                "The length of salt must longer than 0.");
        return new Salt(StringUtils.random(len, separator));
    }

    private char[] salt;

    public Salt(char[] salt) {
        setSalt(salt);
    }

    public Salt(String salt) {
        if (salt == null)
            throw new IllegalArgumentException("missing salt.");
        setSalt(salt.toCharArray());
    }

    public char[] getSalt() {
        return salt;
    }

    public String getSaltAsString() {
        return String.valueOf(salt);
    }

    private void setSalt(char[] salt) {
        if (salt == null || salt.length == 0)
            throw new IllegalArgumentException("Salt must be specified.");
        this.salt = salt;
    }
}
