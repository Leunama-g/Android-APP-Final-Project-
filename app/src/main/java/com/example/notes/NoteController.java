package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteController {

    List<Note> note;
    Context con;
    DBHelper db;

    NoteController(String cat, Context context){
        con = context;
        db = new DBHelper(context);
        note = db.loadNotes(cat);
    }

    public int getSize(){
        if(note!=null)
            return note.size();
        else
            return 0;
    }



    public void populateLinearLayout(LinearLayout left, LinearLayout right, View[] notes){
        TextView title, data;
        LinearLayout _container;
        left.removeAllViews();
        right.removeAllViews();
        Map<String, Integer> resource=new HashMap<String, Integer>();

        resource.put("WORK", R.drawable.note_border_work);
        resource.put("ENTERTAINMENT", R.drawable.note_border_entertainment);
        resource.put("PROJECT", R.drawable.note_border_project);
        resource.put("SCHOOL", R.drawable.note_border_school);
        resource.put("REMINDER", R.drawable.note_border_reminder);

        if(note != null){
            for(int i = 0; i < note.size(); i++){
                title = notes[i].findViewById(R.id.title);
                data = notes[i].findViewById(R.id.note);
                _container = notes[i].findViewById(R.id._container);

                title.setText(note.get(i).title);
                data.setText(note.get(i).data);
                _container.setBackgroundResource(resource.get(note.get(i).category));

                int finalI = i;
                _container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(con, note_viewer.class);

                        intent.putExtra("ID", note.get(finalI).id);
                        intent.putExtra("TITLE", note.get(finalI).title);
                        intent.putExtra("NOTE", note.get(finalI).data);
                        intent.putExtra("CATEGORY", note.get(finalI).category);

                        con.startActivity(intent);
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
