package com.devSait.saitMediator;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class MarksAdapter extends FirestoreRecyclerAdapter<Marksheets, MarksAdapter.marksholder> {

    public MarksAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Marksheets> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MarksAdapter.marksholder holder, int position, @NonNull @NotNull Marksheets model) {
        holder.subjectname.setText(model.getSubjectName());
        holder.marks.setText(model.getMarks());
    }

    @NonNull
    @NotNull
    @Override
    public marksholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    public static class marksholder extends RecyclerView.ViewHolder{
        TextView subjectname,marks;
        public marksholder(@NonNull @NotNull View itemView) {
            super(itemView);
            subjectname=itemView.findViewById(R.id.subname);
            marks = itemView.findViewById(R.id.marks);
        }
    }
}
