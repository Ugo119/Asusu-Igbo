package com.example.android.AsusuIgbo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembers extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

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

        WordAdapter fAdapter = new WordAdapter(this, eFamWords, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(fAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = eFamWords.get(position);
                mMediaPlayer = MediaPlayer.create(FamilyMembers.this,word.getAudioResourceId());
                mMediaPlayer.start();

            }
        });
    }
}
