package com.doominoo.screens.controls;

import com.doominoo.Movie;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;


/**
 * Created by marcmarquez on 23/03/17.
 */
public class MoviePostersListView extends ListView<Movie> {
    private Callback<Movie, Void> actionCallback;
    public MoviePostersListView() {
//        Setting cell factory
        setCellFactory(new Callback<ListView<Movie>, ListCell<Movie>>() {
            @Override
            public ListCell<Movie> call(ListView<Movie> movieListView) {
                ListCell lc = new ListCell<Movie>() {
                    @Override
                    protected void updateItem(Movie movie, boolean bln) {
                        super.updateItem(movie, bln);
                        if (movie != null && movie.getPosterImageUrl() != null) {
                            ImageView imageView = new ImageView(movie.getPosterImageUrl());
                            imageView.setPreserveRatio(true);
                            imageView.fitHeightProperty().bind(this.heightProperty());
//                            Button button = new Button();
//                            button.setGraphic(imageView);
//                            button.setOnAction(actionEvent);
                            setGraphic(imageView);
//                            setGraphic(button);
                        } else if (movie != null && movie.getTitle() != null) {
                            setText(movie.getTitle());
                        }
                    }
                };
                lc.prefHeightProperty().bind(movieListView.heightProperty());
                lc.maxHeightProperty().bind(movieListView.heightProperty());
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

    public void setOnAction(Callback<Movie, Void> actionCallback) {
        this.actionCallback = actionCallback;
    }
}
