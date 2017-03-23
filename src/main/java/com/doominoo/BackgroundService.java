package com.doominoo;

import com.doominoo.couchpotato.CouchPotatoAPI;
import com.doominoo.couchpotato.model.CouchpotatoMovie;
import com.doominoo.couchpotato.model.Files;
import com.doominoo.couchpotato.model.Release;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.services.FindService;
import com.uwetrottmann.tmdb2.services.MoviesService;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by marcmarquez on 05/03/17.
 */
public class BackgroundService extends ScheduledService<Boolean>{
    private static final Logger Log = LoggerFactory.getLogger( BackgroundService.class);
    private static final Properties properties = new Properties();
    private static String COUCHPOTATO_API_URL;
    private static LoadingCache<Long, Optional<com.uwetrottmann.tmdb2.entities.Movie>> movieInfoTMDB2 = CacheBuilder
            .newBuilder()
            .expireAfterAccess(1, TimeUnit.DAYS)
            .recordStats()
            .build(new CacheLoader<Long, Optional<com.uwetrottmann.tmdb2.entities.Movie>>() {
                @Override
                public Optional<com.uwetrottmann.tmdb2.entities.Movie> load(Long l) throws Exception {
                    return getMovieInfoTMDB(l,"es-ES");
                }
            });
    private static CouchPotatoAPI couchpotato;
    private static Tmdb tmdb;
    private static FindService findService ;
    private static MoviesService moviesService;
    private boolean dataUpdated = true;
    private List<Movie> movies = new ArrayList<>();
    static {
        try {
            properties.load(BackgroundService.class.getClassLoader().getResourceAsStream("config.properties"));
//            Setting up couchpotato
            String couchDomain = properties.getProperty("couchpotato-domain");
            if (couchDomain == null) {
                Log.error("Missing couchpotato domain in config.");
                System.exit(1);
            }
            String couchApiKey = properties.getProperty("couchpotato-api-key");
            if (couchDomain == null) {
                Log.error("Missing couchpotato api key in config.");
                System.exit(1);
            }
            COUCHPOTATO_API_URL =  (new URL(new URL(new URL(couchDomain), "api/"),couchApiKey)).toString()+"/";
            Log.info(COUCHPOTATO_API_URL);
            couchpotato = new Retrofit.Builder()
                    .baseUrl(COUCHPOTATO_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(CouchPotatoAPI.class);
            String tmdbApi = properties.getProperty("tmdb-api-key");
            if (tmdbApi == null) {
                Log.error("Missing tmdb api key in config.");
                System.exit(1);
            }
            tmdb = new Tmdb(tmdbApi);
            findService = tmdb.findService();
            moviesService = tmdb.moviesService();
        } catch (IOException ex) {
            System.exit(1);
        }
    }

    protected Task<Boolean> createTask() {
        return new Task<Boolean>() {
            protected Boolean call(){
                List<Movie> newMovies = new ArrayList<>();
                //Getting movies from couchpotato
                try {
                    newMovies.addAll(getCouchpotatoMovies());
                } catch (IOException ex) {
                    Log.error("Failed to get couchpotato movies.");
                    Log.error(ex.getMessage());
                    return false;
                }
                //Getting new movies released in DVD/Bluray
//                newMovies.addAll(getNewMovies());
                //Getting movies from Jackett specific Trackers
                //This one will be more complex because i need to obtain imdbid/tmdbid from name.
//                newMovies.addAll(getJackettMovies());
                synchronized (movies) {
                    if (newMovies.size()!=movies.size()) {
                        Collections.sort(newMovies, (movie, movie2) -> movie.getImdbId().compareToIgnoreCase(movie2.getImdbId()));
                        Collections.sort(movies, (movie, movie2) -> movie.getImdbId().compareToIgnoreCase(movie2.getImdbId()));
                        if (!newMovies.equals(movies)) {
                            movies = newMovies;
                            dataUpdated = true;
                            return true;
                        }
                    }
                    Log.info("Nothing new");
                }
                return true;
            }
        };
    }
    private ArrayList<Movie> getCouchpotatoMovies() throws IOException{
        Log.info("Getting Couchpotato Movies...");
        ArrayList<Movie> moviesTmp = new ArrayList<>();
        List<CouchpotatoMovie> movies = couchpotato.getMovies("").execute().body().getMovies();
        for (CouchpotatoMovie couchMovie : movies ) {
            Movie movie = new Movie();
            movie.setImdbId(couchMovie.getIdentifiers().getImdb());
            movie.setTmdbId(couchMovie.getInfo().getTmdbId());
            movie.setStatus(couchMovie.getStatus());
            try {
                Optional<com.uwetrottmann.tmdb2.entities.Movie> movieInfo = movieInfoTMDB2.get(movie.getTmdbId());
                if (movieInfo.isPresent()) {
                    movie.setReleaseYear(String.valueOf(
                            movieInfo.get().release_date
                                    .toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                                    .getYear()));
                    if (movieInfo.get().runtime != null) {
                        int hours = movieInfo.get().runtime / 60;
                        int minutes = ( movieInfo.get().runtime % 60 );
                            movie.setMovieDuration(
                                    String.format("%dh %dm", hours, minutes)
                            );
                    }
                    movie.setTitle(movieInfo.get().title);
                    movie.setPlot(movieInfo.get().overview);
                    movie.setBackdropImageUrl("http://image.tmdb.org/t/p/w780" + movieInfo.get().backdrop_path);
                    movie.setPosterImageUrl("http://image.tmdb.org/t/p/w342" + movieInfo.get().poster_path);
                } else {
                    movie.setTitle(couchMovie.getTitle());
                    movie.setPlot(couchMovie.getInfo().getPlot());
                }
            } catch (ExecutionException ex) {
                Log.error(ex.getMessage());
                movie.setTitle(couchMovie.getTitle());
                movie.setPlot(couchMovie.getInfo().getPlot());
            }
            List<Release> releases = couchMovie.getReleases();
            if (releases.size()!=0) {
                for (Release release : releases) {
                    Files files = release.getFiles();
                    if (files != null && files.getMovie().size() != 0) {
                        movie.setFilePath(files.getMovie().get(0));
                        break;
                    }
                }
            }
            moviesTmp.add(movie);
        }
        Log.info("Finished getting couchpotato movies");
        CacheStats cacheStats = movieInfoTMDB2.stats();
        Log.info(cacheStats.toString());
        return moviesTmp;
    }

    public List<Movie> getMovies() {
        return movies;
    }

//    public static Optional<com.uwetrottmann.tmdb2.entities.Movie> getMovieInfoTMDB(String imdbId, String language) throws IOException {
    public static Optional<com.uwetrottmann.tmdb2.entities.Movie> getMovieInfoTMDB(Long tmdbId, String language) throws IOException {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            Call<com.uwetrottmann.tmdb2.entities.Movie> callMovieTmdb = moviesService.summary(
                    tmdbId.intValue()
                    ,language,null);
            return Optional.ofNullable(callMovieTmdb.execute().body());
    }

}
