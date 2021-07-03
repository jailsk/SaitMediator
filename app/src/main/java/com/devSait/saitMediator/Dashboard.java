package com.devSait.saitMediator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

public class Dashboard extends AppCompatActivity {

    ImageView profile_pic;
    TextView name, rollno, branch, sem;
    FirebaseFirestore fstore = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    MyAdapter myAdapter;
    Button marks,attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setUpRecycleView();

        profile_pic = findViewById(R.id.Profile_pic);
        name = findViewById(R.id.pname);
        rollno = findViewById(R.id.prollno);
        branch = findViewById(R.id.pbranch);
        sem = findViewById(R.id.psem);
        marks = findViewById(R.id.marks);
        attendance=findViewById(R.id.attendance);

        FirebaseUser user = fAuth.getCurrentUser();
        String currentID = user.getUid();

        DocumentReference ref;
        ref = fstore.collection("users").document(currentID);
        ref.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            String Rname = task.getResult().getString("name");
                            String Rrollno = task.getResult().getString("rollno");
                            String Rbranch = task.getResult().getString("branch");
                            String Rsem = task.getResult().getString("semester");

                            name.setText(Rname);
                            rollno.setText(Rrollno);
                            branch.setText(Rbranch);
                            sem.setText(Rsem);
                        }
                    }
                });
                marks.setOnClickListener(v ->{
                   startActivity(new Intent(getApplicationContext(),Marksheet.class));
                });
                attendance.setOnClickListener(v -> {
                    startActivity(new Intent(getApplicationContext(),Attendance.class));
                });
        }
            public void setUpRecycleView(){
            Query query= fstore.collection("Announcement");
                FirestoreRecyclerOptions<Dynamic_lists> options = new FirestoreRecyclerOptions.Builder<Dynamic_lists>()
                        .setQuery(query,Dynamic_lists.class).build();
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
