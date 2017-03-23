
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Images {

    @SerializedName("actors")
    private HashMap<String, String> mActors;
    @SerializedName("backdrop")
    private List<String> mBackdrop;
    @SerializedName("backdrop_original")
    private List<String> mBackdropOriginal;
    @SerializedName("banner")
    private List<Object> mBanner;
    @SerializedName("clear_art")
    private List<Object> mClearArt;
    @SerializedName("disc_art")
    private List<Object> mDiscArt;
    @SerializedName("extra_fanart")
    private List<Object> mExtraFanart;
    @SerializedName("extra_thumbs")
    private List<Object> mExtraThumbs;
    @SerializedName("landscape")
    private List<Object> mLandscape;
    @SerializedName("logo")
    private List<Object> mLogo;
    @SerializedName("poster")
    private List<String> mPoster;
    @SerializedName("poster_original")
    private List<String> mPosterOriginal;

    public HashMap<String, String> getActors() {
        return mActors;
    }

    public List<String> getBackdrop() {
        return mBackdrop;
    }

    public List<String> getBackdropOriginal() {
        return mBackdropOriginal;
    }

    public List<Object> getBanner() {
        return mBanner;
    }

    public List<Object> getClearArt() {
        return mClearArt;
    }

    public List<Object> getDiscArt() {
        return mDiscArt;
    }

    public List<Object> getExtraFanart() {
        return mExtraFanart;
    }

    public List<Object> getExtraThumbs() {
        return mExtraThumbs;
    }

    public List<Object> getLandscape() {
        return mLandscape;
    }

    public List<Object> getLogo() {
        return mLogo;
    }

    public List<String> getPoster() {
        return mPoster;
    }

    public List<String> getPosterOriginal() {
        return mPosterOriginal;
    }

    public static class Builder {

        private HashMap<String, String> mActors;
        private List<String> mBackdrop;
        private List<String> mBackdropOriginal;
        private List<Object> mBanner;
        private List<Object> mClearArt;
        private List<Object> mDiscArt;
        private List<Object> mExtraFanart;
        private List<Object> mExtraThumbs;
        private List<Object> mLandscape;
        private List<Object> mLogo;
        private List<String> mPoster;
        private List<String> mPosterOriginal;

        public Images.Builder withActors(HashMap<String, String> actors) {
            mActors = actors;
            return this;
        }

        public Images.Builder withBackdrop(List<String> backdrop) {
            mBackdrop = backdrop;
            return this;
        }

        public Images.Builder withBackdropOriginal(List<String> backdropOriginal) {
            mBackdropOriginal = backdropOriginal;
            return this;
        }

        public Images.Builder withBanner(List<Object> banner) {
            mBanner = banner;
            return this;
        }

        public Images.Builder withClearArt(List<Object> clearArt) {
            mClearArt = clearArt;
            return this;
        }

        public Images.Builder withDiscArt(List<Object> discArt) {
            mDiscArt = discArt;
            return this;
        }

        public Images.Builder withExtraFanart(List<Object> extraFanart) {
            mExtraFanart = extraFanart;
            return this;
        }

        public Images.Builder withExtraThumbs(List<Object> extraThumbs) {
            mExtraThumbs = extraThumbs;
            return this;
        }

        public Images.Builder withLandscape(List<Object> landscape) {
            mLandscape = landscape;
            return this;
        }

        public Images.Builder withLogo(List<Object> logo) {
            mLogo = logo;
            return this;
        }

        public Images.Builder withPoster(List<String> poster) {
            mPoster = poster;
            return this;
        }

        public Images.Builder withPosterOriginal(List<String> posterOriginal) {
            mPosterOriginal = posterOriginal;
            return this;
        }

        public Images build() {
            Images Images = new Images();
            Images.mActors = mActors;
            Images.mBackdrop = mBackdrop;
            Images.mBackdropOriginal = mBackdropOriginal;
            Images.mBanner = mBanner;
            Images.mClearArt = mClearArt;
            Images.mDiscArt = mDiscArt;
            Images.mExtraFanart = mExtraFanart;
            Images.mExtraThumbs = mExtraThumbs;
            Images.mLandscape = mLandscape;
            Images.mLogo = mLogo;
            Images.mPoster = mPoster;
            Images.mPosterOriginal = mPosterOriginal;
            return Images;
        }

    }

}
