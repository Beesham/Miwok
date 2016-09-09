package com.example.android.miwok;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsFragment extends Fragment{

    final ArrayList<Word> words = new ArrayList<>();

    MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if ((focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) || (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)){
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        if(mMediaPlayer != null) {
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);
                        }
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        releaseMediaPlayer();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        if(mMediaPlayer != null) mMediaPlayer.start();
                    }
                }
            };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<String> default_words = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.colors_words)));
        ArrayList<String> miwok_words = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.colors_miwok_words)));
        TypedArray words_image_id = getResources().obtainTypedArray(R.array.colors_image_id);

        for(int i=0; i < default_words.size(); i++){
            words.add(new Word(default_words.get(i),
                    miwok_words.get(i),
                    words_image_id.getResourceId(i,0), getResources().getIdentifier("color_" + default_words.get(i).replaceAll(" ","_"), "raw", getActivity().getPackageName())));
        }

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                playAudioClip(position);
            }
        });

        return rootView;
    }


    /*
    * Play audio clip
    */
    private void playAudioClip(int position){
        int result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mMediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getSoundResourceId());
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
