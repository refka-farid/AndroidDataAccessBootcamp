package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.Date;
import java.util.List;

@Dao
public interface UserDao {
    ////////////////////////////////////////////////
    @Query("SELECT * FROM user WHERE uid == :uid")
    User getUserById(String uid);

    @Query("SELECT * FROM user WHERE first_name LIKE :name " +
            "OR last_name LIKE :name")
    public List<User> findUsersWithName(String name);

    /// @Query("SELECT * FROM user WHERE first_name LIKE '%:name%' " +
    ///         "OR last_name LIKE  '%:name%'")
    /// public List<User> searchByName(String name);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(String[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT first_name, last_name FROM User")
    public List<NameTuple> loadFullName();

    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    public List<NameTuple> loadUsersFromRegions(List<String> regions);

    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    public LiveData<List<NameTuple>> loadUsersFromRegionsSync(List<String> regions);


    @Query("SELECT * FROM books " +
            "INNER JOIN User ON uid = books.user_id " +
            "INNER JOIN language ON user.language_id = language.name " +
            "WHERE language.popularity== :popularity")
    public List<Book> findBooksByPopularityLanguage(int popularity);

    @Query("SELECT * FROM user WHERE birthday BETWEEN :from AND :to")
    List<User> findUsersBornBetweenDates(Date from, Date to);


    ////////////////////////////////////////////////
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll1(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll2(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOneUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAll4(User user);

    ////////////////////////////////////////////////
    @Update
    public int updateUsers(User... users);

    @Update
    public void updateUsers2(User... users);

    ////////////////////////////////////////////////
    @Delete
    void delete(User user);

    @Delete
    void deleteUsers(User... user);

    @Delete
    int delete2(User user);

    @Delete
    int deleteUsers2(User... user);

    @Query("DELETE FROM user")
    void deleteAll();
}
