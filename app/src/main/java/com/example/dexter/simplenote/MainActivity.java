package com.example.dexter.simplenote;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dexter.simplenote.DB.Note;
import com.example.dexter.simplenote.R;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView myRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter recyclerViewAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager rvLayoutManager;            // Declaring Layout Manager as a linear layout manager
    private List<Note> notesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);

        myRecyclerView = (RecyclerView) findViewById(R.id.notesLayout);
        recyclerViewAdapter = new NotesAdapter(notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(recyclerViewAdapter);
        prepareNoteList();

    }

    private void prepareNoteList() {
        Note note = new Note("Call Nagarajan Sir and ask for a meeting","#009688", "23 December 2016 | 08:15 AM");
        notesList.add(note);

        note = new Note("Develop Frontent of the APP", "23 December 2016 | 08:15 AM");
        notesList.add(note);
        note =new Note("Take a bathe", "23 December 2016 | 08:15 AM");
        notesList.add(note);
         note = new Note("The thing to be done has to be done", "23 December 2016 | 08:15 AM");
        notesList.add(note);
         note = new Note("WTF!!", "23 December 2016 | 08:15 AM");
        notesList.add(note);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.action_button:
                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Enter Your Message");
                alert.setTitle("Enter Your Title");
                alert.setView(input);
                alert.setPositiveButton("Add Note", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String YouEditTextValue = input.getText().toString();
                        Log.i("TAG", YouEditTextValue);
                    }
                });

        }
    }





}
