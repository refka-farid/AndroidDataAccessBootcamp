package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import android.content.Context;
import androidx.test.InstrumentationRegistry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class AppDatabaseTest {
    private AppDatabase SUT;

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        SUT = AppDatabase.Accessor.getInstanceForTest(appContext);
    }

    @After
    public void tearDown() {
    }


    @Test
    public void insertUserTest() {
        List<User> users = SUT.userDao().getAll();
        Assert.assertThat(users.size(), is(equalTo(0)));

        User user1 = createUser("houliya", "Jawher");
        User user2 = createUser("karim", "Jawher");
        SUT.userDao().insertAll(user1, user2);

        users = SUT.userDao().getAll();
        Assert.assertThat(users.size(), is(equalTo(2)));
    }

    private User createUser(String firstName, String lastName) {
        User user = new User();
        user.uid = UUID.randomUUID().toString();
        user.firstName = firstName;
        user.lastName = lastName;
        return user;
    }
}