package com.example.ndh.myroom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView NoteItemView;
        private NoteClickListener noteClickListener;

        private NoteViewHolder(View itemView) {
            super(itemView);
            NoteItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        public void setNoteClickListener(NoteClickListener noteClickListener)
        {
            this.noteClickListener = noteClickListener;
        }

        @Override
        public void onClick(View v) {
            noteClickListener.onClick(v,getAdapterPosition());
        }
    }

    private final LayoutInflater mInflater;
    private List<Note> mNotes; // Cached copy of Notes

    NoteListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            holder.NoteItemView.setText(current.getNote());

            holder.setNoteClickListener(new NoteClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Note temp = mNotes.get(position);
                    //Log.d("mydummytest", temp.getNote());
                    MainActivity.mNoteViewModel.deleteByNote(temp);
                    notifyDataSetChanged();
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.NoteItemView.setText("No Note");
        }
    }

    void setNotes(List<Note> Notes){
        mNotes = Notes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mNotes has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }
}