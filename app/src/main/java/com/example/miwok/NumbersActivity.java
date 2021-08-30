package com.example.miwok;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private void releaseMediaPlayer() {
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(mMediaPlayer != null){
                if(i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                }
                else if(i == AudioManager.AUDIOFOCUS_GAIN){
                    mMediaPlayer.start();
                }
                else if(i == AudioManager.AUDIOFOCUS_LOSS){
                    releaseMediaPlayer();
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("lutti" , "one" , R.drawable.number_one , R.raw.number_one));
        words.add(new Word("otiko" , "two" , R.drawable.number_two , R.raw.number_two));
        words.add(new Word("tolookosu" , "three" , R.drawable.number_three , R.raw.number_three));
        words.add(new Word("oyyisa" , "four" , R.drawable.number_four , R.raw.number_four));
        words.add(new Word("massokka" , "five" , R.drawable.number_five , R.raw.number_five));
        words.add(new Word("temokka" , "six" , R.drawable.number_six , R.raw.number_six));
        words.add(new Word("kenekaku" , "seven" , R.drawable.number_seven , R.raw.number_seven));
        words.add(new Word("kawinta" , "eight" , R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo'e" , "nine" , R.drawable.number_nine , R.raw.number_nine));
        words.add(new Word("na'aacha" , "ten" , R.drawable.number_ten, R.raw.number_ten));

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        WordAdapter adapter = new WordAdapter(this , words , R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }


            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}