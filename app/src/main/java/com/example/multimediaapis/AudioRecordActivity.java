package com.example.multimediaapis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class AudioRecordActivity extends AppCompatActivity {

    private static final String TAG = "AudioRecordActivity";
    private static final int REQUEST_CODE_PERMISSIONS_AUDIO = 20;
    private static final String[] REQUIRED_PERMISSIONS_AUDIO = new String[]{Manifest.permission.RECORD_AUDIO};

    private Button btnStartRecord, btnStopRecord;
    private TextView tvStatus;

    private MediaRecorder mediaRecorder;
    private String outputFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        btnStartRecord = findViewById(R.id.btnStartRecord);
        btnStopRecord = findViewById(R.id.btnStopRecord);
        tvStatus = findViewById(R.id.tvStatus);

        // Đường dẫn lưu file ghi âm
        outputFile = getExternalCacheDir().getAbsolutePath() + "/myaudio.3gp";

        btnStartRecord.setOnClickListener(v -> {
            if (checkPermissions()) {
                startRecording();
            } else {
                requestPermissions();
            }
        });

        btnStopRecord.setOnClickListener(v -> stopRecording());
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(outputFile);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            tvStatus.setText("Đang ghi âm...");
            btnStartRecord.setEnabled(false);
            btnStopRecord.setEnabled(true);
            Toast.makeText(this, "Bắt đầu ghi âm", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed", e);
            Toast.makeText(this, "Ghi âm thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            tvStatus.setText("Đã dừng. File được lưu tại:\n" + outputFile);
            btnStopRecord.setEnabled(false);
            btnStartRecord.setEnabled(true);
            Toast.makeText(this, "Đã dừng ghi âm", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermissions() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS_AUDIO, REQUEST_CODE_PERMISSIONS_AUDIO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS_AUDIO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startRecording();
            } else {
                Toast.makeText(this, "Bạn phải cấp quyền để ghi âm.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}