package com.a2340.shelterhelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    /**
     * test for context of the App
     * @throws Exception catches an exception
     */
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.a2340.shelterhelper", appContext.getPackageName());
    }

    /**
     * Test how registration handles null inputs
     * @throws Exception
     * @author sam
     */
    @Test
    public void register_TestNull() throws Exception {
        LocalUsers.register(null, new User("Name", "Uname", "password", false));
        LocalUsers.register(InstrumentationRegistry.getTargetContext(), null);
        assertEquals("No users should have been added", 0, LocalUsers.getAllUsers().size());
    }

    /**
     * Test if registration succeeds given valid inputs
     * @throws Exception
     * @author sam
     */
    @Test
    public void register_TestCorrect() throws Exception {
        LocalUsers.register(InstrumentationRegistry.getTargetContext(), new User("Name", "Uname", "password", false));
        assertEquals("A user should have been added", 1, LocalUsers.getAllUsers().size());
    }
}
