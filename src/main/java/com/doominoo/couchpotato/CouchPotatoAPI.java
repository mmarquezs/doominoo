package com.doominoo.couchpotato;

import com.doominoo.couchpotato.model.MoviesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marcmarquez on 07/03/17.
 */
public interface CouchPotatoAPI {
    @GET("movie.list")
    Call<MoviesList> getMovies(@Query("status") String status);

}
