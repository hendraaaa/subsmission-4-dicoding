package com.example.subs4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.subs4.db.MovieHelper;
import com.example.subs4.entity.Movies;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_NOTE = "extra_note";
    private Toolbar toolbar;
    MovieHelper movieHelper;

    private ImageView imageViewPoster, imageViewBackground;
    private TextView tvtitle, tvCount, tvDesc;
    private Movies movies;
    boolean isFavorite = false;
    private Menu menu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = findViewById(R.id.toolbardetail);
        toolbar.setTitle("Detail");
        setSupportActionBar(toolbar);
        imageViewPoster = findViewById(R.id.imagePoster);
        imageViewBackground = findViewById(R.id.imageBackdrop);
        tvtitle = findViewById(R.id.tvTitle_detail);
        tvCount = findViewById(R.id.tv_rating_detail);
        tvDesc = findViewById(R.id.tvDesc);
        movies = getIntent().getParcelableExtra(EXTRA_DATA);


        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.open();
        detail();
        isFavorite = false;
        checkFavorite();



    }
    private void checkFavorite() {
        ArrayList<Movies> moviesInDatabase = movieHelper.getAllMovies();
        for (Movies movies : moviesInDatabase) {
            if (this.movies.getIdMovie() == movies.getIdMovie()) {
                isFavorite = true;
            }
            if (isFavorite == true) {
                break;
            }
        }
    }
        private void favoriteButtonPressed() {
            if (isFavorite) {
                movieHelper.deleteById(movies.getIdMovie());
                Toast.makeText(DetailActivity.this,getResources().getString(R.string.hapus)+ " " + movies.getTitleMovie(),Toast.LENGTH_LONG).show();

            } else {
                movieHelper.insert(movies);
                Toast.makeText(DetailActivity.this,movies.getTitleMovie()+ " " + getResources().getString(R.string.tambah),Toast.LENGTH_LONG).show();


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
    private void detail(){
        if (movies != null) {
            tvtitle.setText(movies.getTitleMovie());
            tvDesc.setText(movies.getDescMovie());
            tvCount.setText(movies.getVoteMovie());
            String url_image = "https://image.tmdb.org/t/p/w185" + movies.getPosterMovie();
            String url_imageBack = "https://image.tmdb.org/t/p/w185" + movies.getBackdropMovie();

            Glide.with(this)
                    .load(url_image)
                    .apply(new RequestOptions().override(250, 230))
                    .into(imageViewPoster);
            Glide.with(this)
                    .load(url_imageBack)
                    .apply(new RequestOptions().override(100, 130))
                    .into(imageViewBackground);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;

        setIconFavorite();
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.love){
            favoriteButtonPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}


