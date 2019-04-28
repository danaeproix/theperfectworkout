package com.customizedworkout;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;


public class MainActivity extends AppCompatActivity implements OnClickableAreaClickedListener {


    ImageView imageView;
    BottomNavigationView navigationView;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  getSupportActionBar().setTitle(R.string.app);
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(R.string.app);

       /* WorkoutList.addAbs();
        WorkoutList.addChest();
        WorkoutList.addBack();
        WorkoutList.addCalf();
        WorkoutList.addForearms();
        WorkoutList.addLegs();
        WorkoutList.addShoulders();
        WorkoutList.addShoulders();
        WorkoutList.addTriceps();
        WorkoutList.addButtocks();*/

        imageView = findViewById(R.id.imageview);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(imageView), this);
        clickableAreasImage.setClickableAreas(getClickableAreasFront());
    }

    public void turnImage(View v) {
        ImageView imageView = findViewById(R.id.imageview);
        if (imageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.body_front).getConstantState()) {
            imageView.setImageResource(R.drawable.body_back);
            ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(imageView), this);
            clickableAreasImage.setClickableAreas(getClickableAreasBack());
        } else {
            imageView.setImageResource(R.drawable.body_front);
            ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(imageView), this);
            clickableAreasImage.setClickableAreas(getClickableAreasFront());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClickableAreaTouched(final Object o) {
        if (o instanceof Muscle) {
            switch (((Muscle) o).getMuscleName()) {
                case "Chest":
                    imageView.setImageResource(R.drawable.body_chest);
                    //Toast.makeText(this, ((Muscle) o).getMuscleName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Abs":
                    imageView.setImageResource(R.drawable.body_abs);
                    //Toast.makeText(this, ((Muscle) o).getMuscleName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Legs":
                    imageView.setImageResource(R.drawable.body_legs);
                    //Toast.makeText(this, ((Muscle) o).getMuscleName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Biceps":
                    imageView.setImageResource(R.drawable.body_biceps);
                    break;
                case "Forearms":
                    imageView.setImageResource(R.drawable.body_forearms);
                    break;
                case "Shoulders":
                    imageView.setImageResource(R.drawable.body_shoulders);
                    break;
                case "Back":
                    imageView.setImageResource(R.drawable.body_back_mus);
                    //Toast.makeText(this, ((Muscle) o).getMuscleName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Calf":
                    imageView.setImageResource(R.drawable.body_calf);
                    //Toast.makeText(this, ((Muscle) o).getMuscleName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Triceps":
                    imageView.setImageResource(R.drawable.body_triceps);
                    //Toast.makeText(this, ((Muscle) o).getMuscleName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Buttocks":
                    imageView.setImageResource(R.drawable.body_buttocks);
                    //Toast.makeText(this, ((Muscle) o).getMuscleName(), Toast.LENGTH_SHORT).show();
                    break;

            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(MainActivity.this, WorkoutListActivity.class);
                if (!((Muscle) o).getMuscleName().equals(null)) {
                    intent.putExtra("muscle", ((Muscle) o).getMuscleName());
                }
                startActivityForResult(intent, BIND_IMPORTANT);
            }
        }, 1000);

    }


    private List<ClickableArea> getClickableAreasFront() {

        List<ClickableArea> clickableAreas = new ArrayList<>();


        clickableAreas.add(new ClickableArea<>(50, 50, 80, 50, new Muscle("Chest")));
        clickableAreas.add(new ClickableArea<>(75, 100, 45, 90, new Muscle("Abs")));

        clickableAreas.add(new ClickableArea<>(40, 100, 30, 30, new Muscle("Biceps")));
        clickableAreas.add(new ClickableArea<>(100, 100, 30, 30, new Muscle("Biceps")));

        clickableAreas.add(new ClickableArea<>(30, 120, 30, 50, new Muscle("Forearms")));
        clickableAreas.add(new ClickableArea<>(105, 120, 30, 50, new Muscle("Forearms")));

        clickableAreas.add(new ClickableArea<>(50, 170, 90, 70, new Muscle("Legs")));

         clickableAreas.add(new ClickableArea<>(40, 50, 30, 45, new Muscle("Shoulders")));
        clickableAreas.add(new ClickableArea<>(100, 50, 30, 45, new Muscle("Shoulders")));

        return clickableAreas;
    }

    private List<ClickableArea> getClickableAreasBack() {

        List<ClickableArea> clickableAreas = new ArrayList<>();

        clickableAreas.add(new ClickableArea<>(40, 220, 80, 50, new Muscle("Calf")));
        clickableAreas.add(new ClickableArea<>(50, 50, 80, 110, new Muscle("Back")));

        clickableAreas.add(new ClickableArea<>(40, 80, 20, 40, new Muscle("Triceps")));
        clickableAreas.add(new ClickableArea<>(110, 80, 20, 40, new Muscle("Triceps")));

        clickableAreas.add(new ClickableArea<>(40, 155, 40, 40, new Muscle("Buttocks")));
        clickableAreas.add(new ClickableArea<>(90, 155, 40, 40, new Muscle("Buttocks")));

        return clickableAreas;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(home);
                    return true;
                case R.id.navigation_tips:
                    Intent intent = new Intent(MainActivity.this, TipsActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_settings:
                    Intent myworkout = new Intent(MainActivity.this, MyWorkoutActivity.class);
                    startActivity(myworkout);
                    return true;
            }
            return false;
        }
    };


    public void onMenuClick(MenuItem item) {
        if (item.getItemId() == R.id.first_action) {
            Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settings);
        }
    }

}
