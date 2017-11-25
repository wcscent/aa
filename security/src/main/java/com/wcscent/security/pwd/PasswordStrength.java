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

package com.wcscent.security.pwd;

/**
 * @author hanpengfei
 */
public final class PasswordStrength {

    public static final PasswordStrength WEAK = new PasswordStrength(0);

    public static final PasswordStrength STRONG = new PasswordStrength(1);

    public static final PasswordStrength VERY_STRONG = new PasswordStrength(2);

    private int weight;

    private PasswordStrength(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public final boolean weakThan(PasswordStrength strength) {
        return weight < strength.weight;
    }

    public final boolean stongThan(PasswordStrength strength) {
        return !weakThan(strength);
    }
}
