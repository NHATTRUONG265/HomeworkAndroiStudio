package com.example.multimediaapis;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayVideoViewActivity extends AppCompatActivity {

    private VideoView videoView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play_videoview);

        videoView = findViewById(R.id.videoView);

        // Đường dẫn đến file video trong res/raw
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.my_video);
        videoView.setVideoURI(videoUri);

        // Thêm bộ điều khiển media (play, pause, tua...)
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.setOnPreparedListener(mp -> {
            Toast.makeText(this, "Video đã sẵn sàng, bắt đầu phát.", Toast.LENGTH_SHORT).show();
            videoView.start();
        });

        videoView.setOnCompletionListener(mp -> {
            Toast.makeText(this, "Phát video hoàn tất.", Toast.LENGTH_SHORT).show();
        });

        videoView.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(this, "Lỗi khi phát video.", Toast.LENGTH_SHORT).show();
            return true; // true nếu lỗi đã được xử lý
        });
    }
}