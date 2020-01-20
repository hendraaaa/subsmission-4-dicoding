package com.example.subs4.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int idTv;
    private String titleTv;
    private String descTv;
    private String genreTv;
    private String posterTv;
    private String backdropTv;
    private String voteTv;

    protected TvShow(Parcel in) {
        idTv = in.readInt();
        titleTv = in.readString();
        descTv = in.readString();
        genreTv = in.readString();
        posterTv = in.readString();
        backdropTv = in.readString();
        voteTv = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public int getIdTv() {
        return idTv;
    }

    public void setIdTv(int idTv) {
        this.idTv = idTv;
    }

    public String getTitleTv() {
        return titleTv;
    }

    public void setTitleTv(String titleTv) {
        this.titleTv = titleTv;
    }

    public String getDescTv() {
        return descTv;
    }

    public void setDescTv(String descTv) {
        this.descTv = descTv;
    }

    public String getGenreTv() {
        return genreTv;
    }

    public void setGenreTv(String releaseDateTv) {
        this.genreTv = releaseDateTv;
    }

    public String getPosterTv() {
        return posterTv;
    }

    public void setPosterTv(String posterTv) {
        this.posterTv = posterTv;
    }

    public String getBackdropTv() {
        return backdropTv;
    }

    public void setBackdropTv(String backdropTv) {
        this.backdropTv = backdropTv;
    }

    public String getVoteTv() {
        return voteTv;
    }

    public void setVoteTv(String voteTv) {
        this.voteTv = voteTv;
    }
    public TvShow(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idTv);
        parcel.writeString(titleTv);
        parcel.writeString(descTv);
        parcel.writeString(genreTv);
        parcel.writeString(posterTv);
        parcel.writeString(backdropTv);
        parcel.writeString(voteTv);
    }
}
