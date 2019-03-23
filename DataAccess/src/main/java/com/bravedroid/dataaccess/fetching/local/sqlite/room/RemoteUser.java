package com.bravedroid.dataaccess.fetching.local.sqlite.room;

import androidx.room.Entity;

@Entity
public class RemoteUser extends User {
    boolean hasVPN;
}
