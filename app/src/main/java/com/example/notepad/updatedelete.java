package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.notepad.Adapter.RecyclerViewAdapter;
import com.example.notepad.RoomDb.AppDatabase;
import com.example.notepad.RoomDb.Dao.NoteDAO;
import com.example.notepad.RoomDb.Entities.Note;
import com.example.notepad.databinding.ActivityUpdatedeleteBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class updatedelete extends AppCompatActivity {
    private ActivityUpdatedeleteBinding binding;
    ArrayList<Note>  noteList=new ArrayList<>();
    private NoteDAO noteDAO;
    RecyclerViewAdapter adapter;
    String selecteddate;
    int selectednoteId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityUpdatedeleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new RecyclerViewAdapter(noteList);
        //noteDAO= Room.databaseBuilder(this,AppDatabase.class,"NODEDb").allowMainThreadQueries().build().noteDAO();

        Intent intent=getIntent();
        if(intent!=null){
            binding.dateTv.setText(intent.getStringExtra("Date"));
            binding.titletv.setText(intent.getStringExtra("Title"));
            binding.description.setText(intent.getStringExtra("description"));


        }
    binding.update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int noteId=getIntent().getIntExtra("NoteId",0);
            Log.i("JAPAN", String.valueOf(noteId));
            update(noteId,binding.titletv.getText().toString(),
                    binding.description.getText().toString());
            Toast.makeText(updatedelete.this, "updated", Toast.LENGTH_SHORT).show();

        }
    });
       binding.delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int noteId=getIntent().getIntExtra("NoteId",0);
               /*deleteData(selecteddate);*/
               delete(noteId);
               adapter.notifyDataSetChanged();
           }
       });
    }



    private void delete( int noteId){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                AppDatabase.getDatabase(updatedelete.this).noteDAO().delete(noteId);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getDataToDb();
                        Toast.makeText(updatedelete.this, "Item Deleted SuccessFully", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

    }

    private void update(int noteId, String title_tv,String description_tv){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                AppDatabase.getDatabase(updatedelete.this).noteDAO().update(noteId,title_tv,description_tv);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getDataToDb();
                        Toast.makeText(updatedelete.this, "Data Updated SuccessFully", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

    }

    private void getDataToDb() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Note> dbnoteList = AppDatabase.getDatabase(updatedelete.this).noteDAO().noteList();


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        noteList.clear();
                        noteList.addAll(dbnoteList);
                        Toast.makeText(updatedelete.this, "" + noteList.size(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }




}




