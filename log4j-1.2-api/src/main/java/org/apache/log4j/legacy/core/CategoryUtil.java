/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.log4j.legacy.core;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.spi.LoggerContext;

/**
 * Delegates to {@code Logger} methods implemented by {@code log4j-core} if appropriate.
 */
public final class CategoryUtil {

    private static org.apache.logging.log4j.core.Logger asCore(final Logger logger) {
        return (org.apache.logging.log4j.core.Logger) logger;
    }

    private static <T> T get(final Logger logger, final Supplier<T> run, final T defaultValue) {
        return isCore(logger) ? run.get() : defaultValue;
    }

    /**
     * Delegates to {@link org.apache.logging.log4j.core.Logger#getAppenders()} if appropriate.
     *
     * @param logger The target logger.
     * @return A Map containing the Appender's name as the key and the Appender as the value.
     */
    public static Map<String, Appender> getAppenders(final Logger logger) {
        return get(logger, asCore(logger)::getAppenders, null);
    }

    /**
     * Delegates to {@link org.apache.logging.log4j.core.Logger#getFilters()} if appropriate.
     *
     * @param logger The target logger.
     * @return An Iterator over all the Filters associated with the Logger.
     */
    public static Iterator<Filter> getFilters(final Logger logger) {
        return get(logger, asCore(logger)::getFilters, null);
    }

    /**
     * Delegates to {@link org.apache.logging.log4j.core.Logger#getContext()} if appropriate.
     *
     * @param logger The target logger.
     * @return the LoggerContext.
     */
    public static LoggerContext getLoggerContext(final Logger logger) {
        return get(logger, asCore(logger)::getContext, null);
    }

    /**
     * Delegates to {@link org.apache.logging.log4j.core.Logger#getParent()} if appropriate.
     *
     * @param logger The target logger.
     * @return The parent Logger.
     */
    public static Logger getParent(final Logger logger) {
        return get(logger, asCore(logger)::getParent, null);
    }

    /**
     * Delegates to {@link org.apache.logging.log4j.core.Logger#isAdditive()} if appropriate.
     *
     * @param logger The target logger.
     * @return true if the associated LoggerConfig is additive, false otherwise.
     */
    public static boolean isAdditive(final Logger logger) {
        return get(logger, asCore(logger)::isAdditive, false);
    }

    private static boolean isCore(final Logger logger) {
        return logger instanceof org.apache.logging.log4j.core.Logger;
    }

    /**
     * Delegates to {@link org.apache.logging.log4j.core.Logger#setAdditive(boolean)} if appropriate.
     *
     * @param logger The target logger.
     * @param additive Boolean value to indicate whether the Logger is additive or not.
     */
    public static void setAdditivity(final Logger logger, final boolean additive) {
        if (isCore(logger)) {
            asCore(logger).setAdditive(additive);
        }
    }

    /**
     * Delegates to {@link org.apache.logging.log4j.core.Logger#setLevel(Level)} if appropriate.
     *
     * @param logger The target logger.
     * @param level The Level to use on this Logger, may be null.
     */
    public static void setLevel(final Logger logger, final Level level) {
        if (isCore(logger)) {
            asCore(logger).setLevel(level);
        }
    }

    private CategoryUtil() {
    }
}
