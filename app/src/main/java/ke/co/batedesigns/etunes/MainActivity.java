package ke.co.batedesigns.etunes;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import ke.co.batedesigns.etunes.Library.AllSongsActivity;
import ke.co.batedesigns.etunes.Model.AudioModel;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    MediaPlayer mediaPlayer;
    AudioModel audio;
    SeekBar seekBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_music:
                    Intent allMusicIntent = new Intent(MainActivity.this, AllSongsActivity.class);
                    startActivity(allMusicIntent);
                    return true;
                case R.id.navigation_equalizer:
                    Intent equalizerIntent = new Intent(MainActivity.this, EqualizerActivity.class);
                    startActivity(equalizerIntent);
                    return true;
                case R.id.navigation_search:
                    Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(searchIntent);
                    return true;
                case R.id.navigation_settings:
                    Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        audio = (AudioModel) intent.getSerializableExtra("audio");

        TextView audioName = findViewById(R.id.audioName);
        Button playBtn = findViewById(R.id.playBtn);
        Button viewAllMediaBtn = findViewById(R.id.viewAllMedia);

        if(audio!=null){
            audioName.setText(audio.getaName());
        }else{
            audioName.setText(R.string.initializing);
        }


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlaying();
            }
        });

        viewAllMediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                startActivity(new Intent(getApplicationContext(),AllSongsActivity.class));
                //finish();
            }
        });

    }//End of onCreate

    public void startPlaying(){
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }

        if(audio!=null){

            Toast.makeText(getApplicationContext(),"Playing from device; "+audio.getaPath(),Toast.LENGTH_SHORT).show();
            mediaPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(audio.getaPath()));
            mediaPlayer.start();

        }else{

            Toast.makeText(getApplicationContext(),"Playing from app",Toast.LENGTH_SHORT).show();
            try {
                AssetFileDescriptor afd = getAssets().openFd("piano.wav");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mediaPlayer.prepare();
                mediaPlayer.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        enableSeekBar();
    }

    public void stopPlaying(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    public void enableSeekBar(){
        seekBar =  findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }
        }, 0, 10);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // Update the progress depending on seek bar
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        stopPlaying();
        super.onBackPressed();
    }

}
