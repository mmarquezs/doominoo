
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Info {

    @SerializedName("actor_roles")
    private HashMap<String, String> mActorRoles;
    @SerializedName("actors")
    private List<String> mActors;
    @SerializedName("directors")
    private List<String> mDirectors;
    @SerializedName("genres")
    private List<String> mGenres;
    @SerializedName("images")
    private Images mImages;
    @SerializedName("imdb")
    private String mImdb;
    @SerializedName("mpaa")
    private String mMpaa;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("plot")
    private String mPlot;
    @SerializedName("rating")
    private Rating mRating;
//    @SerializedName("release_date")
//    private List<ReleaseDate> mReleaseDate;
    @SerializedName("released")
    private String mReleased;
    @SerializedName("runtime")
    private Long mRuntime;
    @SerializedName("tagline")
    private String mTagline;
    @SerializedName("titles")
    private List<String> mTitles;
    @SerializedName("tmdb_id")
    private Long mTmdbId;
    @SerializedName("type")
    private String mType;
    @SerializedName("via_imdb")
    private Boolean mViaImdb;
    @SerializedName("via_tmdb")
    private Boolean mViaTmdb;
    @SerializedName("writers")
    private List<String> mWriters;
    @SerializedName("year")
    private Long mYear;

    public HashMap<String, String> getActorRoles() {
        return mActorRoles;
    }

    public List<String> getActors() {
        return mActors;
    }

    public List<String> getDirectors() {
        return mDirectors;
    }

    public List<String> getGenres() {
        return mGenres;
    }

    public Images getImages() {
        return mImages;
    }

    public String getImdb() {
        return mImdb;
    }

    public String getMpaa() {
        return mMpaa;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getPlot() {
        return mPlot;
    }

    public Rating getRating() {
        return mRating;
    }

//    public List<ReleaseDate> getReleaseDate() {
//        return mReleaseDate;
//    }

    public String getReleased() {
        return mReleased;
    }

    public Long getRuntime() {
        return mRuntime;
    }

    public String getTagline() {
        return mTagline;
    }

    public List<String> getTitles() {
        return mTitles;
    }

    public Long getTmdbId() {
        return mTmdbId;
    }

    public String getType() {
        return mType;
    }

    public Boolean getViaImdb() {
        return mViaImdb;
    }

    public Boolean getViaTmdb() {
        return mViaTmdb;
    }

    public List<String> getWriters() {
        return mWriters;
    }

    public Long getYear() {
        return mYear;
    }

    public static class Builder {

        private HashMap<String, String> mActorRoles;
        private List<String> mActors;
        private List<String> mDirectors;
        private List<String> mGenres;
        private Images mImages;
        private String mImdb;
        private String mMpaa;
        private String mOriginalTitle;
        private String mPlot;
        private Rating mRating;
        private List<ReleaseDate> mReleaseDate;
        private String mReleased;
        private Long mRuntime;
        private String mTagline;
        private List<String> mTitles;
        private Long mTmdbId;
        private String mType;
        private Boolean mViaImdb;
        private Boolean mViaTmdb;
        private List<String> mWriters;
        private Long mYear;

        public Info.Builder withActorRoles(HashMap<String, String> actorRoles) {
            mActorRoles = actorRoles;
            return this;
        }

        public Info.Builder withActors(List<String> actors) {
            mActors = actors;
            return this;
        }

        public Info.Builder withDirectors(List<String> directors) {
            mDirectors = directors;
            return this;
        }

        public Info.Builder withGenres(List<String> genres) {
            mGenres = genres;
            return this;
        }

        public Info.Builder withImages(Images images) {
            mImages = images;
            return this;
        }

        public Info.Builder withImdb(String imdb) {
            mImdb = imdb;
            return this;
        }

        public Info.Builder withMpaa(String mpaa) {
            mMpaa = mpaa;
            return this;
        }

        public Info.Builder withOriginalTitle(String originalTitle) {
            mOriginalTitle = originalTitle;
            return this;
        }

        public Info.Builder withPlot(String plot) {
            mPlot = plot;
            return this;
        }

        public Info.Builder withRating(Rating rating) {
            mRating = rating;
            return this;
        }

        public Info.Builder withReleaseDate(List<ReleaseDate> releaseDate) {
            mReleaseDate = releaseDate;
            return this;
        }

        public Info.Builder withReleased(String released) {
            mReleased = released;
            return this;
        }

        public Info.Builder withRuntime(Long runtime) {
            mRuntime = runtime;
            return this;
        }

        public Info.Builder withTagline(String tagline) {
            mTagline = tagline;
            return this;
        }

        public Info.Builder withTitles(List<String> titles) {
            mTitles = titles;
            return this;
        }

        public Info.Builder withTmdbId(Long tmdbId) {
            mTmdbId = tmdbId;
            return this;
        }

        public Info.Builder withType(String type) {
            mType = type;
            return this;
        }

        public Info.Builder withViaImdb(Boolean viaImdb) {
            mViaImdb = viaImdb;
            return this;
        }

        public Info.Builder withViaTmdb(Boolean viaTmdb) {
            mViaTmdb = viaTmdb;
            return this;
        }

        public Info.Builder withWriters(List<String> writers) {
            mWriters = writers;
            return this;
        }

        public Info.Builder withYear(Long year) {
            mYear = year;
            return this;
        }

        public Info build() {
            Info Info = new Info();
            Info.mActorRoles = mActorRoles;
            Info.mActors = mActors;
            Info.mDirectors = mDirectors;
            Info.mGenres = mGenres;
            Info.mImages = mImages;
            Info.mImdb = mImdb;
            Info.mMpaa = mMpaa;
            Info.mOriginalTitle = mOriginalTitle;
            Info.mPlot = mPlot;
            Info.mRating = mRating;
//            Info.mReleaseDate = mReleaseDate;
            Info.mReleased = mReleased;
            Info.mRuntime = mRuntime;
            Info.mTagline = mTagline;
            Info.mTitles = mTitles;
            Info.mTmdbId = mTmdbId;
            Info.mType = mType;
            Info.mViaImdb = mViaImdb;
            Info.mViaTmdb = mViaTmdb;
            Info.mWriters = mWriters;
            Info.mYear = mYear;
            return Info;
        }

    }

}
