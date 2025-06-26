package com.example.multimediaapis;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;


public class VideoPlayExoPlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private ExoPlayer exoPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play_exoplayer);

        playerView = findViewById(R.id.playerView);
    }

    private void initializePlayer() {
        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);

        // Đường dẫn đến file video trong thư mục res/raw
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.my_video);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);

        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.setRepeatMode(Player.REPEAT_MODE_ONE); // Lặp video (test thử)
        exoPlayer.setPlayWhenReady(true);
        exoPlayer.prepare();

    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // API level 24+ thì initialize ở onStart, release ở onStop
        initializePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (exoPlayer == null) {
            // API level < 24 thì initialize ở onResume, release ở onPause
            initializePlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // API level < 24
        releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // API level 24+
        releasePlayer();
    }
}