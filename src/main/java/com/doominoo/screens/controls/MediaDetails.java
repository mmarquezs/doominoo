package com.doominoo.screens.controls;

import com.doominoo.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by marcmarquez on 11/03/17.
 */
public class MediaDetails {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vbox;

    @FXML
    private VBox vBoxImageView1;

    @FXML
    private ImageViewPane imageViewPane;

    private ImageView imageView;

    @FXML
    private Label title;

    @FXML
    private Label movieYear;

    @FXML
    private Label movieContentRating;

    @FXML
    private Label movieDuration;

    @FXML
    private Label plot;

    @FXML
    private ImageView statusImage;

    @FXML
    private Label statusText;

    @FXML
    void initialize() {
        assert vbox != null : "fx:id=\"vbox\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert vBoxImageView1 != null : "fx:id=\"vBoxImageView1\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert movieYear != null : "fx:id=\"movieYear\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert movieContentRating != null : "fx:id=\"movieContentRating\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert movieDuration != null : "fx:id=\"movieDuration\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert plot != null : "fx:id=\"plot\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert statusImage != null : "fx:id=\"statusImage\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
        assert statusText != null : "fx:id=\"statusText\" was not injected: check your FXML file 'mainScreenMediaDetails.fxml'.";
//        String css = MediaDetails.class.getClassLoader().getResource("mainScreenMediaDetails.css").toExternalForm();
//        vbox.getStylesheets().add(css);
    	title.textProperty().addListener((observable, oldValue, newValue) -> {
    		//title.setText(newValue.toUpperCase());
    	});
    	imageView = new ImageView();
//    	imageViewPane = new ImageViewPane();
        imageViewPane.setImageView(imageView);
//    	imageView.fitHeightProperty().bind(vbox.heightProperty());

    }

    public MediaDetails() {

        FXMLLoader loader = new FXMLLoader(MediaDetails.class.getClassLoader().getResource("views/mainScreenMediaDetails.fxml"));
        loader.setController(this);
        try {
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(Movie movie) {
        setTitle(movie.getTitle());
        setYear(movie.getReleaseYear());
        setDuration(movie.getMovieDuration());
        setPlot(movie.getPlot());
        setImage(movie.getBackdropImageUrl());
        setStatus(movie.getStatus());
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setYear(String year) {
        movieYear.setText(year);
    }

    public void setDuration(String duration) {
        movieDuration.setText(duration);
    }

    public String  getPlot() {
        return plot.getText();
    }

    public void setPlot(String plot) {
        this.plot.setText(plot);
    }

    public void setImage(String imagePath) {
        Image image = new Image(imagePath,true);
        imageViewPane.getImageView().setImage(image);
        imageViewPane.getImageView().setCache(true);
    }

    public void setStatus(String status) {
        switch (status) {
            case "done":
                statusText.setText("Disponible");
                statusImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream("images/check.svg.png")));
                break;
            case "active":
                statusText.setText("Buscando/Descargando");
                statusImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream("images/not-available.svg.png")));
                break;
            case "available":
                statusText.setText("Disponible. No descargada");
                statusImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream("images/available-not-downloaded.svg.png")));
                break;
        }
    }

    public VBox getVbox() {
        return vbox;
    }
}
