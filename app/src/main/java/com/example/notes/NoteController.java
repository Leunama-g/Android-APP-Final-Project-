package com.example.notes;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class NoteController {

    Note[] note;

    NoteController(String cat){
        if(cat.equals("")){
            //load everything
        }
        else{
            //load cat
        }
    }

    public int getSize(){
        if(note!=null)
            return note.length;
        else
            return 0;
    }



    public void populateLinearLayout(LinearLayout left, LinearLayout right, View[] notes){
        TextView title, data;
        LinearLayout _container;

        Map<String, Integer> resource=new HashMap<String, Integer>();

        resource.put("WORK", R.drawable.note_border_work);
        resource.put("ENTERTAINMENT", R.drawable.note_border_entertainment);
        resource.put("PROJECT", R.drawable.note_border_project);
        resource.put("SCHOOL", R.drawable.note_border_school);
        resource.put("REMINDER", R.drawable.note_border_reminder);

        if(note != null){
            for(int i = 0; i < note.length; i++){
                title = notes[i].findViewById(R.id.title);
                data = notes[i].findViewById(R.id.note);
                _container = notes[i].findViewById(R.id._container);

                title.setText(note[i].title);
                data.setText(note[i].data);
                _container.setBackgroundResource(resource.get(note[i].category));

                _container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //edit note activity is called
                        //Intent intent = new Intent(MainActivity, note_viewer.class);
                    }
                });


                if(i % 2 == 0)
                    left.addView(notes[i]);
                else
                    right.addView(notes[i]);

            }
        }
    }
}
