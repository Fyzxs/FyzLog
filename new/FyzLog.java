/*
 * Copyright (c) 2016 Fyzxs. MIT License
 */

package com.fyzxs.android.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.fyzxs.android.BuildConfig;

//Understands how to handle being asked to log
public final class FyzLog {

    private static MessageFormatter ACTIVE_LOGGER = MessageFormatter.ANDROID_LOG;

    private static LogLevel ACTIVE_LEVEL = BuildConfig.DEBUG ? LogLevel.VERBOSE : LogLevel.WARN;

    @VisibleForTesting
    public static void logToSystem(){
        if(BuildConfig.DEBUG) { ACTIVE_LOGGER = MessageFormatter.SYSTEM_OUT; }
    }

    public static void v(@NonNull final String msg) { v(msg, (Object[]) null); }
    public static void v(@NonNull final String msgFormat, @Nullable final Object... args) {
        log(AndroidLogger.VERBOSE, msgFormat, args);
    }

    public static void d(@NonNull final String msg) { d(msg, (Object[]) null); }
    public static void d(@NonNull final String msgFormat, @Nullable final Object... args) {
        log(AndroidLogger.DEBUG, msgFormat, args);
    }

    public static void i(@NonNull final String msg) { i(msg, (Object[]) null); }
    public static void i(@NonNull final String msgFormat, @Nullable final Object... args) {
        log(AndroidLogger.INFO, msgFormat, args);
    }

    public static void w(@NonNull final String msg) { w(msg, (Object[]) null); }
    public static void w(@NonNull final String msgFormat, @Nullable final Object... args) {
        log(AndroidLogger.WARN, msgFormat, args);
    }

    public static void e(@NonNull final String msg) { e(msg, (Object[]) null); }
    public static void e(@NonNull final String msgFormat, final Object... args) {
        log(AndroidLogger.ERROR, msgFormat, args);
    }

    public static void wtf(@NonNull final String msg) { wtf(msg, (Object[]) null); }
    public static void wtf(@NonNull final String msgFormat, final Object... args) {
        log(AndroidLogger.ASSERT, msgFormat, args);
    }

    private static void log(@NonNull final AndroidLogger logger, @NonNull final String msgFormat, @Nullable final Object... args){
        ACTIVE_LOGGER.log(logger, ACTIVE_LEVEL, msgFormat, args);
    }
}
