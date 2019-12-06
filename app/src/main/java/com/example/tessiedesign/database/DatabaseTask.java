package com.example.tessiedesign.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

public class DatabaseTask extends AsyncTask<Void, Void, List<Request>> {
    private  ICallbackReceiverDB receiverCallback;
    private Context context;
    private TessieDesignDB db;

    public DatabaseTask(Context ctx, ICallbackReceiverDB receiverCallback) {
        this.context = ctx;
        this.receiverCallback = receiverCallback;
        //Database Creation
        db = Room.databaseBuilder(context, TessieDesignDB.class, "TessieDesignDB").build();

    }

    @Override
    protected List<Request> doInBackground(Void... voids) {
        return db.requestDAO().consultRequests();
    }

    @Override
    protected void onPostExecute(List<Request> requests) {
        //Send the log of requests to UI
        receiverCallback.getAllRequests(requests);
    }

    public interface ICallbackReceiverDB {
        void getAllRequests(List<Request> requestList);
    }
}

