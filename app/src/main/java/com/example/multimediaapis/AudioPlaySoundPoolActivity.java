package com.example.multimediaapis;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AudioPlaySoundPoolActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int soundId;
    private boolean isSoundLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play_soundpool);

        // Cấu hình thuộc tính âm thanh
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        // Tạo SoundPool
        soundPool = new SoundPool.Builder()
                .setMaxStreams(5) // Số lượng âm thanh có thể phát cùng lúc
                .setAudioAttributes(audioAttributes)
                .build();

        // Load file âm thanh từ res/raw
        soundId = soundPool.load(this, R.raw.my_sound_effect, 1);

        // Listener để biết khi nào âm thanh đã được load xong
        soundPool.setOnLoadCompleteListener((sp, sampleId, status) -> {
            if (status == 0) { // status 0 là thành công
                isSoundLoaded = true;
            }
        });

        Button btnPlaySound = findViewById(R.id.btnPlaySound);
        btnPlaySound.setOnClickListener(v -> {
            if (isSoundLoaded) {
                // play(soundID, leftVolume, rightVolume, priority, loop, rate)
                // loop = 0: không lặp, -1: lặp vô hạn
                soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Giải phóng SoundPool khi activity bị hủy
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}