package com.example.homework41.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework41.databinding.ItemRvBinding;
import com.example.homework41.ui.App;
import com.example.homework41.ui.form.FormModel;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ItemRvBinding binding;
    List<FormModel> list = new ArrayList<>();
    private LayoutInflater inflater;
    private onItemClick click;
    private FormModel editModel;
    private EditNoteItem editNoteItem;

    public TaskAdapter(Context context, EditNoteItem editNoteItem) {
        this.inflater = LayoutInflater.from(context);
        this.editNoteItem = editNoteItem;
    }

    public void removeNote(int position) {
        App.db.noteDao().delete(list.remove(position));
        notifyItemRemoved(position);
    }

    void setList(List<FormModel> list) {
        this.list = list;
    }

    public void setListener(onItemClick click) {
        this.click = click;
    }

    public List<FormModel> getList() {
        return list;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        binding.txtTitle.setText(list.get(position).getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editNoteItem.editClil(position);
                editModel = list.get(holder.getBindingAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(v -> {
                click.onLongClick(getAdapterPosition());
                return true;
            });
        }
    }

    interface onItemClick {
        void onLongClick(int position);
    }

    interface EditNoteItem {
        void editClil(int position);
    }
}
