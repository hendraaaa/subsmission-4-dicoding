package com.example.subs4.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    private int idMovie;
    private String titleMovie;
    private String descMovie;
    private String releaseDateMovie;
    private String posterMovie;
    private String backdropMovie;
    private String voteMovie;

    protected Movies(Parcel in) {
        idMovie = in.readInt();
        titleMovie = in.readString();
        descMovie = in.readString();
        releaseDateMovie = in.readString();
        posterMovie = in.readString();
        backdropMovie = in.readString();
        voteMovie = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getDescMovie() {
        return descMovie;
    }

    public void setDescMovie(String descMovie) {
        this.descMovie = descMovie;
    }

    public String getReleaseDateMovie() {
        return releaseDateMovie;
    }

    public void setReleaseDateMovie(String releaseDateMovie) {
        this.releaseDateMovie = releaseDateMovie;
    }

    public String getPosterMovie() {
        return posterMovie;
    }

    public void setPosterMovie(String posterMovie) {
        this.posterMovie = posterMovie;
    }

    public String getBackdropMovie() {
        return backdropMovie;
    }

    public void setBackdropMovie(String backdropMovie) {
        this.backdropMovie = backdropMovie;
    }

    public String getVoteMovie() {
        return voteMovie;
    }

    public void setVoteMovie(String voteMovie) {
        this.voteMovie = voteMovie;
    }
    public Movies(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idMovie);
        parcel.writeString(titleMovie);
        parcel.writeString(descMovie);
        parcel.writeString(releaseDateMovie);
        parcel.writeString(posterMovie);
        parcel.writeString(backdropMovie);
        parcel.writeString(voteMovie);
    }
}
