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
import com.example.notepad.RoomDb.DisplayDescription;
import com.example.notepad.RoomDb.Entities.Note;
import com.example.notepad.clickItem.RecyclerOnItemClick;
import com.example.notepad.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<Note> noteList = new ArrayList<>();
    ArrayList<Note> userArrayList=new ArrayList<>();
    RecyclerViewAdapter adapter;
    int SelectednoteId = 0;
    private NoteDAO noteDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // noteDAO= Room.databaseBuilder(this,AppDatabase.class,"NODEDb").allowMainThreadQueries().build().noteDAO();
        adapter = new RecyclerViewAdapter(noteList);
        binding.rvView.setAdapter(adapter);
        getDataToDb();


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddNote.class);
                startActivity(intent);
            }
        });
        adapter.setRecyclerViewclickLisnter(new RecyclerOnItemClick() {

            @Override
            public void clickListener2(Note note) {
               // Toast.makeText(MainActivity.this,note.getDescription_tv(),Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MainActivity.this,DisplayDescription.class);
                intent.putExtra("Title",note.title_tv);
                intent.putExtra("description",note.description_tv);
                startActivity(intent);
            }

            @Override
            public void editNodeListener(Note note) {
                //Toast.makeText(MainActivity.this,note.getDate(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,updatedelete.class);
                intent.putExtra("Date",note.date);
                intent.putExtra("NoteId",note.noteId);
                intent.putExtra("Title",note.title_tv);
                intent.putExtra("description",note.description_tv);
                startActivity(intent);

            }

            @Override
            public void deleteNodeListener(Note note) {

                delete(note.noteId);
                noteList.remove(note);
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void getDataToDb() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Note> dbnoteList = AppDatabase.getDatabase(MainActivity.this).noteDAO().noteList();


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       noteList.clear();
                        noteList.addAll(dbnoteList);
                        adapter.notifyDataSetChanged();
                       // Toast.makeText(MainActivity.this, "" + noteList.size(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }


    private void delete( int noteId){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                AppDatabase.getDatabase(MainActivity.this).noteDAO().delete(noteId);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getDataToDb();
                        Toast.makeText(MainActivity.this, "Item Deleted SuccessFully", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}