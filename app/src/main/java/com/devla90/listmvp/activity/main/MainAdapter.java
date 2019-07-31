package com.devla90.listmvp.activity.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devla90.listmvp.R;
import com.devla90.listmvp.model.Note;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapater> {

    private Context context;
    private List<Note> notes;
    private ItemClickListener itemClickListener;


    public MainAdapter(Context context, List<Note> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapater onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note,viewGroup,false);
        return new RecyclerViewAdapater(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapater holder, int position) {

        Note note = notes.get(position);
        holder.textViewTitle.setText(note.getNombre());
        holder.textViewDesc.setText(note.getDetalle());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class RecyclerViewAdapater extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewTitle, textViewDesc;
        CardView cardView;
        ItemClickListener itemClickListener;

        RecyclerViewAdapater(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            cardView = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
