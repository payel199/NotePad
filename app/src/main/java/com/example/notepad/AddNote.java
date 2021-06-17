package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.example.notepad.Adapter.RecyclerViewAdapter;
import com.example.notepad.RoomDb.AppDatabase;
import com.example.notepad.RoomDb.Dao.NoteDAO;
import com.example.notepad.RoomDb.Entities.Note;
import com.example.notepad.clickItem.RecyclerOnItemClick;
import com.example.notepad.databinding.ActivityAddNoteBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddNote extends AppCompatActivity {
    private ActivityAddNoteBinding binding;
    ArrayList<Note> noteList = new ArrayList<>();
    private NoteDAO noteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Add Your Notes");
        noteDAO= Room.databaseBuilder(this,AppDatabase.class,"NODEDb").allowMainThreadQueries().build().noteDAO();

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToDb(
                  binding.title.getText().toString(),
                        binding.descriptionTv.getText().toString()
                );
                Intent intent=new Intent(AddNote.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void insertToDb(String title, String description){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {


                Note note=new Note();
                note.setTitle_tv(title);
                note.setDescription_tv(description);
                note.setDate(getTotadydate());
                AppDatabase.getDatabase(AddNote.this).noteDAO().insert(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddNote.this, "Value Inserted Successfull ", Toast.LENGTH_SHORT).show();
                        binding.title.setText("");
                        binding.descriptionTv.setText("");
                        binding.title.requestFocus();

                    }
                });



            }
        });

    }



    public  String getTotadydate(){
        Date todaysdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(todaysdate);
        return date;
    }
}