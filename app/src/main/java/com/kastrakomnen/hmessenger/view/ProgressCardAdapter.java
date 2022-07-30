package com.kastrakomnen.hmessenger.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kastrakomnen.hmessenger.R;

import java.util.List;

public class ProgressCardAdapter extends RecyclerView.Adapter<ProgressCardAdapter.ViewHolder> {

    private ItemClickListener itemClickListener;

    List<ProgressCard> progressCards;

    public ProgressCardAdapter(List<ProgressCard> progressCards){
        this.progressCards = progressCards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View progress_card = inflater.inflate(R.layout.layout_progress_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(progress_card);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Fill data in here
        ProgressCard progressCard = progressCards.get(position);

        holder.tv_chapter_no.setText(Integer.toString(progressCard.getIndex()));

        if (progressCard.isLocked()){

            /* How the box will look */
            holder.box.setImageResource(R.drawable.brick_style_shady_locked);

            /* How the stars will look */
            holder.star1.setImageResource(R.drawable.star_dead);
            holder.star2.setImageResource(R.drawable.star_dead);
            holder.star3.setImageResource(R.drawable.star_dead);

            /* How the info will look */
            holder.tv_hi_score.setVisibility(View.INVISIBLE);
            holder.tv_hi_score_title.setVisibility(View.INVISIBLE);

            holder.setItemClickListener(null);
        }else{
            holder.box.setImageResource(R.drawable.brick_style_shady_red);

            if (progressCard.getEarnStar() == 0){
                holder.star1.setImageResource(R.drawable.star_dead);
                holder.star2.setImageResource(R.drawable.star_dead);
                holder.star3.setImageResource(R.drawable.star_dead);
            }else if (progressCard.getEarnStar() == 1){
                holder.star2.setImageResource(R.drawable.star_dead);
                holder.star3.setImageResource(R.drawable.star_dead);
            }else if (progressCard.getEarnStar() == 2){
                holder.star2.setImageResource(R.drawable.star_dead);
            }

            holder.tv_hi_score.setText(Integer.toString(progressCard.getHighScore()));

            holder.setItemClickListener(itemClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return progressCards.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView box;
        ImageView star1;
        ImageView star2;
        ImageView star3;

        TextView tv_chapter_no;
        TextView tv_hi_score;
        TextView tv_hi_score_title;

        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            box         = itemView.findViewById(R.id.iv_box);
            star1       = itemView.findViewById(R.id.iv_star_1);
            star2       = itemView.findViewById(R.id.iv_star_2);
            star3       = itemView.findViewById(R.id.iv_star_3);
            tv_chapter_no = itemView.findViewById(R.id.tv_chapter_no);
            tv_hi_score   = itemView.findViewById(R.id.tv_hi_score);
            tv_hi_score_title   = itemView.findViewById(R.id.tv_high_score_title);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onClick(view, getAdapterPosition());
        }
    }
}
