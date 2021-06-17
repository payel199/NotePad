package com.example.notepad.clickItem;

import com.example.notepad.RoomDb.Entities.Note;

public interface RecyclerOnItemClick {

    void clickListener2(Note note);
    void editNodeListener(Note note);
    void deleteNodeListener(Note note);
}
