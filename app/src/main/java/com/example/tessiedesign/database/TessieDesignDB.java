package com.example.tessiedesign.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Request.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class TessieDesignDB extends RoomDatabase {

    public abstract RequestDAO requestDAO();
}
