package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class note_viewer extends AppCompatActivity {
    int id;
    EditText _title, _note;
    Spinner catSP;
    ConstraintLayout _container;
    String[] categoryList = {"Select Note Group","WORK","ENTERTAINMENT", "PROJECT", "SCHOOL", "REMINDER"};

    Button save, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_viewer);

        id = getIntent().getIntExtra("ID", -1);


        _title = findViewById(R.id.title_txt);
        _note = findViewById(R.id.note_txt);
        _container = findViewById(R.id.container_constraint);
        catSP = findViewById(R.id.cat_picker);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,categoryList
        );

        catSP.setAdapter(categoryAdapter);


        if(id != -1){
            String title_txt = getIntent().getStringExtra("TITLE");
            String note_txt = getIntent().getStringExtra("NOTE");
            String resource_txt = getIntent().getStringExtra("CATEGORY");

            _title.setText(title_txt);
            _note.setText(note_txt);
            catSP.setSelection(returnPos(resource_txt));
            changeBack(resource_txt);
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
                    DBHelper db = new DBHelper(getApplicationContext());
                    db.deleteOne(id);
                    finish();
                }

            }
        });

        catSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0)
                    changeBack(categoryList[i]);
                else
                    _container.setBackgroundResource(R.drawable.note_border);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(_title.getText().toString().trim().equals("") || _note.getText().toString().trim().equals("")){
                    Toast.makeText(note_viewer.this, "Title or Note area are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    DBHelper db = new DBHelper(getApplicationContext());
                    Note data = new Note();
                    data.id = id;
                    data.title = _title.getText().toString();
                    data.data = _note.getText().toString();
                    data.category = catSP.getSelectedItem().toString();
                    db.saveNote(data);
                    finish();
                }
            }
        });



    }

    public void changeBack(String cat){
        Map<String, Integer> resource=new HashMap<String, Integer>();

        resource.put("WORK", R.drawable.note_border_work);
        resource.put("ENTERTAINMENT", R.drawable.note_border_entertainment);
        resource.put("PROJECT", R.drawable.note_border_project);
        resource.put("SCHOOL", R.drawable.note_border_school);
        resource.put("REMINDER", R.drawable.note_border_reminder);

        _container.setBackgroundResource(resource.get(cat));
    }

    public int returnPos(String cat){
        for(int i = 0; i < categoryList.length; i++){
            if(categoryList[i].equals(cat))
                return i;
        }
        return 0;
    }

}