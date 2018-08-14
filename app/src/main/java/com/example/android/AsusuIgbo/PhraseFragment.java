package com.example.android.AsusuIgbo;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;

/*
 * A simple {@link Fragment} subclass.
 */
public class PhraseFragment extends Fragment {
    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /*
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    /* Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /*
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };


    public PhraseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Create an ArrayList to hold list of words with variable length
        final ArrayList<Word> ePhraWords = new ArrayList<Word>();
        ePhraWords.add(new Word("How are you?", "Kedu ka imere?", R.raw.kedu));
        ePhraWords.add(new Word("What is your name?", "Gini bu aha gi?", R.raw.ahagi));
        ePhraWords.add(new Word("My name is Ugo", "Aha m bu Ugo", R.raw.aham_bu));
        ePhraWords.add(new Word("Good morning", "Ututu oma", R.raw.ututu_oma));
        ePhraWords.add(new Word("Good afternoon", "Ehihie oma", R.raw.ehihie));
        ePhraWords.add(new Word("I am hungry", "Aguu n'agu m", R.raw.aguuna));
        ePhraWords.add(new Word("I want to buy", "Achoro m izu", R.raw.achorom_izu));
        ePhraWords.add(new Word("Where are you?", "Kee ebe ino?", R.raw.kedu_ebe_ino));
        ePhraWords.add(new Word("Where do you live?", "Kedu ebe ibi?", R.raw.kedu_ebe_ibi));
        ePhraWords.add(new Word("Come here", "Bia ebea", R.raw.bia_ebea));
        ePhraWords.add(new Word("How old are you?", "Afo ole ka idi?", R.raw.afo_ole_ka_idi));
        ePhraWords.add(new Word("This is my father", "Nkea bu nna m", R.raw.nkea_bu_nnam));
        ePhraWords.add(new Word("Who are you?", "Onye ka ibu?", R.raw.onye_ka_ibu));
        ePhraWords.add(new Word("What time is it?", "Gini n'aku?", R.raw.gini_naku));
        ePhraWords.add(new Word("I have a headache", "Isi n'awa m", R.raw.isi_nawam));
        ePhraWords.add(new Word("I am playing", "Ana m egwu egwu", R.raw.anam_egwu_egwu));

        //Create an ArrayAdapter object, adapter from the Custom ArrayAdapter class, WordAdapter
        WordAdapter adapter = new WordAdapter(getActivity(), ePhraWords, R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //Call the ArrayAdapter method, setAdapter on the ListView object
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>  adapterView, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                //Get the {@link Word} object at the given position the user clicked on
                Word word = ePhraWords.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });


        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        //when activity stops, release media player resource
        releaseMediaPlayer();
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
            //Abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }


}
