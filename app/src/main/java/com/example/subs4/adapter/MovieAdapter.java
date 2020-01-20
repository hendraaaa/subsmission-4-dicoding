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
import com.example.subs4.R;
import com.example.subs4.entity.Movies;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movies> mData = new ArrayList<>();
    public void setData(ArrayList<Movies>items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        holder.bind(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA,mData.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvRating,tvTitle;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_poster);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
        void bind(Movies movies){
            String url_image = "https://image.tmdb.org/t/p/w185" + movies.getPosterMovie();
            tvRating.setText(movies.getVoteMovie());
            tvTitle.setText(movies.getTitleMovie());
            Glide.with(itemView.getContext())
                    .load(url_image)
                    .apply(new RequestOptions().override(120,250))
                    .into(imageView);
        }
    }
}
