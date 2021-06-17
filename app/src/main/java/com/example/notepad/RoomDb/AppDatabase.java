package com.example.notepad.RoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notepad.RoomDb.Dao.NoteDAO;
import com.example.notepad.RoomDb.Entities.Note;

@Database(entities = {Note.class},version =3,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract NoteDAO noteDAO();
    private   static  volatile  AppDatabase INSTANCE;
  public  static  AppDatabase getDatabase(Context context){
        if(INSTANCE==null){
            synchronized (AppDatabase.class){
                if(INSTANCE==null){
                    INSTANCE=Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"NotePad").build();
               }
            }
        }
       return INSTANCE;
   }
}




