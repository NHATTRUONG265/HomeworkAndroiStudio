package com.example.multimediaapis;
// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
// ... import các Button

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTestCameraX).setOnClickListener(v ->
                startActivity(new Intent(this, CameraXActivity.class)));

        findViewById(R.id.btnPlayVideoExoPlayer).setOnClickListener(v ->
                startActivity(new Intent(this, VideoPlayExoPlayerActivity.class)));

        findViewById(R.id.btnPlayVideoVideoView).setOnClickListener(v ->
                startActivity(new Intent(this, VideoPlayVideoViewActivity.class)));

        findViewById(R.id.btnRecordAudio).setOnClickListener(v ->
                startActivity(new Intent(this, AudioRecordActivity.class)));

        findViewById(R.id.btnPlayAudioMediaPlayer).setOnClickListener(v ->
                startActivity(new Intent(this, AudioPlayMediaPlayerActivity.class)));

        findViewById(R.id.btnPlayAudioSoundPool).setOnClickListener(v ->
                startActivity(new Intent(this, AudioPlaySoundPoolActivity.class)));

        findViewById(R.id.btnRecordVideo).setOnClickListener(v ->
                startActivity(new Intent(this, VideoRecordActivity.class)));

    }
}