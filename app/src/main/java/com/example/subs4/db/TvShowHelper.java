package com.example.subs4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.subs4.entity.TvShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.subs4.db.DataBaseContract.MovieColumns.TABEL;
import static com.example.subs4.db.DataBaseContract.TvShowColumns.DESKRIPSITV;
import static com.example.subs4.db.DataBaseContract.TvShowColumns.GAMBARTV;
import static com.example.subs4.db.DataBaseContract.TvShowColumns.JUDULTV;
import static com.example.subs4.db.DataBaseContract.TvShowColumns.TABELTV;
import static com.example.subs4.db.DataBaseContract.TvShowColumns.VOTETV;

public class TvShowHelper {
    private static String DATABASE_TABEL = TABELTV;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;
    private static TvShowHelper INSTANCE;
    private TvShow tvShow;

    private TvShowHelper(Context context){
        mDatabaseHelper = new DatabaseHelper(context);
    }
    public static TvShowHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new TvShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }
    public void open() throws SQLException{
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }
    public void close(){
        mDatabaseHelper.close();
        if (mDatabase.isOpen())
            mDatabase.close();
    }
    public long insert(TvShow tvShow) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID,tvShow.getIdTv());
        contentValues.put(JUDULTV,tvShow.getTitleTv());
        contentValues.put(DESKRIPSITV,tvShow.getDescTv());
        contentValues.put(VOTETV,tvShow.getVoteTv());
        contentValues.put(GAMBARTV,tvShow.getPosterTv());
        Log.d("tvShow", "insert: ");
        return mDatabase.insert(DATABASE_TABEL, null, contentValues);
    }
    public ArrayList<TvShow> getAllTvShows() {
        ArrayList<TvShow> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.query(DATABASE_TABEL, null,
                null,
                null,
                null,
                null,
                _ID + " DESC");
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                tvShow = new TvShow();
                tvShow.setIdTv(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                tvShow.setTitleTv(cursor.getString(cursor.getColumnIndexOrThrow(JUDULTV)));
                tvShow.setDescTv(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSITV)));
                tvShow.setVoteTv(cursor.getString(cursor.getColumnIndexOrThrow(VOTETV)));
                tvShow.setPosterTv(cursor.getString(cursor.getColumnIndexOrThrow(GAMBARTV)));
                arrayList.add(tvShow);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public int deleteById(int id) {
        Log.d("hapus", "deleteById: ");
        return mDatabase.delete(TABELTV, _ID + " = '" + id + "'",null);
    }
}
