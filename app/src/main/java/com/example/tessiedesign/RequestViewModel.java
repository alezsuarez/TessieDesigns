package com.example.tessiedesign;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.tessiedesign.database.Request;
import com.example.tessiedesign.database.TessieDesignDB;

import java.util.List;

public class RequestViewModel extends androidx.lifecycle.ViewModel {
    private LiveData<List<Request>> requestLog;

    public RequestViewModel() {
        super();
    }

    public LiveData<List<Request>> getRequests(Context ctx) {
        TessieDesignDB db = Room.databaseBuilder(ctx, TessieDesignDB.class, "StickerOrdersDB").build();
        requestLog = db.requestDAO().consultRequests();
        return requestLog;
    }

}
