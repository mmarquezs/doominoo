package com.doominoo.screens;

import com.doominoo.BackgroundService;
import com.doominoo.Movie;
import com.doominoo.ScreensManager;
import com.doominoo.VideoPlayer;
import com.doominoo.screens.controls.MediaDetails;
import com.doominoo.screens.controls.MoviePostersListView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by marcmarquez on 04/03/17.
 */
public class MainScreen extends StackPane implements AppScreen {
    private static final Logger Log = LoggerFactory.getLogger( MainScreen.class);
    private static final String TITLE = "PELICULES";
    private ScreensManager manager;
    private BackgroundService service;
    private ObservableList<Movie> moviesObservableList = FXCollections.observableList(new ArrayList<>());
    private VideoPlayerScreen videoPlayerScreen;
    public void setScreensManager(ScreensManager screensManager) {
        manager = screensManager;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox topButtonsHBox;

    @FXML
    private ListView<Movie> movieDetailsListView;
//    private ListView<String> movieDetailsListView;
    @FXML
    private MoviePostersListView moviesPostersList;

    @FXML
    private HBox bottomButtonsHBox;
    
    @FXML
    private GridPane gridPane;


    @FXML
    private Label titleLabel;

    public MainScreen(BackgroundService service, ScreensManager manager) throws IOException {
//    MainScreen(ScreensManager manager) throws IOException {
        this.service = service;
        this.manager = manager;
        videoPlayerScreen = ((VideoPlayerScreen) manager
                .getScreen("VideoPlayerScreen"));
        service.setOnSucceeded(workerStateEvent -> {
            updateUi();
        });
        FXMLLoader loader = new FXMLLoader(VideoPlayer.class.getClassLoader().getResource("views/mainScreen.fxml"));
//        VideoPlayer videoPlayer = new VideoPlayer(rootPane);
        loader.setController(this);
        getChildren().add(loader.load());
//        return loader.getController();
//        initVideoPlayer();
//        initVideoPlayerOverlay();
//        setMinSize(0,0);
//        setAlignment(Pos.TOP_LEFT);
//        getChildren().add(videoPlayer.getPane());
//        getChildren().add(overlay.getPane());
    }

    @FXML
    void initialize() {
        assert topButtonsHBox != null : "fx:id=\"topButtonsHBox\" was not injected: check your FXML file 'mainScreen.fxml'.";
        assert movieDetailsListView != null : "fx:id=\"movieDetailsListView\" was not injected: check your FXML file 'mainScreen.fxml'.";
        assert moviesPostersList != null : "fx:id=\"moviesPostersList\" was not injected: check your FXML file 'mainScreen.fxml'.";
        assert bottomButtonsHBox != null : "fx:id=\"bottomButtonsHBox\" was not injected: check your FXML file 'mainScreen.fxml'.";
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'mainScreen.fxml'.";
	    assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'mainScreen.fxml'.";

//    	String css = MediaDetails.class.getClassLoader().getResource("mainScreen.css").toExternalForm();
//        this.getStylesheets().add(css);

        titleLabel.setText(TITLE);
        movieDetailsListView.setItems(moviesObservableList);
        movieDetailsListView.setCellFactory(new Callback<ListView<Movie>, ListCell<Movie>>() {
            @Override
            public ListCell<Movie> call(ListView<Movie> movieListView) {
                ListCell lc = new ListCell<Movie>() {
                    @Override
                    protected void updateItem(Movie movie, boolean bln) {
                        super.updateItem(movie, bln);
                        if (movie != null) {
                            MediaDetails mediaDetails = new MediaDetails();
                            mediaDetails.setData(movie);
                            setGraphic(mediaDetails.getVbox());
                        }
                    }
                };
                lc.prefWidthProperty().bind(movieListView.widthProperty());
        		lc.prefHeightProperty().bind(movieListView.heightProperty());
	        	lc.maxWidthProperty().bind(movieListView.widthProperty());
	        	lc.maxHeightProperty().bind(movieListView.heightProperty());
                return lc;
            }
        });

        //Disable scrolling
        movieDetailsListView.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                scrollEvent.consume();
            }
        });

        moviesPostersList.setItems(moviesObservableList);
        moviesPostersList.setOnAction(movie -> {
            String path = movie.getFilePath();
            if (path != null) {
                manager.setActiveScreenTo("VideoPlayerScreen");
                videoPlayerScreen.setBackScreen("MainScreen");
                VideoPlayer videoPlayer = videoPlayerScreen
                        .getVideoPlayer();
                videoPlayer.play(path);
            }
            return null;
        });
        moviesPostersList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                movieDetailsListView.getSelectionModel().select(t1.intValue());
//                movieDetailsListView.getFocusModel().focus(t1.intValue());
                movieDetailsListView.scrollTo(t1.intValue());
            }
        });

    }
    void updateUi() {
        Platform.runLater(() -> {
            int position = moviesPostersList.getFocusModel().getFocusedIndex();
            moviesObservableList.clear();
            moviesObservableList.addAll(service.getMovies());
            moviesPostersList.refresh();
            movieDetailsListView.refresh();
            restoreSelection(position);
        });
    }

    private void restoreSelection(int position) {
        movieDetailsListView.getSelectionModel().select(position);
        movieDetailsListView.getFocusModel().focus(position);
        movieDetailsListView.scrollTo(position);
        moviesPostersList.getSelectionModel().select(position);
        moviesPostersList.getFocusModel().focus(position);
        moviesPostersList.scrollTo(position);
    }

}
