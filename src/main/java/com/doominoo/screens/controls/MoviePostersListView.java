package com.doominoo.screens.controls;

import com.doominoo.Movie;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;


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
                    ImageView poster;
                    Label title;
                    VBox vBox;
                    VBox imageContainer;
                    {
                        vBox = new VBox();
                        vBox.setAlignment(Pos.CENTER);
                        title = new Label();
                        title.setAlignment(Pos.CENTER);
//                        title.setBorder(new Border(new BorderStroke(Color.YELLOW,
//                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        title.setBackground(new Background(new BackgroundFill(Color.PURPLE,CornerRadii.EMPTY, Insets.EMPTY)));
                        poster = new ImageView();
                        poster.setCache(true);
                        poster.setPreserveRatio(true);
//                        poster.getImage().setBackground(new Background(new BackgroundFill(Color.PURPLE,CornerRadii.EMPTY, Insets.EMPTY)));
//                        imageContainer = new VBox();
//                        vBox.getChildren().addAll(imageContainer,title);
                        vBox.getChildren().addAll(poster,title);
//                        imageContainer.getChildren().addAll(poster);
//                        imageContainer.prefHeightProperty().bind(movieListView.heightProperty().subtract(title.prefHeightProperty()));
//                        imageContainer.maxHeightProperty().bind(movieListView.heightProperty().subtract(title.prefHeightProperty()));
                        poster.fitHeightProperty().bind(prefHeightProperty().subtract(title.prefHeightProperty()));

//                        vBox.setBorder(new Border(new BorderStroke(Color.YELLOW,
//                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        vBox.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY, Insets.EMPTY)));
                        vBox.setFillWidth(false);
                        prefHeightProperty().bind(movieListView.heightProperty());
                        maxHeightProperty().bind(movieListView.heightProperty());
//                        vBox.prefHeightProperty().bind(movieListView.heightProperty().subtract(vBox.getBorder().getInsets().getTop()+vBox.getBorder().getInsets().getBottom()));
//                        vBox.maxHeightProperty().bind(movieListView.heightProperty().subtract(vBox.getBorder().getInsets().getTop()+vBox.getBorder().getInsets().getBottom()));
                        vBox.prefHeightProperty().bind(heightProperty());
                        vBox.maxHeightProperty().bind(heightProperty());
//                        poster.fitHeightProperty().bind(vBox.maxHeightProperty().subtract(title.getBoundsInLocal().getMaxX()));
//                        poster.fitHeightProperty().bind(vBox.maxHeightProperty().subtract(vBox.getBorder().getInsets().getTop()+vBox.getBorder().getInsets().getBottom()));
//                        title.maxHeightProperty().addListener(new ChangeListener<Number>() {
//                            @Override
//                            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                                poster.setFitHeight(vBox.getMaxHeight()-t1.doubleValue());
//                            }
//                        });
//                        vBox.maxHeightProperty().addListener(new ChangeListener<Number>() {
//                            @Override
//                            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                                poster.setFitHeight(vBox.getMaxHeight()-t1.doubleValue());
//                            }
//                        });
                        poster.imageProperty().addListener(new ChangeListener<Image>() {
                            @Override
                            public void changed(ObservableValue<? extends Image> observableValue, Image image, Image t1) {
                                title.setMaxWidth(poster.getBoundsInParent().getWidth());
                                title.setPrefWidth(poster.getBoundsInParent().getWidth());
                            }
                        });
//                        poster.fitHeightProperty().bind(vBox.maxHeightProperty().subtract(title.maxHeightProperty()));
                        setGraphic(vBox);
//                        poster.fitHeightProperty().bind(vBox.heightProperty());
//                        vBox.prefHeightProperty().bind(heightProperty());
//                        vBox.maxHeightProperty().bind(heightProperty());
                        setScaleX(0.85);
                        setScaleY(0.85);
                        selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                            if (t1) {
                                zoomInPoster(this, Duration.millis(250));
                            }else{
                                zoomOutPoster(this, Duration.millis(250));
                            }
                        });
                        hoverProperty().addListener((observableValue, aBoolean, t1) -> {
                            if (t1) {
                                zoomInPoster(this, Duration.millis(250));
                            }else{
                                zoomOutPoster(this, Duration.millis(250));
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Movie movie, boolean bln) {
                        super.updateItem(movie, bln);
                        if (movie != null ) {
                            if (movie.getPosterImage() != null) {
                                poster.setImage(movie.getPosterImage().getImage());
                                title.setText(movie.getTitle());
                            }
                        }
                    }
                };
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
