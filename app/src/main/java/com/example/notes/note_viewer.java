package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.HashMap;
import java.util.Map;

public class note_viewer extends AppCompatActivity {
    int id;
    EditText _title, _note;
    ConstraintLayout _container;

    Button save, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_viewer);

        id = getIntent().getIntExtra("ID", -1);
        if(id != -1){
            Map<String, Integer> resource=new HashMap<String, Integer>();

            resource.put("WORK", R.drawable.note_border_work);
            resource.put("ENTERTAINMENT", R.drawable.note_border_entertainment);
            resource.put("PROJECT", R.drawable.note_border_project);
            resource.put("SCHOOL", R.drawable.note_border_school);
            resource.put("REMINDER", R.drawable.note_border_reminder);

            _title = findViewById(R.id.title_txt);
            _note = findViewById(R.id.note_txt);
            _container = findViewById(R.id.container_constraint);

            _title.setText(getIntent().getStringExtra("TITLE"));
            _note.setText(getIntent().getStringExtra("NOTE"));
            _container.setBackgroundResource(resource.get(getIntent().getStringExtra("CATEGORY")));
        }

        delete = findViewById(R.id.delete_btn);
        save = findViewById(R.id.save_btn);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id == -1){
                    finish();
                }
                else{
                    //db helper shit
                    //delete note via id
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db helper shit
                if(id == -1){
                    //save new note
                }else{
                    //save via id
                }

            }
        });

    }
}