package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AppDatabaseTest {
    private AppDatabase SUT;

    @Before
    public void setUp() {
        Context appContext = ApplicationProvider.getApplicationContext();
        SUT = AppDatabase.Accessor.getInstanceForTest(appContext);
    }

    @After
    public void tearDown() {
        SUT.bookDao().deleteAll();
        SUT.userDao().deleteAll();
        SUT.LanguageDao().deleteAll();
        SUT.close();
    }


    @Test
    public void insertUserTest() {
        List<User> users = SUT.userDao().getAll();
        assertThat(users.size(), is(equalTo(0)));

        User user1 = createUser("houliya", "Jawher");
        User user2 = createUser("karim", "Jawher");
        User user3 = createUser("maher", "byebye");

        SUT.userDao().insertAll(user1, user2);
        List<Long> longs = SUT.userDao().insertAll1(user1, user2);
        long[] longs1 = SUT.userDao().insertAll2(user1, user2);
        long result = SUT.userDao().insertOneUser(user1);
        assertThat(result, is(equalTo(7L)));
        //SUT.userDao().updateUsers(user3,user2);

        users = SUT.userDao().getAll();
        // Assert.assertThat(users.size(), is(equalTo(2)));
    }

    @Test
    public void UpdateTest() {
        User specialUser = createSpecialUser();
        SUT.userDao().insertOneUser(specialUser);
        specialUser.firstName = "miral";
        SUT.userDao().updateUsers(specialUser);
        User userById = SUT.userDao().getUserById("1111");
        assertThat(userById.firstName, is(equalTo("miral")));
        assertThat(userById.lastName, is(equalTo("mhiri")));
    }

    @Test
    public void notFoundUserTest() {
        User userById = SUT.userDao().getUserById("1111");
        List<User> all = SUT.userDao().getAll();
        assertThat(userById, is(equalTo(null)));
        assertThat(all.size(), is(equalTo(0)));
    }

    @Test
    public void findUserByNameTest() {
        SUT.userDao().insertAll(
                createUser("ahmed", "ben Mahmoud"),
                createUser("mohamed", "ahmed"),
                createUser("ameni", "shhaybi "),
                createUser("mefte7", "mesbe7")
        );

        List<User> ahmedLsit = SUT.userDao().findUsersWithName("ahmed");
        assertThat(ahmedLsit.size(), is(equalTo(2)));
    }

    @Test
    public void deleteTest() {
        User specialUser = createUser("wely", "policeman");
        SUT.userDao().insertOneUser(specialUser);
        User specialUserNew = createUser("ahmed", "sala7");
        SUT.userDao().insertOneUser(specialUserNew);
        User user3 = createUser("maher", "byebye");
        SUT.userDao().insertOneUser(user3);
        SUT.userDao().delete(user3);
        List<User> users = SUT.userDao().getAll();
        assertThat(users.size(), is(equalTo(2)));
    }

    @Test
    public void loadUsersFromRegionTest() {
        User userWithRegion1 = createUserWithRegion("adam", "smith", "america");
        SUT.userDao().insertOneUser(userWithRegion1);

        User userWithRegion2 = createUserWithRegion("salma", "kouper", "america");
        SUT.userDao().insertOneUser(userWithRegion2);

        User userWithRegion3 = createUserWithRegion("mariem", "lupi", "asia");
        SUT.userDao().insertOneUser(userWithRegion3);

        User userWithRegion4 = createUserWithRegion("sami", "wefi", "africa");
        SUT.userDao().insertOneUser(userWithRegion4);

        List<String> regions = new ArrayList<>();
        regions.add("africa");
        regions.add("asia");

        List<NameTuple> nameTuples = SUT.userDao().loadUsersFromRegions(regions);
        assertThat(nameTuples.size(), is(equalTo(2)));

    }

    @Test
    public void findBooksByPopularityLanguageTest() {
        Language french = createLanguage("French", 5);
        SUT.LanguageDao().insertOneLanguage(french);

        User specialUser = createSpecialUser();
        SUT.userDao().insertOneUser(specialUser);

        Book book = createBook("1111");
        SUT.bookDao().insertOneBook(book);


        List<Book> booksByPopularityLanguage = SUT.userDao().findBooksByPopularityLanguage(5);
        assertThat(booksByPopularityLanguage.size(), is(equalTo(1)));
    }

    @Test
    public void dateConverterTest() {
        User userWithDate = createUserWithDate();
        SUT.userDao().insertOneUser(userWithDate);
        User userById = SUT.userDao().getUserById("1111");
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dayToString = formatter.format(userById.birthday);
        assertThat(dayToString, is(equalTo("1999-08-05")));
    }

    private User createUser(String firstName, String lastName) {
        User user = new User();
        user.uid = UUID.randomUUID().toString();
        user.firstName = firstName;
        user.lastName = lastName;
        return user;
    }

    private User createUserWithRegion(String firstName, String lastName, String region) {
        User user = new User();
        user.uid = UUID.randomUUID().toString();
        user.firstName = firstName;
        user.lastName = lastName;
        user.region = region;
        return user;
    }

    private User createSpecialUser() {
        User user = new User();
        user.uid = "1111";
        user.firstName = "zeyneb";
        user.lastName = "mhiri";
        user.languageId = "French";
        return user;
    }

    private Book createBook(String userId) {
        Book book = new Book();
        book.userId = userId;
        return book;
    }

    private Language createLanguage(String name, int popularity) {
        Language language = new Language();
        language.name = name;
        language.popularity = popularity;
        return language;
    }

    private User createUserWithDate() {
        User user = new User();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("1999-08-05");
        } catch (ParseException ignored) {

        }
        user.uid = "1111";
        user.birthday = date;
        return user;
    }
}