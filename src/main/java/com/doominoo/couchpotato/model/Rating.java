
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Rating {

    @SerializedName("imdb")
    private List<Double> mImdb;

    public List<Double> getImdb() {
        return mImdb;
    }

    public static class Builder {

        private List<Double> mImdb;

        public Rating.Builder withImdb(List<Double> imdb) {
            mImdb = imdb;
            return this;
        }

        public Rating build() {
            Rating Rating = new Rating();
            Rating.mImdb = mImdb;
            return Rating;
        }

    }

}
