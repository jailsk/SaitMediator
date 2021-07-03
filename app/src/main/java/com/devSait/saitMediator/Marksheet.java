package com.devSait.saitMediator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Marksheet extends AppCompatActivity {
    /*FirebaseFirestore fstore= FirebaseFirestore.getInstance();
    TextView name, rollno, branch, sem;
    String UID;
    FirebaseAuth fAuth=FirebaseAuth.getInstance(); */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marksheet);
        /*UID = fAuth.getCurrentUser().getUid();
        DocumentReference ref;
        ref = fstore.collection("users").document(UID);
        ref.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
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
    }
        setUpRecycleView();

    }
    public void setUpRecycleView(){

        Task<DocumentSnapshot> documentSnapshotTask = fstore.collection("users").document(UID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        Query query=fstore.collection("Marks").document(UID).collection(String.valueOf(fstore.collection(task.getResult().getString("semester"))));
                        FirestoreRecyclerOptions<Marksheets> options = new FirestoreRecyclerOptions.Builder<Marksheets>()
                                .setQuery(query,Marksheets.class).build();
                        myAdapt = new MarksAdapter(options);

                    }
                });
        RecyclerView recyclerView = findViewById(R.id.marks_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapt);
    }
    @Override
    protected void onStart(){
        super.onStart();
        myAdapt.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myAdapt.stopListening();
    }
    */
    }
        public void logout (View view){
            FirebaseAuth.getInstance().signOut();//logout
            startActivity(new Intent(getApplicationContext(), login_page.class));
            finish();
        }

    }