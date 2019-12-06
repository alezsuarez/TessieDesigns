package com.example.tessiedesign.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Request {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="request_id")
    public int request_id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "size")
    public String size;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "quantity")
    public String quantity;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "data")
    public String data;

    @ColumnInfo(name = "notes")
    public String notes;

}
