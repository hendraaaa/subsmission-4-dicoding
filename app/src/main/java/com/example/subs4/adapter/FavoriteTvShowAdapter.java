package com.example.subs4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.subs4.R;
import com.example.subs4.entity.Movies;
import com.example.subs4.entity.TvShow;

import java.util.ArrayList;

public class FavoriteTvShowAdapter extends RecyclerView.Adapter<FavoriteTvShowAdapter.FavoriteTvShowViewHolder> {
    private ArrayList<TvShow>listTvShow;
    private Context context;
    public FavoriteTvShowAdapter(Context context){
        this.context = context;
    }
    public void setTvshow(ArrayList<TvShow> tvshow) {
        this.listTvShow = tvshow;
    }
    public void setListTvShows(ArrayList<TvShow>listTvShow){
        if (listTvShow.size() > 0){
            this.listTvShow.clear();
        }
        this.listTvShow.addAll(listTvShow);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FavoriteTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_favorite_tvshow,parent,false);
        return new FavoriteTvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvShowViewHolder holder, int position) {
        holder.tvJudul.setText(listTvShow.get(position).getTitleTv());
        holder.tvDesc.setText(listTvShow.get(position).getDescTv());
        holder.tvVote.setText(listTvShow.get(position).getVoteTv());
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185" + listTvShow.get(position).getPosterTv())
                .apply(new RequestOptions().override(150,220))
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listTvShow.size();
    }

    public class FavoriteTvShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvJudul,tvDesc,tvVote;
        public FavoriteTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_posterfavTvShow);
            tvJudul = itemView.findViewById(R.id.tv_titlefavTvShow);
            tvDesc = itemView.findViewById(R.id.tv_descriptionfavTvShow);
            tvVote = itemView.findViewById(R.id.tv_voteFavTvShow);
        }
    }
}
