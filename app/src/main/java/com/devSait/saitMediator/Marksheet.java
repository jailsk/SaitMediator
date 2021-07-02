package com.devSait.saitMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Marksheet extends AppCompatActivity {
    FirebaseFirestore fstore= FirebaseFirestore.getInstance();
    MyAdapter myAdapter;
    String UID;
    FirebaseAuth fAuth=FirebaseAuth.getInstance();
    @Override
    Marksheet ob=new Marksheet();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marksheet);
        UID=fAuth.getCurrentUser().getUid();
        setUpRecycleView();

    }
    public static String getSemester(){
        final String[] sem = new String[1];
        Task<DocumentSnapshot> dref= fstore.collection("users").document(UID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        sem[0] = Objects.requireNonNull(task.getResult()).getString("semseter");
                    }

                });
        return sem[0];
    }
    public void setUpRecycleView(){

        CollectionReference Cref= fstore.collection("Marksheet").document(UID).collection(String.valueOf(fstore.collection(Marksheet.getSemester())));
        FirestoreRecyclerOptions<Marksheets> options = new FirestoreRecyclerOptions.Builder<Marksheets>()
                .setSnapshotArray().build();
        myAdapter = new MyAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.dynamic_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        myAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }


    public void logout(View view){
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), login_page.class));
        finish();
    }

}