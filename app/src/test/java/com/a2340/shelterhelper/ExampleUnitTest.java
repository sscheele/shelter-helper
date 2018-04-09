package com.a2340.shelterhelper;

import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    /*
    *Unit test for findByUsername() in LocalUsers.java
    *@author Pallavi Chetia
    */
    @Test
    public void findByUsername_NullTest() {
        User testUser = createUserforTest("null", "null");
        assertEquals("null", testUser.getUsername());
        assertEquals("null", testUser.getPassword());
    }

    private User createUserforTest(String username, String password) {
        return (new User("User1", username, password, true));

    }
     
    /*
    *Unit test for tryLogin() in LocalUsers.java
    *@author Aarti Thapar
    */
    private Shelter createShelterforTest(String address, String key, String name) {
        return (new Shelter("Peachtree", 30, 78.90, 56.00,
                "4072476879", 7891, "Shelter1",
                "notes1", "null", "Yes" ));
    }

    @Test
    public void tryLogin_NullTest() {
        User testUser = createUserforTest("null", "");
        assertEquals("null", testUser.getUsername());
        assertEquals("null", testUser.getPassword());

        User testUser2 = createUserforTest("User1", "123");
        assertEquals("123", testUser2.getPassword());
    }
     
    /*
    *Unit test for onChildChanged() in MainActivity.java
    *@author Sanskriti Rathi
    */
    @Nullable
    private java.util.List shelters;

    @Before
    public void setUp() {
        shelters = new java.util.ArrayList<>();
    }
    @After
    public void teardown() {
        shelters = null;
    }
    @Test
    public void onChildChanged_SizeTest() {
        assertEquals("An empty shelterList should have size zero", 0,
                shelters.size());
    }

}




