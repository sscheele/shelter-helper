package com.a2340.shelterhelper;

import android.support.annotation.Nullable;

//import com.google.firebase.database.DataSnapshot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("LawOfDemeter")
public class ExampleUnitTest {
    /**
     * test to check if addition is correct
     * @throws Exception catches exception
     */
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    /**
     * test to check is user is null
     * @author Pallavi Chetia
     * @throws Exception catches exception
     */
    @Test
    public void findByUsername_NullTest() throws Exception {
        User testUser = createUserforTest("null", "null");
        assertEquals("null", testUser.getUsername());
        assertEquals("null", testUser.getPassword());
    }

    private User createUserforTest(String username, String password) {
        return (new User("User1", username, password, true));

    }

    /**
     * test to check tryLogin method
     * @author Aarti Thapar
     * @throws Exception catches exception
     */



    @Test
    public void tryLogin_NullTest() throws Exception{
        User testUser = createUserforTest("null", "");
        assertEquals("null", testUser.getUsername());
        assertEquals("null", testUser.getPassword());

        User testUser2 = createUserforTest("User1", "123");
        assertEquals("123", testUser2.getPassword());
    }
     

    @Nullable
    private java.util.List shelters;
    /**
     * setUp of the test
     */
    @Before
    public void setUp() {
        shelters = new java.util.ArrayList<>();
    }
    /**
     * teardown of the test
     */
    @After
    public void teardown() {
        shelters = null;
    }
    /**
     * test to check for size of shelters
     * @author Sanskriti Rathi
     * @throws Exception catches exception
     */
    @Test
    public void onChildChanged_SizeTest() throws Exception {
        assertEquals("An empty shelterList should have size zero", 0,
                shelters.size());
    }

}




