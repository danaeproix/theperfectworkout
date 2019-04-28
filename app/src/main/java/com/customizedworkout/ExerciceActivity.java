package com.customizedworkout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class ExerciceActivity extends AppCompatActivity {

    private static int incrementedValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle(R.string.app);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);

        TextView textView = findViewById(R.id.textview);
        ImageView imageView = findViewById(R.id.imageview);

        final Bundle bundle = getIntent().getExtras();

        if(bundle != null && bundle.containsKey("id")) {
            textView.setText(bundle.getString("name"));

            String name = bundle.getString("id");

            final VideoView videoView = findViewById(R.id.videoview);
            Resources resources = getResources();
            int path = resources.getIdentifier(name,
                    "raw", getPackageName());


            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
                    + path);

            videoView.setVideoURI(uri);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {

                    mp.setVolume(0, 0);
                }
            });
            videoView.seekTo( 1 );
            videoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoView.start();
                }
            });
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void addTo(View view){
        final Bundle bundle = getIntent().getExtras();


        if(bundle != null && bundle.containsKey("name")) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
           // prefs.edit().clear().commit();
            boolean addPrefs = true;
            for (int i = 0; i < prefs.getAll().size()/3; i++){
                if(prefs.getString("name" + i, "").equals(bundle.getString("name"))){
                    addPrefs = false;
                    Toast.makeText(ExerciceActivity.this, "Already added", Toast.LENGTH_SHORT).show();
                }
            }
           if(addPrefs) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name" + incrementedValue, bundle.getString("name"));
                editor.putString("id" + incrementedValue, bundle.getString("id"));
                editor.putInt("iconId" + incrementedValue, bundle.getInt("iconId"));
                editor.apply();
                editor.commit();
                Toast.makeText(ExerciceActivity.this, "Exercice added :)", Toast.LENGTH_SHORT).show();
                incrementedValue++;
            }
        }
    }

    @Override
    protected  void onPause() {
        super.onPause();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(ExerciceActivity.this, MainActivity.class);

                    startActivity(home);
                    return true;
                case R.id.navigation_tips:
                    Intent tips = new Intent(ExerciceActivity.this, TipsActivity.class);

                    startActivity(tips);
                    return true;
                case R.id.navigation_settings:
                    Intent myworkout = new Intent(ExerciceActivity.this, MyWorkoutActivity.class);

                    startActivity(myworkout);
                    return true;
            }
            return false;
        }
    };
}
