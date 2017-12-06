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

import com.wcscent.commons.vertify.Verifiable;

import java.io.Serializable;

/**
 * @author hanpengfei
 */
public abstract class AbstractId
    implements Identifiable<String>, Verifiable, Serializable {

    private static final long serialVersionUID = -6757024589010218184L;

    private String src;

    public AbstractId(String src) {
        super();
        setSrc(src);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof AbstractId)) {
            return false;
        }

        AbstractId typedId = (AbstractId) o;
        return src.equals(typedId.src);
    }

    @Override
    public int hashCode() {
        return (hashOddValue() * hashPrimaryValue())
            + src.hashCode();
    }

    @Override
    public String id() {
        return src;
    }

    @Override
    public void verify() throws Exception {
        // default not implemented
    }

    /**
     * Implementor's hash odd value.
     *
     * @return 0, default value or an odd value of hash code.
     */
    protected abstract int hashOddValue();

    /**
     * Implementor's hash primary value.
     *
     * @return 0, default value or primary of hash code.
     */
    protected abstract int hashPrimaryValue();

    private void setSrc(String src) {
        if (src == null || src.trim().isEmpty()) {
            throw new IllegalArgumentException("Must specific id source.");
        }
        this.src = src;
    }
}
