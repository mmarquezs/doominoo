package com.doominoo.screens;

import com.doominoo.BackgroundService;
import com.doominoo.Movie;
import com.doominoo.ScreensManager;
import com.doominoo.VideoPlayer;
import com.doominoo.screens.controls.CarouselElement;
import com.doominoo.screens.controls.GenericCarousel;
import com.doominoo.screens.controls.ImageViewPane;
import com.doominoo.screens.controls.MediaDetails;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private GenericCarousel<Movie> moviesPostersList;

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

        moviesPostersList.setCellFactory(new Callback<GenericCarousel<Movie>, CarouselElement<Movie>>() {
            @Override
            public CarouselElement<Movie> call(GenericCarousel<Movie> movieListView) {

                CarouselElement lc = new CarouselElement<Movie>() {
                    ImageView poster;
                    ImageViewPane imageViewPane;
                    Label title;
                    VBox vBox;
//                    VBox imageContainer;
                    {
                        VBox vBox = new VBox() {
                                @Override
                                protected void layoutChildren() {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
//                                            double maxWidth = 0;
//                                            for (Node node : getChildren()) {
//                                                double width = node.getBoundsInParent().getWidth();
//                                                if (width>maxWidth) {
//                                                    maxWidth = width;
//                                                }
//                                            }
                                            if (getChildren().size()>1) {
//                                                System.out.println(((Node) getChildren().get(0)).getBoundsInParent().getWidth());
                                                setPrefWidth(getChildren().get(0).getBoundsInParent().getWidth());
                                                ((Label) getChildren().get(1)).setMaxWidth(getPrefWidth());
//                                                ((Label) getChildren().get(1)).setPrefWidth(getPrefWidth());
                                            }
//                                            setPrefWidth(maxWidth);
                                            setMaxWidth(Double.MAX_VALUE);
//                                            System.out.println(maxWidth);
                                        }
                                    });
//                                    if (getChildren().size()>1) {
//                                        System.out.println(((Node) getChildren().get(0)).getBoundsInParent().getWidth());
////                                        setPrefWidth(getChildren().get(0).getBoundsInParent().getWidth());
////                                        ((Label) getChildren().get(1)).setMaxWidth(getPrefWidth());
////                                        ((Label) getChildren().get(1)).setPrefWidth(getPrefWidth());
//                                    }
                                    super.layoutChildren();

                                }
                            };
                        vBox.setMaxWidth(Double.MAX_VALUE);
//                        vBox.setAlignment(Pos.CENTER);
                        title = new Label();
//                        title.setAlignment(Pos.CENTER);
//                        title.setBorder(new Border(new BorderStroke(Color.YELLOW,
//                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        title.setBackground(new Background(new BackgroundFill(Color.PURPLE,CornerRadii.EMPTY, Insets.EMPTY)));
                        poster = new ImageView();
//                        poster.setCache(true);
                        poster.setPreserveRatio(true);
//                        poster.getImage().setBackground(new Background(new BackgroundFill(Color.PURPLE,CornerRadii.EMPTY, Insets.EMPTY)));
//                        imageContainer = new VBox();
//                        vBox.getChildren().addAll(imageContainer,title);
                        imageViewPane = new ImageViewPane();
                        imageViewPane.setImageView(poster);
                        vBox.getChildren().addAll(imageViewPane,title);
//                        imageContainer.getChildren().addAll(poster);
//                        imageContainer.prefHeightProperty().bind(movieListView.heightProperty().subtract(title.prefHeightProperty()));
//                        imageContainer.maxHeightProperty().bind(movieListView.heightProperty().subtract(title.prefHeightProperty()));

//                        vBox.setBorder(new Border(new BorderStroke(Color.YELLOW,
//                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        vBox.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY, Insets.EMPTY)));
//                        vBox.setFillWidth(false);
//                        prefHeightProperty().bind(movieListView.heightProperty());
//                        maxHeightProperty().bind(movieListView.heightProperty());
//                        poster.fitHeightProperty().bind(maxHeightProperty().subtract(title.maxHeightProperty()));
////                        vBox.prefHeightProperty().bind(movieListView.heightProperty().subtract(vBox.getBorder().getInsets().getTop()+vBox.getBorder().getInsets().getBottom()));
////                        vBox.maxHeightProperty().bind(movieListView.heightProperty().subtract(vBox.getBorder().getInsets().getTop()+vBox.getBorder().getInsets().getBottom()));
//                        vBox.prefHeightProperty().bind(heightProperty());
//                        vBox.maxHeightProperty().bind(heightProperty());
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
//                        poster.imageProperty().addListener(new ChangeListener<Image>() {
//                            @Override
//                            public void changed(ObservableValue<? extends Image> observableValue, Image image, Image t1) {
//                                title.setMaxWidth(poster.getBoundsInParent().getWidth());
//                                title.setPrefWidth(poster.getBoundsInParent().getWidth());
//                            }
//                        });
//                        poster.fitHeightProperty().bind(vBox.maxHeightProperty().subtract(title.maxHeightProperty()));
                        setGraphic(vBox);
//                        poster.fitHeightProperty().bind(vBox.heightProperty());
//                        vBox.prefHeightProperty().bind(heightProperty());
//                        vBox.maxHeightProperty().bind(heightProperty());
//                        setScaleX(0.85);
//                        setScaleY(0.85);
//                        selectedProperty().addListener((observableValue, aBoolean, t1) -> {
//                            if (t1) {
//                                zoomInPoster(this, Duration.millis(250));
//                            }else{
//                                zoomOutPoster(this, Duration.millis(250));
//                            }
//                        });
//                        hoverProperty().addListener((observableValue, aBoolean, t1) -> {
//                            if (t1) {
//                                zoomInPoster(this, Duration.millis(250));
//                            }else{
//                                zoomOutPoster(this, Duration.millis(250));
//                            }
//                        });
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
//        moviesPostersList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                movieDetailsListView.getSelectionModel().select(t1.intValue());
////                movieDetailsListView.getFocusModel().focus(t1.intValue());
//                movieDetailsListView.scrollTo(t1.intValue());
//            }
//        });
        moviesPostersList.selectedIndexProperty().addListener(new ChangeListener<Number>() {
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
            int position = moviesPostersList.getSelectedIndex();
            moviesObservableList.clear();
            moviesObservableList.addAll(service.getMovies());
//            moviesPostersList.refresh();
//            movieDetailsListView.refresh();
            restoreSelection(position);
        });
    }

    private void restoreSelection(int position) {
        movieDetailsListView.getSelectionModel().select(position);
        movieDetailsListView.getFocusModel().focus(position);
        movieDetailsListView.scrollTo(position);
        moviesPostersList.select(position);
//        moviesPostersList.getFocusModel().focus(position);
//        moviesPostersList.scrollTo(position);
    }

}
