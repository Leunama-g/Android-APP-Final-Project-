package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class cat_view extends AppCompatActivity {
    TextView title;
    LinearLayout left, right;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_view);

        category = getIntent().getStringExtra("cat");
        title = findViewById(R.id.title_txt);
        title.setText(category);

        left = findViewById(R.id.left_layout);
        right = findViewById(R.id.right_layout);
        setUpNoteContainer();
    }

    private void setUpNoteContainer(){
        NoteController nc = new NoteController(category, cat_view.this);
        View[] notes = new View[nc.getSize()];

        for(int i = 0; i < notes.length; i++){
            if(i % 2 == 0){
                if(i == 0)
                    notes[i] = getLayoutInflater().inflate(R.layout.note_continer_left_top, left, false);
                else
                    notes[i] = getLayoutInflater().inflate(R.layout.note_continer_left, left, false);
            }
            else{
                if(i == 1)
                    notes[i] = getLayoutInflater().inflate(R.layout.note_continer_right_top, right, false);
                else
                    notes[i] = getLayoutInflater().inflate(R.layout.note_continer_right, right, false);
            }
        }

        nc.populateLinearLayout(left,right,notes);
    }
}