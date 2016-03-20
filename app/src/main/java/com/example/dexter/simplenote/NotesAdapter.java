package com.example.dexter.simplenote;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dexter.simplenote.DB.Note;

import java.util.List;

/**
 * Created by Dexter on 3/21/2016.
 */
public class NotesAdapter extends RecyclerView.Adapter <NotesAdapter.MyViewHolder> {

    private List<Note> noteList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView content, datetime;
        public CardView card;

        public MyViewHolder(View view) {
            super(view);
            content = (TextView) view.findViewById(R.id.infoContent);
            datetime = (TextView) view.findViewById(R.id.datetime);
            card = (CardView)view.findViewById(R.id.infocard);
        }
    }
    public NotesAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notecard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.content.setText(note.getNote());
        holder.datetime.setText(note.getDateTime());
        holder.card.setBackgroundColor(Color.parseColor(note.getColor()));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

}
