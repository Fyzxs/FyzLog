/*
 * Copyright (c) 2016 Fyzxs. MIT License
 */

package com.fyzxs.android.log;

import com.google.common.base.Preconditions;

import java.util.Locale;

//Understands how to format the log params
/* package */ abstract class MessageFormatter {
    /* package */ static MessageFormatter SYSTEM_OUT = new MessageFormatter() {

        @Override
        public void log(final AndroidLogger androidLogger, final LogLevel activeLevel, final String msgFormat, final Object... args) {
            Preconditions.checkNotNull(msgFormat, "FyzLog message can not be null");
            System.out.println(buildMessage(androidLogger, activeLevel, msgFormat, args).toString());
        }

        private StringBuilder buildMessage(final AndroidLogger androidLogger, final LogLevel activeLevel, final String msgFormat, final Object[] args) {
            final StackTraceElement frame = getCallingStackTraceElement();
            return createPrefix(androidLogger, activeLevel)
                    .append(createTag(frame))
                    .append(" ")
                    .append(createMessage(frame, String.format(Locale.US, msgFormat, args)));
        }

        private StringBuilder createPrefix(final AndroidLogger androidLogger, final LogLevel activeLevel) {
            final StringBuilder sb = new StringBuilder();
            androidLogger.writeTag(sb);
            sb.append("@");
            activeLevel.writeTag(sb);
            sb.append("/");
            return sb;
        }
    };

    //Understands how to format for android log
    /* package */ static MessageFormatter ANDROID_LOG = new MessageFormatter() {
        @Override
        public void log(final AndroidLogger androidLogger, final LogLevel activeLevel, final String msgFormat, final Object... args) {
            if (msgFormat == null || androidLogger.isLoggable(activeLevel)) { return; }

            final StackTraceElement frame = getCallingStackTraceElement();
            final String tag = createTag(frame);
            final String msg = String.format(Locale.US, msgFormat, args);
            final String message = createMessage(frame, msg);
            androidLogger.log(tag, message);
        }
    };

    /* package */ abstract void log(final AndroidLogger androidLogger, final LogLevel activeLevel, final String msgFormat, final Object... args);

    private static String createTag(final StackTraceElement frame) {
        final String fullClassName = frame.getClassName();
        final String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
        return "FYZ:" + className;
    }
    private static String createMessage(final StackTraceElement frame, final String msg) {
        return String.format(Locale.US,
                             "[%s] %s : %s",
                             Thread.currentThread().getName(),
                             frame.getMethodName(),
                             msg);
    }

    private static StackTraceElement getCallingStackTraceElement() {
        boolean hitLogger = false;
        for (final StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            final boolean isLogger = ste.getClassName().startsWith(FyzLog.class.getName());
            hitLogger = hitLogger || isLogger;
            if (hitLogger && !isLogger) {
                return ste;
            }
        }
        return new StackTraceElement(FyzLog.class.getName(),
                                     "getCallingStackTraceElement",
                                     null,
                                     -1);
    }
}