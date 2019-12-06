package com.example.tessiedesign;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.tessiedesign.R;
import com.example.tessiedesign.RequestAdapter;
import com.example.tessiedesign.database.DatabaseTask;
import com.example.tessiedesign.database.Request;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RequestLogActivity extends AppCompatActivity implements DatabaseTask.ICallbackReceiverDB {
    private RecyclerView recyclerView;
    private RequestAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reycler_activity);

        DatabaseTask task = new DatabaseTask(this, this);
        task.execute();

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new RequestAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void getAllRequests(List<Request> requestList) {
        adapter.setRequests(requestList);
    }
}
