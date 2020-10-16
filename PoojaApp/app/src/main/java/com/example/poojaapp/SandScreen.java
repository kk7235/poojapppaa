package com.example.poojaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class SandScreen extends AppCompatActivity {

    SignaturePad mSignaturePad1;
    GifImageView karpooram;
    Button   cleartext,textnext;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_screen);
        start();
        mSignaturePad1 = (SignaturePad) findViewById(R.id.signature_pad);
        cleartext=findViewById(R.id.cleartext);
        textnext=findViewById(R.id.textnext);
        karpooram=findViewById(R.id.karpooram);
        cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad1.clear();
                karpooram.setVisibility(View.VISIBLE);
                mSignaturePad1.setVisibility(View.GONE);

                try {
                    GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.poojasandgi);
                    karpooram.setImageDrawable(gifDrawable);
                } catch (Resources.NotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }





                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        mSignaturePad1.setVisibility(View.VISIBLE);
                        karpooram.setVisibility(View.GONE);
                    }
                }, 2500);





            }
        });
        textnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        mSignaturePad1.setOnSignedListener(new SignaturePad.OnSignedListener() {

            @Override
            public void onStartSigning() {
                //Event triggered when the pad is touched
            }

            @Override
            public void onSigned() {
                //Event triggered when the pad is signed
            }

            @Override
            public void onClear() {
                //Event triggered when the pad is cleared
            }
        });
    }
    protected void onSaveInstanceState(Bundle oldInstanceState)
    {
        super.onSaveInstanceState(oldInstanceState);
        oldInstanceState.clear();
    }
    private void stop() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
    public void start(){
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.poojaedukal);
            player.start();
            player.setLooping(true);
        }

    }
}