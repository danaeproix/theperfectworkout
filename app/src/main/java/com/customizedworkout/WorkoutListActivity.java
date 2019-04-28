package com.customizedworkout;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkoutListActivity extends AppCompatActivity implements WorkoutListAdapter.OnListItemClickListener {

    BottomNavigationView navigationView;
    TextView textView;
    RecyclerView workoutList;
    WorkoutListAdapter workoutAdapter;


    FirebaseAuth auth;
    private DatabaseReference database;

    public WorkoutListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        textView = findViewById(R.id.textview);

        auth = FirebaseAuth.getInstance();

        final Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("muscle")) {
            textView.setText(bundle.getString("muscle"));
        }

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        workoutList = findViewById(R.id.rv);
        workoutList.hasFixedSize();
        workoutList.setLayoutManager(new LinearLayoutManager(this));

        if (bundle != null && bundle.containsKey("muscle")) {
            switch (bundle.getString("muscle")) {
                case "Chest":
                    database = FirebaseDatabase.getInstance().getReference("/Chest");
                    break;
                case "Abs":
                    database = FirebaseDatabase.getInstance().getReference("/Abs");
                    break;
                case "Back":
                    database = FirebaseDatabase.getInstance().getReference("/Back");
                    break;
                case "Biceps":
                    database = FirebaseDatabase.getInstance().getReference("/Biceps");
                    break;
                case "Calf":
                    database = FirebaseDatabase.getInstance().getReference("/Calf");
                    break;
                case "Forearms":
                    database = FirebaseDatabase.getInstance().getReference("/Forearms");
                    break;
                case "Legs":
                    database = FirebaseDatabase.getInstance().getReference("/Legs");
                    break;
                case "Shoulders":
                    database = FirebaseDatabase.getInstance().getReference("/Shoulders");
                    break;
                case "Triceps":
                    database = FirebaseDatabase.getInstance().getReference("/Triceps");
                    break;
                case "Buttocks":
                    database = FirebaseDatabase.getInstance().getReference("/Buttocks");
                    break;
            }
        }

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<WorkoutList> workouts = new ArrayList<>();
                for (DataSnapshot workoutsSnapshot : dataSnapshot.getChildren()) {
                    WorkoutList workout = workoutsSnapshot.getValue(WorkoutList.class);
                    String name = workout.getName();
                    String id = workout.getId();
                    int iconId = workout.getIconId();
                    workouts.add(new WorkoutList(name, id, iconId));
                }

                WorkoutListActivity.this.workoutAdapter = new WorkoutListAdapter(workouts, WorkoutListActivity.this);


                WorkoutListActivity.this.workoutList.setAdapter(WorkoutListActivity.this.workoutAdapter);


                //Toast.makeText(WorkoutListActivity.this, ""+ workoutAdapter.getOnListItemClickListener(), Toast.LENGTH_SHORT);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        progressBar.setVisibility(View.GONE);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent main = new Intent(WorkoutListActivity.this, MainActivity.class);

                    startActivity(main);
                    return true;
                case R.id.navigation_tips:
                    Intent tips = new Intent(WorkoutListActivity.this, TipsActivity.class);

                    startActivity(tips);
                    return true;
                case R.id.navigation_settings:
                    Intent myworkout = new Intent(WorkoutListActivity.this, MyWorkoutActivity.class);

                    startActivity(myworkout);
                    return true;
            }
            return false;
        }
    };


    @Override
    public void onListItemClick(int clickedItemIndex) {
        final Bundle bundle = getIntent().getExtras();
        //int workoutNumber = clickedItemIndex + 1;
        //Toast.makeText(this, "WorkoutNumber : " + workoutNumber, Toast.LENGTH_SHORT).show();
        WorkoutList workout = this.workoutAdapter.getWorkoutLists().get(clickedItemIndex);

        Intent intent = new Intent(this, ExerciceActivity.class);
        String name = workout.getName();
        String id = workout.getId();

        int iconId = workout.getIconId();
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        intent.putExtra("iconId", iconId);
        if (bundle != null && bundle.containsKey("muscle")) {
            intent.putExtra("muscle", bundle.getString("muscle"));
        }
        startActivityForResult(intent, BIND_IMPORTANT);
    }
}
