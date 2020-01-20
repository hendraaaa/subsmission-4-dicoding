package com.example.subs4.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subs4.R;
import com.example.subs4.adapter.FavoriteTvShowAdapter;
import com.example.subs4.db.TvShowHelper;
import com.example.subs4.entity.TvShow;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFav extends Fragment {
    private RecyclerView recyclerView;
    private TvShowHelper tvShowHelper;
    private FavoriteTvShowAdapter adapter;
    private ArrayList<TvShow> tvShows = new ArrayList<>();


    public TvShowFav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleViewFavTvshow);
        tvShowHelper = TvShowHelper.getInstance(view.getContext());
        tvShowHelper.open();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new FavoriteTvShowAdapter(view.getContext());
        adapter.setTvshow(tvShows);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvShows = tvShowHelper.getAllTvShows();
        adapter.setListTvShows(tvShows);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvShowHelper.close();
    }
}

