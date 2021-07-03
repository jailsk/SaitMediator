package com.devSait.saitMediator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class MyAdapter extends FirestoreRecyclerAdapter<Dynamic_lists, MyAdapter.AnnouncementHolder> {

    public MyAdapter(@NonNull FirestoreRecyclerOptions<Dynamic_lists> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MyAdapter.AnnouncementHolder holder, int position, @NonNull @NotNull Dynamic_lists model) {
        holder.name.setText(model.getName());
        holder.des.setText(model.getDescription());
        holder.author.setText(model.getAuthor());
        holder.date.setText(model.getTime());
    }

    @NonNull
    @Override
    public AnnouncementHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new AnnouncementHolder(v);
    }

    public static class AnnouncementHolder extends RecyclerView.ViewHolder {
            TextView name,des,author,date;
            public AnnouncementHolder(@NonNull @NotNull View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.name);
                des=itemView.findViewById(R.id.desc);
                author=itemView.findViewById(R.id.author);
                date=itemView.findViewById(R.id.date);
            }
        }
}