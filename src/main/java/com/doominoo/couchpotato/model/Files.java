
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Files {

    @SerializedName("image_poster")
    private List<String> mImagePoster;
    @SerializedName("leftover")
    private List<String> mLeftover;
    @SerializedName("movie")
    private List<String> mMovie;
    @SerializedName("nfo")
    private List<String> mNfo;
    @SerializedName("subtitle")
    private List<String> mSubtitle;
    @SerializedName("subtitle_extra")
    private List<String> mSubtitleExtra;
    @SerializedName("trailer")
    private List<String> mTrailer;

    public List<String> getImagePoster() {
        return mImagePoster;
    }

    public List<String> getLeftover() {
        return mLeftover;
    }

    public List<String> getMovie() {
        return mMovie;
    }

    public List<String> getNfo() {
        return mNfo;
    }

    public List<String> getSubtitle() {
        return mSubtitle;
    }

    public List<String> getSubtitleExtra() {
        return mSubtitleExtra;
    }

    public List<String> getTrailer() {
        return mTrailer;
    }

    public static class Builder {

        private List<String> mImagePoster;
        private List<String> mLeftover;
        private List<String> mMovie;
        private List<String> mNfo;
        private List<String> mSubtitle;
        private List<String> mSubtitleExtra;
        private List<String> mTrailer;

        public Files.Builder withImagePoster(List<String> imagePoster) {
            mImagePoster = imagePoster;
            return this;
        }

        public Files.Builder withLeftover(List<String> leftover) {
            mLeftover = leftover;
            return this;
        }

        public Files.Builder withMovie(List<String> movie) {
            mMovie = movie;
            return this;
        }

        public Files.Builder withNfo(List<String> nfo) {
            mNfo = nfo;
            return this;
        }

        public Files.Builder withSubtitle(List<String> subtitle) {
            mSubtitle = subtitle;
            return this;
        }

        public Files.Builder withSubtitleExtra(List<String> subtitleExtra) {
            mSubtitleExtra = subtitleExtra;
            return this;
        }

        public Files.Builder withTrailer(List<String> trailer) {
            mTrailer = trailer;
            return this;
        }

        public Files build() {
            Files Files = new Files();
            Files.mImagePoster = mImagePoster;
            Files.mLeftover = mLeftover;
            Files.mMovie = mMovie;
            Files.mNfo = mNfo;
            Files.mSubtitle = mSubtitle;
            Files.mSubtitleExtra = mSubtitleExtra;
            Files.mTrailer = mTrailer;
            return Files;
        }

    }

}
