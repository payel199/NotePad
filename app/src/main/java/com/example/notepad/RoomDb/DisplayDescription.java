package com.example.notepad.RoomDb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.notepad.Adapter.RecyclerViewAdapter;
import com.example.notepad.MainActivity;
import com.example.notepad.R;
import com.example.notepad.RoomDb.Entities.Note;
import com.example.notepad.clickItem.RecyclerOnItemClick;
import com.example.notepad.databinding.ActivityDisplayDescriptionBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisplayDescription extends AppCompatActivity {
    private ActivityDisplayDescriptionBinding binding;
    ArrayList<Note> noteList = new ArrayList<>();
    int SelectednoteId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("   Notes Details  ");
        Intent intent = getIntent();
        if (intent != null) {

            binding.tittletv.setText(intent.getStringExtra("Title"));
            binding.descriptiontv.setText(intent.getStringExtra("description"));
        }
    }
}