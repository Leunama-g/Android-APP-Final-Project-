package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button m_all, m_groups, _new;
    boolean btn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.mainContent, new allNotesView()).commit();

        m_all = findViewById(R.id.all_btn);
        m_groups = findViewById(R.id.groups_btn);
        _new = findViewById(R.id.new_btn);

        m_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = true;
                //fragment change code
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContent, new allNotesView()).commit();
                m_all.setBackgroundResource(R.drawable.main_btn_background_active);
                m_all.setTextColor(Color.parseColor("#FF4D00"));

                m_groups.setBackgroundResource(R.drawable.main_btn_background_inactive);
                m_groups.setTextColor(Color.parseColor("#342D2D"));
            }
        });

        m_groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = false;
                //fragment change code
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContent, new groupView()).commit();
                m_groups.setBackgroundResource(R.drawable.main_btn_background_active);
                m_groups.setTextColor(Color.parseColor("#FF4D00"));

                m_all.setBackgroundResource(R.drawable.main_btn_background_inactive);
                m_all.setTextColor(Color.parseColor("#342D2D"));
            }
        });

        _new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, cat_view.class));
            }
        });
    }


}