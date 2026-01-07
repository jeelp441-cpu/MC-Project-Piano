package com.example.fpiano;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SoundPool sp;

    // RECORDING
    boolean isRecording = false;
    ArrayList<NoteEvent> recordedNotes = new ArrayList<>();

    // WHITE KEYS
    int c, d, e, f, g, a, b, c2, d2, e2, f2, g2, a2, b2;

    // BLACK KEYS
    int cs, ds, fs, gs, as, cs2, ds2, fs2, gs2, as2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}