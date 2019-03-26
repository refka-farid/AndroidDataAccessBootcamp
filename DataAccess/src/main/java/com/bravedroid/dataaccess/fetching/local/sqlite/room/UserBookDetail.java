package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.room.DatabaseView;

@DatabaseView("SELECT books.user_Id, books.book_title, user.first_name FROM books " +
        " INNER JOIN user ON books.user_id= user.uid")
public class UserBookDetail {
    public String user_id;
    public String bookTitle;
    public String firstName;
}
