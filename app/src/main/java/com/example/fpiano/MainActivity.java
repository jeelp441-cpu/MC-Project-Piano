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

        // Fullscreen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.activity_main);

        // SOUNDPOOL (SAFE SETTINGS)
        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        sp = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(attr)
                .build();

        // LOAD WHITE KEYS
        c=sp.load(this,R.raw.c,1);
        d=sp.load(this,R.raw.d,1);
        e=sp.load(this,R.raw.e,1);
        f=sp.load(this,R.raw.f,1);
        g=sp.load(this,R.raw.g,1);
        a=sp.load(this,R.raw.a,1);
        b=sp.load(this,R.raw.b,1);

        c2=sp.load(this,R.raw.c2,1);
        d2=sp.load(this,R.raw.d2,1);
        e2=sp.load(this,R.raw.e2,1);
        f2=sp.load(this,R.raw.f2,1);
        g2=sp.load(this,R.raw.g2,1);
        a2=sp.load(this,R.raw.a2,1);
        b2=sp.load(this,R.raw.b2,1);

        // LOAD BLACK KEYS
        cs=sp.load(this,R.raw.cs,1);
        ds=sp.load(this,R.raw.ds,1);
        fs=sp.load(this,R.raw.fs,1);
        gs=sp.load(this,R.raw.gs,1);
        as=sp.load(this,R.raw.as,1);

        cs2=sp.load(this,R.raw.cs2,1);
        ds2=sp.load(this,R.raw.ds2,1);
        fs2=sp.load(this,R.raw.fs2,1);
        gs2=sp.load(this,R.raw.gs2,1);
        as2=sp.load(this,R.raw.as2,1);

        bindKeys();

        // BUTTONS
        Button btnRecord = findViewById(R.id.btnRecord);
        Button btnPlay   = findViewById(R.id.btnPlay);
        Button btnDemo   = findViewById(R.id.btnDemo);

        btnRecord.setOnClickListener(v -> {
            recordedNotes.clear();
            isRecording = true;
            btnRecord.setText("■ ");
        });

        btnPlay.setOnClickListener(v -> {
            isRecording = false;
            btnRecord.setText("● ");
            playRecording();
        });

        btnDemo.setOnClickListener(v -> {
            playDemoSong();
        });
    }
    private void bindKeys(){
        play(R.id.key_c,c); play(R.id.key_d,d); play(R.id.key_e,e);
        play(R.id.key_f,f); play(R.id.key_g,g); play(R.id.key_a,a);
        play(R.id.key_b,b);

        play(R.id.key_c2,c2); play(R.id.key_d2,d2); play(R.id.key_e2,e2);
        play(R.id.key_f2,f2); play(R.id.key_g2,g2); play(R.id.key_a2,a2);
        play(R.id.key_b2,b2);

        play(R.id.key_cs,cs); play(R.id.key_ds,ds); play(R.id.key_fs,fs);
        play(R.id.key_gs,gs); play(R.id.key_as,as);

        play(R.id.key_cs2,cs2); play(R.id.key_ds2,ds2);
        play(R.id.key_fs2,fs2); play(R.id.key_gs2,gs2);
        play(R.id.key_as2,as2);
    }
    private void play(int id, int sound) {
        Button key = findViewById(id);

        key.setOnTouchListener((v, e) -> {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {

                key.setScaleX(0.98f);
                key.setScaleY(0.98f);

                sp.play(sound,0.7f,0.7f,1,0,1);

                if (isRecording) {
                    recordedNotes.add(new NoteEvent(sound, id));
                }
            }
            else if (e.getAction()==MotionEvent.ACTION_UP ||
                    e.getAction()==MotionEvent.ACTION_CANCEL) {

                key.setScaleX(1f);
                key.setScaleY(1f);
            }
            return true;
        });
    }
    private void playRecording(){
        Handler handler = new Handler();
        int delay = 0;

        for (NoteEvent note : recordedNotes) {
            handler.postDelayed(() -> {
                sp.play(note.sound, 0.7f, 0.7f, 1, 0, 1);

                Button key = findViewById(note.keyId);
                key.setAlpha(0.5f);
                handler.postDelayed(() -> key.setAlpha(1f), 200);

            }, delay);

            delay += 300;
        }
    }
    private void  playDemoSong(){

    }

}
class NoteEvent {
    int sound;
    int keyId;

    NoteEvent(int sound, int keyId) {
        this.sound = sound;
        this.keyId = keyId;
    }
}
