package com.example.android.AsusuIgbo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an ArrayList to hold list of words with variable length
        final ArrayList<Word> eNumWords = new ArrayList<Word>();
        eNumWords.add(new Word("One", "Otu", R.drawable.one, R.raw.otuu));
        eNumWords.add(new Word("Two", "Abuo", R.drawable.two, R.raw.abuo));
        eNumWords.add(new Word("Three", "Ato", R.drawable.three, R.raw.ato));
        eNumWords.add(new Word("Four", "Ano", R.drawable.four, R.raw.ano));
        eNumWords.add(new Word("Five", "Ise", R.drawable.five, R.raw.ise));
        eNumWords.add(new Word("Six", "Isii", R.drawable.six, R.raw.isii));
        eNumWords.add(new Word("Seven", "Asaa", R.drawable.seven, R.raw.asaa));
        eNumWords.add(new Word("Eight", "Asato", R.drawable.eight, R.raw.asato));
        eNumWords.add(new Word("Nine", "Itolu", R.drawable.nine, R.raw.itolu));
        eNumWords.add(new Word("Ten", "Iri", R.drawable.ten, R.raw.iri));
        eNumWords.add(new Word("Eleven", "Iri n'otu", R.drawable.eleven, R.raw.iri_notu));
        eNumWords.add(new Word("Twelve", "Iri n'abuo", R.drawable.twelve, R.raw.iri_nabuo));
        eNumWords.add(new Word("Thirteen", "Iri n'ato", R.drawable.thirteen, R.raw.iri_nato));
        eNumWords.add(new Word("Fourteen", "Iri n'ano", R.drawable.fourteen, R.raw.ir_inano));
        eNumWords.add(new Word("Fifteen", "Iri n'ise", R.drawable.fifteen, R.raw.iri_nise));
        eNumWords.add(new Word("Sixteen", "Iri n'isi", R.drawable.sixteen, R.raw.iri_nisi));
        eNumWords.add(new Word("Seventeen", "Iri n'asaa", R.drawable.seventeen, R.raw.iri_nasaa));
        eNumWords.add(new Word("Eighteen", "Iri n'asato", R.drawable.eighteen, R.raw.iri_nasato));
        eNumWords.add(new Word("Nineteen", "Iri n'itolu", R.drawable.nineteen, R.raw.iri_nitolu));
        eNumWords.add(new Word("Twenty", "Iri abuo", R.drawable.twenty, R.raw.iri_abuo));

        //Create an ArrayAdapter object, adapter from the Custom ArrayAdapter class, WordAdapter
        WordAdapter adapter = new WordAdapter(this, eNumWords, R.color.category_numbers);
        //Create a ListView object
        ListView listView = (ListView) findViewById(R.id.list);
        //Call the ArrayAdapter method, setAdapter on the ListView object
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the {@link Word} object at the given position the user clicked on
                Word word = eNumWords.get(position);
                //Create and setup the media player and method to get the correct audio file.
                mMediaPlayer = MediaPlayer.create(Numbers.this,word.getAudioResourceId());
                //Start the audio file
                mMediaPlayer.start();

            }
        });


    }
}
