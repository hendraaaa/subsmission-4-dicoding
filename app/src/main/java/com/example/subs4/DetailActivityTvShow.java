package com.example.subs4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.subs4.db.TvShowHelper;
import com.example.subs4.entity.Movies;
import com.example.subs4.entity.TvShow;

import java.util.ArrayList;

public class DetailActivityTvShow extends AppCompatActivity {
    private ImageView imageViewPoster,imageViewBackdrop;
    private TextView tvTitle,tvRating,tvDesc;
    private TvShow tvShow;
    public static final String EXTRA_DATA = "extra_data";
    private TvShowHelper tvShowHelper;
    private boolean isFavorite = false;
    private Menu menu;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        toolbar = findViewById(R.id.toolbardetailtvShow);
        toolbar.setTitle("Detail");
        setSupportActionBar(toolbar);
        imageViewPoster = findViewById(R.id.imagePosterDetailTvShow);
        imageViewBackdrop = findViewById(R.id.imageBackdropDetailtvShow);
        tvTitle = findViewById(R.id.tvTitle_detailTvShow);
        tvRating = findViewById(R.id.tv_rating_detailTvShow);
        tvDesc = findViewById(R.id.tvDescdetailTvShow);
        tvShow = getIntent().getParcelableExtra(EXTRA_DATA);
        tvShowHelper = TvShowHelper.getInstance(getApplicationContext());
        tvShowHelper.open();
        detail();
        checkFavorite();
    }
    private void detail(){
        if (tvShow != null){
            tvTitle.setText(tvShow.getTitleTv());
            tvDesc.setText(tvShow.getDescTv());
            tvRating.setText(tvShow.getVoteTv());
            String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPosterTv();
            String url_imageBack = "https://image.tmdb.org/t/p/w185" + tvShow.getBackdropTv();

            Glide.with(this)
                    .load(url_image)
                    .apply(new RequestOptions().override(250, 230))
                    .into(imageViewPoster);
            Glide.with(this)
                    .load(url_imageBack)
                    .apply(new RequestOptions().override(100, 130))
                    .into(imageViewBackdrop);

        }

    }
    private void checkFavorite() {
        ArrayList<TvShow> tvshowInDatabase = tvShowHelper.getAllTvShows();
        for (TvShow tvShow : tvshowInDatabase) {
            if (this.tvShow.getIdTv() == tvShow.getIdTv()) {
                isFavorite = true;
            }
            if (isFavorite == true) {
                break;
            }
        }
    }
    private void favoriteButtonPressed() {
        if (isFavorite) {
            tvShowHelper.deleteById(tvShow.getIdTv());
            Toast.makeText(DetailActivityTvShow.this,getResources().getString(R.string.hapus)+ " " + tvShow.getTitleTv(),Toast.LENGTH_LONG).show();

        } else {
           tvShowHelper.insert(tvShow);
           Toast.makeText(DetailActivityTvShow.this,tvShow.getTitleTv()+ " " + getResources().getString(R.string.tambah),Toast.LENGTH_LONG).show();
        }
        isFavorite = !isFavorite;
        setIconFavorite();
    }
    private void setIconFavorite(){
        if (isFavorite) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.heart));

        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.heart1));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        this.menu = menu;
        setIconFavorite();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.love)
            favoriteButtonPressed();
        return super.onOptionsItemSelected(item);
    }
}
