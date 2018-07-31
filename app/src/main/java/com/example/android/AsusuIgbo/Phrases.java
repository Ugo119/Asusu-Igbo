package com.example.android.AsusuIgbo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an ArrayList to hold list of words with variable length
        final ArrayList<Word>  ePhraWords = new ArrayList<Word>();
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
        WordAdapter adapter = new WordAdapter(this,  ePhraWords, R.color.category_phrases);
        //Create a ListView object
        ListView listView = (ListView) findViewById(R.id.list);
        //Call the ArrayAdapter method, setAdapter on the ListView object
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = ePhraWords.get(position);
                mMediaPlayer = MediaPlayer.create(Phrases.this,word.getAudioResourceId());
                mMediaPlayer.start();

            }
        });
    }
}
