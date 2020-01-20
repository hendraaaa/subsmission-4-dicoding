package com.example.subs4.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.subs4.BuildConfig;
import com.example.subs4.entity.Movies;
import com.example.subs4.entity.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowModel extends ViewModel {
    private static final String API_KEY = BuildConfig.TheMovieDBAPI;
    private MutableLiveData<ArrayList<TvShow>> listTvShow = new MutableLiveData<>();

    public void setTvShow(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listItems = new ArrayList<>();
        String url = "http://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String reuslt = new String(responseBody);
                    JSONObject responObject = new JSONObject(reuslt);
                    JSONArray list = responObject.getJSONArray("results");

                    for (int i = 0; i < list.length();i++){
                        JSONObject tvShows = list.getJSONObject(i);
                        TvShow tvShow = new TvShow();
                        tvShow.setIdTv(tvShows.getInt("id"));
                        tvShow.setTitleTv(tvShows.getString("original_name"));
                        tvShow.setDescTv(tvShows.getString("overview"));
                        tvShow.setBackdropTv(tvShows.getString("backdrop_path"));
                        tvShow.setPosterTv(tvShows.getString("poster_path"));
                        tvShow.setVoteTv(tvShows.getString("vote_count"));
                        listItems.add(tvShow);
                    }
                    listTvShow.postValue(listItems);
                }catch (JSONException e){
                    Log.d("onSuccess1: ",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure: ",error.getMessage());

            }
        });
    }
    public LiveData<ArrayList<TvShow>> getTvShow(){
        return listTvShow;
    }
}
