package com.example.subs4.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.subs4.DetailActivity;
import com.example.subs4.DetailActivityTvShow;
import com.example.subs4.R;
import com.example.subs4.entity.Movies;
import com.example.subs4.entity.TvShow;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private ArrayList<TvShow> mData = new ArrayList<>();
    public void setData(ArrayList<TvShow>items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_tvshow,parent,false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvShowViewHolder holder, int position) {
        holder.bind(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivityTvShow.class);
                intent.putExtra(DetailActivity.EXTRA_DATA,mData.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewTvShow;
        private TextView tvRating,tvTitle;
        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewTvShow = itemView.findViewById(R.id.img_posterTv);
            tvRating = itemView.findViewById(R.id.tv_ratingTv);
            tvTitle = itemView.findViewById(R.id.tvTitleTv);
        }
        void bind(TvShow tvShow){
            String url_image = "https://image.tmdb.org/t/p/original" + tvShow.getPosterTv();
            tvRating.setText(tvShow.getVoteTv());
            tvTitle.setText(tvShow.getTitleTv());
            Glide.with(itemView.getContext())
                    .load(url_image)
                    .apply(new RequestOptions().override(120,250))
                    .placeholder(R.drawable.loading)
                    .into(imageViewTvShow);
        }
    }
}
