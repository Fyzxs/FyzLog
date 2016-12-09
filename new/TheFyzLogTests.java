/*
 * Copyright (c) 2016 Fyzxs. MIT License
 */

package com.fyzxs.android.log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;

@SuppressWarnings("ConstantConditions")
/*
Class is prefixed with 'The' to avoid a collision with the Logger's extraction of the calling method.
*/
public class TheFyzLogTests {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void systemOut() throws Exception {
        FyzLog.logToSystem();

        systemOutRule.clearLog();
        final Thread thread = new Thread(() -> {
            FyzLog.wtf("Non formatted!");
        });
        thread.setName("CustomName");
        thread.start();
        thread.join();
        assertThat(systemOutRule.getLog()).isNotEmpty();

    }

    @Test
    public void androidLog() throws Exception {

        try {
            FyzLog.wtf("Non formatted!");
            fail("Shouldn't get here");
        } catch (final RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo(
                    "Method wtf in android.util.Log not mocked. See http://g.co/androidstudio/not-mocked for details.");
        }
    }
}
