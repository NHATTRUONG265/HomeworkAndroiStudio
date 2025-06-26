package com.example.multimediaapis;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AudioPlayMediaPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button btnPlay, btnPause, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play_mediaplayer);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);

        btnPlay.setOnClickListener(v -> playAudio());
        btnPause.setOnClickListener(v -> pauseAudio());
        btnStop.setOnClickListener(v -> stopAudio());
    }

    private void playAudio() {
        if (mediaPlayer == null) {
            // Tạo mediaPlayer từ file trong res/raw
            mediaPlayer = MediaPlayer.create(this, R.raw.my_audio);
            mediaPlayer.setOnCompletionListener(mp -> {
                // Tự động giải phóng khi phát xong
                stopAudio();
                Toast.makeText(this, "Phát xong!", Toast.LENGTH_SHORT).show();
            });
        }
        mediaPlayer.start();
        Toast.makeText(this, "Đang phát...", Toast.LENGTH_SHORT).show();
    }

    private void pauseAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Toast.makeText(this, "Đã tạm dừng", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this, "Đã dừng", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Đảm bảo giải phóng tài nguyên khi activity không còn hiển thị
        stopAudio();
    }
}
