/*
 * Copyright (c) 2016 Fyzxs. MIT License
 */

package com.fyzxs.android.log;


import android.util.Log;

//Understands how to log to android
/* package */ abstract class AndroidLogger {
    /* package */ final static AndroidLogger VERBOSE = new AndroidLogger(LogLevel.VERBOSE) {
        @Override
        public void log(final String tag, final String msg) { Log.v(tag, msg); }
    };
    /* package */ final static AndroidLogger DEBUG = new AndroidLogger(LogLevel.DEBUG) {
        @Override
        public void log(final String tag, final String msg) { Log.d(tag, msg); }
    };
    /* package */ final static AndroidLogger INFO = new AndroidLogger(LogLevel.INFO) {
        @Override
        public void log(final String tag, final String msg) { Log.i(tag, msg); }
    };
    /* package */ final static AndroidLogger WARN = new AndroidLogger(LogLevel.WARN) {
        @Override
        public void log(final String tag, final String msg) { Log.w(tag, msg); }
    };
    /* package */ final static AndroidLogger ERROR = new AndroidLogger(LogLevel.ERROR) {
        @Override
        public void log(final String tag, final String msg) { Log.e(tag, msg); }
    };
    /* package */ final static AndroidLogger ASSERT = new AndroidLogger(LogLevel.ASSERT) {
        @Override
        public void log(final String tag, final String msg) { Log.wtf(tag, msg); }
    };

    private final LogLevel logLevel;

    private AndroidLogger(final LogLevel logLevel) { this.logLevel = logLevel; }

    /* package */
    abstract void log(final String tag, final String msg);

    /* package */ boolean isLoggable(final LogLevel other) { return this.logLevel.isLoggableAt(other); }

    /* package */ void writeTag(final StringBuilder sb) { logLevel.writeTag(sb); }
}