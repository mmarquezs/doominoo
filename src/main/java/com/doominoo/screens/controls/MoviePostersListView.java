package com.doominoo.screens.controls;

import com.doominoo.Movie;
import javafx.animation.ScaleTransition;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import javafx.util.Duration;


/**
 * Created by marcmarquez on 23/03/17.
 */
public class MoviePostersListView extends ListView<Movie> {
    private Callback<Movie, Void> actionCallback;
    public MoviePostersListView() {
//        Setting cell factory
        setOrientation(Orientation.HORIZONTAL);
        setCellFactory(new Callback<ListView<Movie>, ListCell<Movie>>() {
            @Override
            public ListCell<Movie> call(ListView<Movie> movieListView) {

                ListCell lc = new ListCell<Movie>() {
                    @Override
                    protected void updateItem(Movie movie, boolean bln) {
                        super.updateItem(movie, bln);
                        if (movie != null && movie.getPosterImage() != null) {
                            ImageView imageView = movie.getPosterImage();
                            imageView.setCache(true);
                            imageView.setPreserveRatio(true);
                            imageView.fitHeightProperty().bind(this.heightProperty());
                            setGraphic(imageView);
                        } else if (movie != null && movie.getTitle() != null) {
                            setText(movie.getTitle());
                        }
                    }
                };
                lc.setScaleX(0.85);
                lc.setScaleY(0.85);
                lc.prefHeightProperty().bind(movieListView.heightProperty());
                lc.maxHeightProperty().bind(movieListView.heightProperty());
                lc.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (t1) {
                        zoomInPoster(lc, Duration.millis(250));
                    }else{
                        zoomOutPoster(lc, Duration.millis(250));
                    }
                });
                lc.hoverProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (t1) {
                        zoomInPoster(lc, Duration.millis(250));
                    }else{
                        zoomOutPoster(lc, Duration.millis(250));
                    }
                });
                return lc;
            }
        });
        setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                actionCallback.call(getSelectionModel().getSelectedItem());
            }
        });
        setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    actionCallback.call(getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void zoomInPoster(Node node, Duration duration) {
        ScaleTransition st = new ScaleTransition(duration,node);
        st.setFromX(0.85);
        st.setToX(1);
        st.setFromY(0.85);
        st.setToY(1);
        st.play();
    }

    private void zoomOutPoster(Node node, Duration duration) {
        ScaleTransition st = new ScaleTransition(duration,node);
        st.setFromX(1);
        st.setToX(0.85);
        st.setFromY(1);
        st.setToY(0.85);
        st.play();
    }

    public void setOnAction(Callback<Movie, Void> actionCallback) {
        this.actionCallback = actionCallback;
    }

//    @Override
//    public String getUserAgentStylesheet() {
//        return MoviePostersListView.class.getClassLoader().getResource("css/controls/moviePostersListView.css").toExternalForm();
//    }
}
