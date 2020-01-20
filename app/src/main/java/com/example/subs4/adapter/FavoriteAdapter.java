package com.example.subs4.adapter;

import android.content.Context;
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
import com.example.subs4.R;
import com.example.subs4.entity.Movies;
import com.example.subs4.ui.notifications.CustomOnItemClickListener;

import java.util.ArrayList;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private ArrayList<Movies> listMovies;
    private Context context;
    public FavoriteAdapter(Context context) {
        this.context = context;
    }
    public void setMovies(ArrayList<Movies> movies) {
        this.listMovies = movies;
    }


    public void setListMovies(ArrayList<Movies> listMovies) {
        if (listMovies.size() > 0){
            this.listMovies.clear();

        }
        this.listMovies.addAll(listMovies);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_favorite,parent,false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteViewHolder holder, final int position) {
        holder.tvJudul.setText(listMovies.get(position).getTitleMovie());
        holder.tvDesc.setText(listMovies.get(position).getDescMovie());
        holder.tvRilis.setText(listMovies.get(position).getReleaseDateMovie());
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185"+listMovies.get(position).getPosterMovie())
                .apply(new RequestOptions().override(150,220))
                .into(holder.imgPoster);
        holder.itemView.setOnLongClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(holder.itemView.getContext(),"hello",Toast.LENGTH_SHORT).show();
            }
        }));
    }



    @Override
    public int getItemCount() {
       return listMovies.size();

    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPoster;
        private TextView tvJudul,tvDesc,tvRilis;
        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_posterfav);
            tvJudul = itemView.findViewById(R.id.tv_titlefav);
            tvDesc = itemView.findViewById(R.id.tv_descriptionfav);
            tvRilis = itemView.findViewById(R.id.tv_releaseFav);
        }
    }
}
