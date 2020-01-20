package com.example.subs4.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subs4.R;
import com.example.subs4.adapter.FavoriteAdapter;
import com.example.subs4.db.MovieHelper;
import com.example.subs4.entity.Movies;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFav extends Fragment {
    private RecyclerView recyclerView;
    private MovieHelper movieHelper;
    private FavoriteAdapter adapter;
    private ArrayList<Movies> movies = new ArrayList<>();
    FrameLayout frameLayout;


    public MovieFav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleViewmovie);
        frameLayout = view.findViewById(R.id.frame);
        movieHelper = MovieHelper.getInstance(view.getContext());
        movieHelper.open();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new FavoriteAdapter(view.getContext());
        adapter.setMovies(movies);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        movies  = movieHelper.getAllMovies();
        adapter.setListMovies(movies);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }
}
