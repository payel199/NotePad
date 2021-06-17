package com.example.notepad.RoomDb.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
        public  int noteId;

        @ColumnInfo(name = "title_tv")
        public String  title_tv;

        @ColumnInfo(name = "description_tv")
        public String description_tv;

    @ColumnInfo(name = "date")
    public String date;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle_tv() {
        return title_tv;
    }

    public void setTitle_tv(String title_tv) {
        this.title_tv = title_tv;
    }

    public String getDescription_tv() {
        return description_tv;
    }

    public void setDescription_tv(String description_tv) {
        this.description_tv = description_tv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
