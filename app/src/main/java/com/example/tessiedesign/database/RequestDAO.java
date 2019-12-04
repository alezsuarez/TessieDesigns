package com.example.tessiedesign.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface RequestDAO {
    @Insert(onConflict = IGNORE)
    public void insertRequest(Request... orders);

    @Query("SELECT * FROM request")
    public LiveData<List<Request>> consultRequests();
}
