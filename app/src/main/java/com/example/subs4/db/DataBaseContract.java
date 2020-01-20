package com.example.subs4.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DataBaseContract {


    public static final class MovieColumns implements BaseColumns{
        public static String TABEL = "movie";
        public static String JUDUL = "title";
        public static String DESKRIPSI = "overview";
        public static String RILIS = "release_date";
        public static String GAMBAR = "image_poster";


    }
    public static final class TvShowColumns implements BaseColumns{
        public static String TABELTV = "tvshow";
        public static String JUDULTV = "title";
        public static String DESKRIPSITV = "overview";
        public static String VOTETV = "release_date";
        public static String GAMBARTV = "image_poster";
    }
}
