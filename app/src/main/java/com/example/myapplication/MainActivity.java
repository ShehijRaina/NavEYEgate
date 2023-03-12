package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    Button btn_scan;
    ImageButton pause;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(view -> {
            scan_code();
        });
        pause = findViewById(R.id.pause);
        pause.setOnClickListener(view -> {
            pause_1W_Level_2();
        });
    }

    private void scan_code() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume Up to switch on flash");
        //options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);

    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents()!= null){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    play_1W_Level_2();
                }
            }).show();
        }
    });

    private void play_1W_Level_2() {
        if(player == null){
            player = MediaPlayer.create(this, R.raw.audio);
        }
        player.start();
    }

    private void pause_1W_Level_2() {
        if(player == null){
            player = MediaPlayer.create(this, R.raw.audio);
        }
        player.pause();
    }


}
