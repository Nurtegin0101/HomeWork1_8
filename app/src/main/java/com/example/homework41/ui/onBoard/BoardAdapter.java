package com.example.homework41.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework41.R;
import com.example.homework41.databinding.PageBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private String[] list = {"1", "2", "3", "4"};
    private int[] imgList = {R.raw.workout_santa, R.raw.foody, R.raw.loading_circles, R.raw.perfect_loop_loading};
    private PageBoardBinding binding;

    protected ClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PageBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.onBoard(position);
        binding.btnBoard.setOnClickListener(v -> {
            listener.click();
        });
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBoard(int position) {
            binding.boardFirstTv.setText(list[position]);
            binding.boardImgView.setAnimation(imgList[position]);
            binding.boardImgView.playAnimation();
            if (position == imgList.length-1) {
                binding.btnBoard.setVisibility(View.VISIBLE);
            }
        }
    }

    interface ClickListener {
        void click();
    }
}

