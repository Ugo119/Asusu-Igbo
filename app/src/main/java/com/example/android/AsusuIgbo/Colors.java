package com.example.android.AsusuIgbo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> eColWords = new ArrayList<Word>();
        eColWords.add(new Word("White", "Ocha", R.drawable.color_white, R.raw.ocha));
        eColWords.add(new Word("Black", "Oji", R.drawable.color_black, R.raw.oji));
        eColWords.add(new Word("Yellow", "Odo odo", R.drawable.color_mustard_yellow, R.raw.odo_odo));
        eColWords.add(new Word("Red", "Uhie", R.drawable.color_red, R.raw.uhie));
        eColWords.add(new Word("Green", "Akwukwo ndu", R.drawable.color_green, R.raw.akwukwo_ndu));
        eColWords.add(new Word("Blue", "Anunu", R.drawable.blue, R.raw.anunu));
        eColWords.add(new Word("Orange", "Mmanu mmanu", R.drawable.orange, R.raw.manu_manu));


        WordAdapter adapter = new WordAdapter(this, eColWords, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = eColWords.get(position);
                mMediaPlayer = MediaPlayer.create(Colors.this,word.getAudioResourceId());
                mMediaPlayer.start();

            }
        });
    }
}
