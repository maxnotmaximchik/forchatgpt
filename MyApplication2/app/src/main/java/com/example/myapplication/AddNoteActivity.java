package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private Button buttonSaveMe;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private NoteDatabase noteDatabase = NoteDatabase.getInstance(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        initViews();

        buttonSaveMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    public static Intent newIntent(Context context)
    {
        return new Intent(context, AddNoteActivity.class);
    }

    private void initViews()
    {
        editTextNote = findViewById(R.id.editTextNote);
        buttonSaveMe = findViewById(R.id.buttonSaveMe);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
    }

    private void saveNote()
    {
        String text = editTextNote.getText().toString().trim();
        int id = noteDatabase.notesDao().getNotes().size();
        int priority = getPriority();

        Note note = new Note(id, text, priority);

        noteDatabase.notesDao().addNote(note);

        finish();

    }

    private int getPriority()
    {
        int priority;

        if (radioButtonLow.isChecked())
        {
            priority = 0;
        }
        else if (radioButtonMedium.isChecked())
        {
            priority = 1;
        } else
        {
            priority = 2;
        } return priority;
    }
}