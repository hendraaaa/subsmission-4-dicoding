package com.example.subs4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.subs4.entity.Movies;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.subs4.db.DataBaseContract.MovieColumns.DESKRIPSI;
import static com.example.subs4.db.DataBaseContract.MovieColumns.GAMBAR;
import static com.example.subs4.db.DataBaseContract.MovieColumns.JUDUL;
import static com.example.subs4.db.DataBaseContract.MovieColumns.RILIS;
import static com.example.subs4.db.DataBaseContract.MovieColumns.TABEL;

public class MovieHelper {
    private static String DATABASE_TABLE = TABEL;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDataBase;
    private static MovieHelper INSTANCE;
    private Movies movies;

    private MovieHelper(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        mDataBase = mDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        mDatabaseHelper.close();

        if (mDataBase.isOpen())
            mDataBase.close();
    }




    public long insert(Movies movies) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID,movies.getIdMovie());
        contentValues.put(JUDUL,movies.getTitleMovie());
        contentValues.put(DESKRIPSI,movies.getDescMovie());
        contentValues.put(RILIS,movies.getReleaseDateMovie());
        contentValues.put(GAMBAR,movies.getPosterMovie());
        Log.d("ded", "insert: ");
        return mDataBase.insert(DATABASE_TABLE, null, contentValues);
    }

    public int deleteById(int id) {
        Log.d("hapus", "deleteById: ");
        return mDataBase.delete(TABEL, _ID + " = '" + id + "'",null);
    }
    public ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> arrayList = new ArrayList<>();
        Cursor cursor = mDataBase.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " DESC");
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                movies = new Movies();
                movies.setIdMovie(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movies.setTitleMovie(cursor.getString(cursor.getColumnIndexOrThrow(JUDUL)));
                movies.setDescMovie(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                movies.setReleaseDateMovie(cursor.getString(cursor.getColumnIndexOrThrow(RILIS)));
                movies.setPosterMovie(cursor.getString(cursor.getColumnIndexOrThrow(GAMBAR)));
                arrayList.add(movies);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
}
