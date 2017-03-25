package com.doominoo;

import javafx.scene.image.ImageView;

/**
 * Created by marcmarquez on 09/03/17.
 */
public class Movie {
    private String imdbId;
    private String contentRating;
    private String releaseYear;
    private String movieDuration = "";
    private String title = "<MISSING TITLE>";
    private String plot;
    private String filePath;
    private String backdropImageUrl;
    private ImageView posterImage;
    private String status;
    private Long tmdbId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBackdropImageUrl() {
        return backdropImageUrl;
    }

    public void setBackdropImageUrl(String backdropImageUrl) {
        this.backdropImageUrl = backdropImageUrl;
    }

    public ImageView getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(ImageView posterImage){
        this.posterImage = posterImage;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!imdbId.equals(movie.imdbId)) return false;
        if (contentRating != null ? !contentRating.equals(movie.contentRating) : movie.contentRating != null)
            return false;
        if (releaseYear != null ? !releaseYear.equals(movie.releaseYear) : movie.releaseYear != null) return false;
        if (movieDuration != null ? !movieDuration.equals(movie.movieDuration) : movie.movieDuration != null)
            return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (plot != null ? !plot.equals(movie.plot) : movie.plot != null) return false;
        if (filePath != null ? !filePath.equals(movie.filePath) : movie.filePath != null) return false;
        if (backdropImageUrl != null ? !backdropImageUrl.equals(movie.backdropImageUrl) : movie.backdropImageUrl != null)
            return false;
        return posterImage != null ? posterImage.equals(movie.posterImage) : movie.posterImage == null;
    }

    @Override
    public int hashCode() {
        int result = imdbId.hashCode();
        result = 31 * result + (contentRating != null ? contentRating.hashCode() : 0);
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (movieDuration != null ? movieDuration.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (backdropImageUrl != null ? backdropImageUrl.hashCode() : 0);
        result = 31 * result + (posterImage != null ? posterImage.hashCode() : 0);
        return result;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Long getTmdbId() {
        return tmdbId;
    }
}
