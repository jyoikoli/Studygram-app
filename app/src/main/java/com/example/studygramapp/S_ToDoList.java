package com.example.studygramapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class S_ToDoList extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference notesReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sto_do_list);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        notesReference = database.getReference().child("Students TODO LIST");

        FloatingActionButton add = findViewById(R.id.addNote1);
        add.setOnClickListener(view -> showAddNoteDialog());

        TextView empty = findViewById(R.id.empty1);
        RecyclerView recyclerView = findViewById(R.id.recycler1);

        notesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Note> arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Note note = dataSnapshot.getValue(Note.class);
                    if (note != null) {
                        note.setKey(dataSnapshot.getKey());
                        arrayList.add(note);
                    }
                }

                updateUI(arrayList, empty, recyclerView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
    }

    private void showAddNoteDialog() {
        View view1 = LayoutInflater.from(this).inflate(R.layout.add_note_dialog, null);
        TextInputLayout titleLayout, contentLayout;
        titleLayout = view1.findViewById(R.id.titleLayout);
        contentLayout = view1.findViewById(R.id.contentLayout);
        TextInputEditText titleET, contentET;
        titleET = view1.findViewById(R.id.titleET);
        contentET = view1.findViewById(R.id.contentET);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Add")
                .setView(view1)
                .setPositiveButton("Add", (dialogInterface, i) -> {
                    String title = Objects.requireNonNull(titleET.getText()).toString().trim();
                    String content = Objects.requireNonNull(contentET.getText()).toString().trim();

                    if (title.isEmpty()) {
                        titleLayout.setError("This field is required!");
                    } else if (content.isEmpty()) {
                        contentLayout.setError("This field is required!");
                    } else {
                        // Add note to the database
                        Note newNote = new Note();
                        newNote.setTitle(title);
                        newNote.setContent(content);

                        notesReference.push().setValue(newNote)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(S_ToDoList.this, "Note added successfully", Toast.LENGTH_SHORT).show();
                                    dialogInterface.dismiss();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(S_ToDoList.this, "Error adding note", Toast.LENGTH_SHORT).show();
                                    dialogInterface.dismiss();
                                });
                    }
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
                .create();
        alertDialog.show();
    }

    private void updateUI(ArrayList<Note> notes, TextView empty, RecyclerView recyclerView) {
        if (notes.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        NoteAdapter adapter = new NoteAdapter(this, notes);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(note -> showEditNoteDialog(note));
    }

    private void showEditNoteDialog(Note note) {
        View view = LayoutInflater.from(this).inflate(R.layout.add_note_dialog, null);
        TextInputLayout titleLayout, contentLayout;
        TextInputEditText titleET, contentET;

        titleET = view.findViewById(R.id.titleET);
        contentET = view.findViewById(R.id.contentET);
        titleLayout = view.findViewById(R.id.titleLayout);
        contentLayout = view.findViewById(R.id.contentLayout);

        titleET.setText(note.getTitle());
        contentET.setText(note.getContent());

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Edit")
                .setView(view)
                .setPositiveButton("Save", (dialogInterface, i) -> {
                    String newTitle = Objects.requireNonNull(titleET.getText()).toString().trim();
                    String newContent = Objects.requireNonNull(contentET.getText()).toString().trim();

                    if (newTitle.isEmpty()) {
                        titleLayout.setError("This field is required!");
                    } else if (newContent.isEmpty()) {
                        contentLayout.setError("This field is required!");
                    } else {
                        // Edit note in the database
                        Note updatedNote = new Note();
                        updatedNote.setTitle(newTitle);
                        updatedNote.setContent(newContent);

                        notesReference.child(note.getKey()).setValue(updatedNote)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(S_ToDoList.this, "Note edited successfully", Toast.LENGTH_SHORT).show();
                                    dialogInterface.dismiss();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(S_ToDoList.this, "Error editing note", Toast.LENGTH_SHORT).show();
                                    dialogInterface.dismiss();
                                });
                    }
                })
                .setNeutralButton("Close", (dialogInterface, i) -> dialogInterface.dismiss())
                .setNegativeButton("Delete", (dialogInterface, i) -> {
                    // Delete note from the database
                    notesReference.child(note.getKey()).removeValue()
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(S_ToDoList.this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(S_ToDoList.this, "Error deleting note", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            });
                })
                .create();
        alertDialog.show();
    }
}
