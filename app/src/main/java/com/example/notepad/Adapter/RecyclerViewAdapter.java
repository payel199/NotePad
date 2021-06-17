package com.example.notepad.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.RoomDb.Entities.Note;
import com.example.notepad.clickItem.RecyclerOnItemClick;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Note> noteList = new ArrayList<>();
    RecyclerOnItemClick recyclerOnItemClick;
    RecyclerOnItemClick deleteItemClick;
    RecyclerOnItemClick editItemClick;
    RecyclerOnItemClick listner2;

    public RecyclerViewAdapter(ArrayList<Note> noteList) {
        this.noteList =noteList;
    }

    public void setRecyclerViewclickLisnter(RecyclerOnItemClick listner2){

        this.listner2=listner2;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note=noteList.get(position);
        holder.title_tv.setText(note.getTitle_tv());
        
        holder.date.setText(note.getDate());

      /* holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerOnItemClick.clickListener(position);
            }
        });*/

      /*  holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItemClick.clickListener(position);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItemClick.clickListener(position);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView title_tv,description_tv,date;
        ConstraintLayout constraintLayout;
        ImageButton delete,edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv=itemView.findViewById(R.id.title);
            description_tv=itemView.findViewById(R.id.description_tv);
            date=itemView.findViewById(R.id.datecurrent);
            constraintLayout=itemView.findViewById(R.id.constraint);
            delete=itemView.findViewById(R.id.delete);
            edit=itemView.findViewById(R.id.upload);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner2!=null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listner2.clickListener2(noteList.get(getAdapterPosition()));
                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner2!=null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listner2.deleteNodeListener(noteList.get(getAdapterPosition()));
                    }
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner2!=null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listner2.editNodeListener(noteList.get(getAdapterPosition()));
                    }
                }
            });

        }
    }
}
