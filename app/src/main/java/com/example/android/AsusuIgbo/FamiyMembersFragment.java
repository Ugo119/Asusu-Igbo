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

/**
 * A simple {@link Fragment} subclass.
 */
public class FamiyMembersFragment extends Fragment {
    /* Handles playback of all the sound files */
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


    public FamiyMembersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Create an ArrayList to hold list of words with variable length
        final ArrayList<Word> eFamWords = new ArrayList<Word>();
        eFamWords.add(new Word("Father", "Nna", R.drawable.family_father, R.raw.nna));
        eFamWords.add(new Word("Mother", "Nne", R.drawable.family_mother, R.raw.nne));
        eFamWords.add(new Word("Son", "Okpara", R.drawable.family_son, R.raw.okpara));
        eFamWords.add(new Word("Daughter", "Ada", R.drawable.family_daughter, R.raw.ada));
        eFamWords.add(new Word("Grand father", "Nna nna", R.drawable.family_grandfather, R.raw.nna_nna));
        eFamWords.add(new Word("Grand mother", "Nne nne", R.drawable.family_grandmother, R.raw.nne_nne));
        eFamWords.add(new Word("Nephew", "Nwa nwanne", R.drawable.family_younger_brother, R.raw.nwa_nwanne));
        eFamWords.add(new Word("Niece", "Nwa nwanne", R.drawable.family_younger_sister, R.raw.nwa_nwanne));

        //Create an ArrayAdapter object, adapter from the Custom ArrayAdapter class, WordAdapter
        WordAdapter adapter = new WordAdapter(getActivity(), eFamWords, R.color.category_family);

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
                Word word = eFamWords.get(position);

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

    /*
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
