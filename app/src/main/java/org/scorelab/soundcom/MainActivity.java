package org.scorelab.soundcom;

import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Object;

public class MainActivity extends AppCompatActivity {
    private ImageButton startBtn;


    //Requesting run-time permissions
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;

    private void requestAudioPermissions() {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                //Give user option to still opt-in the permissions
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);

            } else {
                // Show user dialog to grant permission to record audio
                Toast.makeText(this, "Permissions Granted to record audio", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestAudioPermissions();

        startBtn = (ImageButton)findViewById(R.id.button1);
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent svc=new Intent(getBaseContext(), RecordingService.class);
                startService(svc);


                finish(); //because I want to close the UI after service started
            }
        });
    }
}

