package com.customizedworkout;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;

public class TipsActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    private static final String CLIENT_ID = "c3b41d78f33c4f2f89b226b37b660f08";
    private static final String REDIRECT_URI = "my-customized-workout://callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    String html;
    Button diet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle(R.string.app);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        diet = findViewById(R.id.diet);


        //    mSpotifyAppRemote.isConnected();

        // mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(TipsActivity.this, MainActivity.class);

                    startActivity(home);
                    return true;
                case R.id.navigation_tips:
                    Intent tips = new Intent(TipsActivity.this, TipsActivity.class);

                    startActivity(tips);
                    return true;
                case R.id.navigation_settings:
                    Intent myworkout = new Intent(TipsActivity.this, MyWorkoutActivity.class);

                    startActivity(myworkout);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");

                        // Now you can start interacting with App Remote

                        connected();

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("MainActivity", throwable.getMessage(), throwable);

                    }
                });

    }

    public void startPlaylist(View v){
        if(mSpotifyAppRemote.isConnected()) {
            mSpotifyAppRemote.getPlayerApi().play("spotify:user:21jj7duf2yr4itggkwfnmuiwa:playlist:4JsCksWkgYBCTad3PY03RB");
        }
    }

    private void connected() {

        final PlayerApi playerApi = mSpotifyAppRemote.getPlayerApi();


        ImageView stop = findViewById(R.id.stop);
        ImageView play = findViewById(R.id.play);
        ImageView before = findViewById(R.id.before);
        ImageView next = findViewById(R.id.next);

        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                playerApi.pause();

            }
        });

        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                playerApi.resume();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                playerApi.skipNext();
            }
        });
        before.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                playerApi.skipPrevious();
            }
        });


    }

    public void dietOnClick(View view) {
        String url = "https://en.wikipedia.org/wiki/Diet_(nutrition)";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    @Override
    protected void onStop() {
        super.onStop();

        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSpotifyAppRemote.getPlayerApi().pause();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);

    }


}
