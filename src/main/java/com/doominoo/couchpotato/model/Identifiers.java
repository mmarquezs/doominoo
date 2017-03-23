
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Identifiers {

    @SerializedName("imdb")
    private String mImdb;

    public String getImdb() {
        return mImdb;
    }

    public static class Builder {

        private String mImdb;

        public Identifiers.Builder withImdb(String imdb) {
            mImdb = imdb;
            return this;
        }

        public Identifiers build() {
            Identifiers Identifiers = new Identifiers();
            Identifiers.mImdb = mImdb;
            return Identifiers;
        }

    }

}
