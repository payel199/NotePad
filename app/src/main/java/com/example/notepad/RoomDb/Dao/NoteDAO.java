package com.example.notepad.RoomDb.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notepad.RoomDb.Entities.Note;

import java.util.List;

@Dao
public interface NoteDAO {

        @Insert
        public void insert(Note note);

        @Query("SELECT * FROM note")
        public List<Note> noteList();

        @Query("UPDATE note SET title_tv =:title_tv AND description_tv=:description_tv WHERE noteId = :noteId")
        public void update(int noteId, String title_tv,String description_tv);

        @Query("DELETE FROM note WHERE noteId =:noteId")
        public void delete(int noteId);





    }
