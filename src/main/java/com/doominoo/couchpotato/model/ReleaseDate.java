
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ReleaseDate {

    @SerializedName("bluray")
    private Boolean mBluray;
    @SerializedName("dvd")
    private Long mDvd;
    @SerializedName("expires")
    private Long mExpires;
    @SerializedName("theater")
    private Long mTheater;

    public Boolean getBluray() {
        return mBluray;
    }

    public Long getDvd() {
        return mDvd;
    }

    public Long getExpires() {
        return mExpires;
    }

    public Long getTheater() {
        return mTheater;
    }

    public static class Builder {

        private Boolean mBluray;
        private Long mDvd;
        private Long mExpires;
        private Long mTheater;

        public ReleaseDate.Builder withBluray(Boolean bluray) {
            mBluray = bluray;
            return this;
        }

        public ReleaseDate.Builder withDvd(Long dvd) {
            mDvd = dvd;
            return this;
        }

        public ReleaseDate.Builder withExpires(Long expires) {
            mExpires = expires;
            return this;
        }

        public ReleaseDate.Builder withTheater(Long theater) {
            mTheater = theater;
            return this;
        }

        public ReleaseDate build() {
            ReleaseDate ReleaseDate = new ReleaseDate();
            ReleaseDate.mBluray = mBluray;
            ReleaseDate.mDvd = mDvd;
            ReleaseDate.mExpires = mExpires;
            ReleaseDate.mTheater = mTheater;
            return ReleaseDate;
        }

    }

}
