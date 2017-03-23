
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MoviesList {

    @SerializedName("empty")
    private Boolean mEmpty;
    @SerializedName("movies")
    private List<CouchpotatoMovie> mCouchpotatoMovies;
    @SerializedName("success")
    private Boolean mSuccess;
    @SerializedName("total")
    private Long mTotal;

    public Boolean getEmpty() {
        return mEmpty;
    }

    public List<CouchpotatoMovie> getMovies() {
        return mCouchpotatoMovies;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public Long getTotal() {
        return mTotal;
    }

    public static class Builder {

        private Boolean mEmpty;
        private List<CouchpotatoMovie> mCouchpotatoMovies;
        private Boolean mSuccess;
        private Long mTotal;

        public MoviesList.Builder withEmpty(Boolean empty) {
            mEmpty = empty;
            return this;
        }

        public MoviesList.Builder withMovies(List<CouchpotatoMovie> couchpotatoMovies) {
            mCouchpotatoMovies = couchpotatoMovies;
            return this;
        }

        public MoviesList.Builder withSuccess(Boolean success) {
            mSuccess = success;
            return this;
        }

        public MoviesList.Builder withTotal(Long total) {
            mTotal = total;
            return this;
        }

        public MoviesList build() {
            MoviesList MoviesList = new MoviesList();
            MoviesList.mEmpty = mEmpty;
            MoviesList.mCouchpotatoMovies = mCouchpotatoMovies;
            MoviesList.mSuccess = mSuccess;
            MoviesList.mTotal = mTotal;
            return MoviesList;
        }

    }

}
