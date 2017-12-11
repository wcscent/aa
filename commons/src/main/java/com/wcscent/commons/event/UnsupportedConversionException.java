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

package com.wcscent.commons.event;

/**
 * The class {@code UnsupportedConversionException} be used when
 * {@code DomainEvent} cannot be convert to {@code ApplicationEvent}.
 *
 * @author hanpengfei
 * @see DomainEvent#toApplicationEvent()
 */
public class UnsupportedConversionException extends Exception {

    private static final long serialVersionUID = 8078016952831528022L;

    public UnsupportedConversionException() {
        super();
    }

    public UnsupportedConversionException(String message) {
        super(message);
    }

    public UnsupportedConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedConversionException(Throwable cause) {
        super(cause);
    }

    protected UnsupportedConversionException(String message,
                                             Throwable cause,
                                             boolean enableSuppression,
                                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
