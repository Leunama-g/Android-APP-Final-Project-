package com.example.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link allNotesView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class allNotesView extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public allNotesView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment allNotesView.
     */
    // TODO: Rename and change types and number of parameters
    public static allNotesView newInstance(String param1, String param2) {
        allNotesView fragment = new allNotesView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_notes_view, container, false);
    }
    
    LinearLayout left, right;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        left = view.findViewById(R.id.left_layout);
        right = view.findViewById(R.id.right_layout);
        setUpNoteContainer();
    }

    private void setUpNoteContainer(){

        NoteController nc = new NoteController("");

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