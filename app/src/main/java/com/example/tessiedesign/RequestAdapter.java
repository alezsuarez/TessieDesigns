package com.example.tessiedesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tessiedesign.database.Request;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Request> requestList;

    public RequestAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context.getApplicationContext());
        View itemView = inflater.inflate(R.layout.recycler_item_view, parent, false);

        return new RequestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RequestViewHolder pHolder = (RequestViewHolder) holder;
        if(requestList != null) {
            Request request = requestList.get(position);

            pHolder.tvNoOrder.setText(Integer.toString(request.request_id));
            pHolder.tvName.setText(request.name);
            pHolder.tvSize.setText(request.size);
            pHolder.tvType.setText(request.type);
            pHolder.tvQty.setText(request.quantity);
            pHolder.tvImage.setText(request.image);
            pHolder.tvData.setText(request.data);
            pHolder.tvNotes.setText(request.notes);
        } else {
            pHolder.tvName.setText("NONE");
        }
    }

    @Override
    public int getItemCount() {
        if(this.requestList == null)
            return 0;
        return this.requestList.size();
    }

    class RequestViewHolder extends RecyclerView.ViewHolder{
        public TextView tvNoOrder ,tvName, tvSize, tvType,tvQty, tvImage,tvData, tvNotes;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNoOrder = itemView.findViewById(R.id.tvNoOrder);
            tvName = itemView.findViewById(R.id.tvName);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvType = itemView.findViewById(R.id.tvType);
            tvQty = itemView.findViewById(R.id.tvQty);
            tvImage = itemView.findViewById(R.id.tvImage);
            tvData = itemView.findViewById(R.id.tvData);
            tvNotes = itemView.findViewById(R.id.tvNotes);
        }
    }

    public void setRequests(List<Request> requests) {
        this.requestList = requests;
        notifyDataSetChanged();
    }
}
