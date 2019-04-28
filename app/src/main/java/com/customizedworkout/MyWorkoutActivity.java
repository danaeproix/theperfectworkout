package com.customizedworkout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MyWorkoutActivity extends AppCompatActivity implements MyWorkoutAdapter.OnListItemClickListener {

    RecyclerView workoutList;
    MyWorkoutAdapter workoutAdapter;
    FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle(R.string.app);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout);

        auth = FirebaseAuth.getInstance();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (prefs.getAll().size() == 0) {
            Toast.makeText(MyWorkoutActivity.this, "No exercices added yet", Toast.LENGTH_SHORT).show();
        }


        workoutList = findViewById(R.id.rv);
        workoutList.hasFixedSize();
        workoutList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<WorkoutList> workouts = new ArrayList<>();

        for (int i = 0; i < prefs.getAll().size() / 3; i++) {

            String name = prefs.getString("name" + i, "");
            int iconId = prefs.getInt("iconId" + i, 1);
            String id = prefs.getString("id" + i, "");

            workouts.add(new WorkoutList(name, id, iconId));

        }

        MyWorkoutActivity.this.workoutAdapter = new MyWorkoutAdapter(workouts, MyWorkoutActivity.this, getApplicationContext());
        MyWorkoutActivity.this.workoutList.setAdapter(MyWorkoutActivity.this.workoutAdapter);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(MyWorkoutActivity.this, MainActivity.class);

                    startActivity(home);
                    return true;
                case R.id.navigation_tips:
                    Intent tips = new Intent(MyWorkoutActivity.this, TipsActivity.class);

                    startActivity(tips);
                    return true;
                case R.id.navigation_settings:
                    Intent myworkout = new Intent(MyWorkoutActivity.this, MyWorkoutActivity.class);

                    startActivity(myworkout);
                    return true;
            }
            return false;
        }
    };

    public void refresh(View view){
        onRestart();
    }

    @Override
    protected void onRestart() {


        super.onRestart();
        Intent i = new Intent(MyWorkoutActivity.this, MyWorkoutActivity.class);
        startActivity(i);
        finish();

    }


    @Override
    public void onListItemClick(int clickedItemIndex, View view) {



        final WorkoutList workout = this.workoutAdapter.getWorkoutLists().get(clickedItemIndex);



        //int workoutNumber = clickedItemIndex + 1;
        //Toast.makeText(this, "WorkoutNumber : " + workoutNumber, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ExerciceActivity.class);
        String name = workout.getName();
        String id = workout.getId();
        int iconId = workout.getIconId();
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        intent.putExtra("iconId", iconId);
        startActivityForResult(intent, BIND_IMPORTANT);
    }


}
