/*
 * Copyright (c) 2016 Fyzxs. MIT License
 */

package com.fyzxs.android.log;

import android.util.Log;

//Understands the levels of log-ability
/* package */ final class LogLevel{
    /* package */ final static LogLevel VERBOSE = new LogLevel(Log.VERBOSE, "V");
    /* package */ final static LogLevel DEBUG = new LogLevel(Log.DEBUG, "D");
    /* package */ final static LogLevel INFO = new LogLevel(Log.INFO, "I");
    /* package */ final static LogLevel WARN = new LogLevel(Log.WARN, "W");
    /* package */ final static LogLevel ERROR = new LogLevel(Log.ERROR, "E");
    /* package */ final static LogLevel ASSERT = new LogLevel(Log.ASSERT, "A");

    private final int logLevel;
    private final String tag;

    private LogLevel(final int logLevel, final String tag){
        this.logLevel = logLevel;
        this.tag = tag;
    }

    /* package */ void writeTag(final StringBuilder sb){ sb.append(tag); }

    /* package */ boolean isLoggableAt(final LogLevel other) {
        return this.logLevel <= other.logLevel;
    }
}
