package com.example.subs4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.subs4.db.DataBaseContract.MovieColumns.TABEL;
import static com.example.subs4.db.DataBaseContract.TvShowColumns.TABELTV;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbmovie";
    private static final int DATABSE_VERSION = 1;
    private static final String SQL_CREATE_TABLE = String.format("CREATE TABLE %s" +
            "(%s TEXT NOT NULL," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL)",
            TABEL,
            DataBaseContract.MovieColumns._ID,
            DataBaseContract.MovieColumns.JUDUL,
            DataBaseContract.MovieColumns.DESKRIPSI,
            DataBaseContract.MovieColumns.RILIS,
            DataBaseContract.MovieColumns.GAMBAR
    );
    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s" +
            "(%s TEXT NOT NULL," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL," +
            "%s TEXT NOT NULL)",
            TABELTV,
            DataBaseContract.TvShowColumns._ID,
            DataBaseContract.TvShowColumns.JUDULTV,
            DataBaseContract.TvShowColumns.DESKRIPSITV,
            DataBaseContract.TvShowColumns.VOTETV,
            DataBaseContract.TvShowColumns.GAMBARTV
    );
    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TV);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABEL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABELTV);
        onCreate(sqLiteDatabase);


    }
}
