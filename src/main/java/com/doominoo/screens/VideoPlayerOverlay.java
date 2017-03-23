package com.doominoo.screens;

import com.doominoo.ScreensManager;
import com.doominoo.VideoPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by marcmarquez on 02/02/17.
 */

public class VideoPlayerOverlay implements AppScreen {
    private ScreensManager manager;
    private VideoPlayer videoPlayer;
    private Logger logger=Logger.getLogger(getClass().getName());
    private String backScreen;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane overlayPane;

    @FXML
    private Button rewindButton;

    @FXML
    private Button playPauseButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button forwardButton;

//    @FXML
//    private VideoPlayerPositionSlider videoPlayerPositionSlider;
    @FXML
    private Slider videoPlayerPositionSlider;

    @FXML
    private Text timePositionText;


    public static VideoPlayerOverlay getPlayerOverlay(VideoPlayer videoPlayer, ScreensManager manager) throws IOException {
        FXMLLoader loader = new FXMLLoader(VideoPlayerOverlay.class.getClassLoader().getResource("views/videoPlayerOverlay.fxml"));
        VideoPlayerOverlay videoPlayerOverlay = new VideoPlayerOverlay(videoPlayer, manager);
        loader.setController(videoPlayerOverlay);
        loader.load();
        return loader.getController();
    }

    public void setScreensManager(ScreensManager screensManager) {
        manager = screensManager;

    }


    public void setBackScreen(String screen) {
        this.backScreen = screen;
    }

    VideoPlayerOverlay(VideoPlayer videoPlayer, ScreensManager manager) {
        this.videoPlayer = videoPlayer;
        this.manager = manager;
    }

    GridPane getPane() {
        return overlayPane;

    }

    @FXML
    void initialize() throws NoSuchMethodException{
        assert overlayPane != null : "fx:id=\"overlayPane\" was not injected: check your FXML file 'videoPlayerOverlay.fxml'.";
        assert rewindButton != null : "fx:id=\"rewindButton\" was not injected: check your FXML file 'videoPlayerOverlay.fxml'.";
        assert playPauseButton != null : "fx:id=\"playPauseButton\" was not injected: check your FXML file 'videoPlayerOverlay.fxml'.";
        assert stopButton != null : "fx:id=\"stopButton\" was not injected: check your FXML file 'videoPlayerOverlay.fxml'.";
        assert forwardButton != null : "fx:id=\"forwardButton\" was not injected: check your FXML file 'videoPlayerOverlay.fxml'.";
        assert videoPlayerPositionSlider != null : "fx:id=\"videoPlayerPositionSlider\" was not injected: check your FXML file 'videoPlayerOverlay.fxml'.";
        assert timePositionText != null : "fx:id=\"timePositionText\" was not injected: check your FXML file 'videoPlayerOverlay.fxml'.";


        playPauseButton.setOnAction(actionEvent -> {
            switch (videoPlayer.getStatus()) {
                case FORWARDING:
                    videoPlayer.stopForward();
                    break;
                case REWINDING:
                    videoPlayer.stopRewind();
                    break;
                case STOPPED:
                case PAUSED:
                    videoPlayer.play();
                    break;
                case PLAYING:
                    videoPlayer.pause();
                    break;
            }
        });

        stopButton.setOnAction(actionEvent -> {
            videoPlayer.stop();
            switch (videoPlayer.getStatus()) {
                case FORWARDING:
                    videoPlayer.stopForward();
                    break;
                case REWINDING:
                    videoPlayer.stopRewind();
                    break;
            }
            videoPlayer.stop();
            manager.setActiveScreenTo(backScreen);
        });

        forwardButton.setOnAction(actionEvent -> videoPlayer.forward());
        rewindButton.setOnAction(actionEvent -> videoPlayer.rewind());
//        Label label = new Label();
//        label.textProperty().bind(videoPlayerPositionSlider.valueProperty().asString());
//        PopOver popup = new PopOver(label);
//        popup.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);

        videoPlayerPositionSlider.valueProperty().bindBidirectional(videoPlayer.timeProperty());
//        videoPlayerPositionSlider.valueProperty().addListener((observable) -> {
//            if (videoPlayerPositionSlider.isValueChanging()) {
//                videoPlayer.seek(videoPlayerPositionSlider.getValue());
//            }
//        });
        videoPlayerPositionSlider.maxProperty().bind(videoPlayer.lengthProperty());
        videoPlayerPositionSlider.setBlockIncrement(5000);

        videoPlayer.timeProperty().addListener((observableValue, oldnumber, newnumber) -> {
            timePositionText.setText(timeInHoursMinutes(newnumber.doubleValue())+" / "+timeInHoursMinutes(videoPlayer.lengthProperty().doubleValue()));
        });

        videoPlayer.lengthProperty().addListener((observableValue, oldnumber, newnumber) -> {
            timePositionText.setText(timeInHoursMinutes(videoPlayer.timeProperty().doubleValue())+" / "+timeInHoursMinutes(newnumber.doubleValue()));
        });
//        timePositionText.textProperty().bind(vi);

        logger.log(Level.INFO,"mediaPlayer initiated");

    }

    private String timeInHoursMinutes(double milliseconds) {
//        double seconds = seconds;
        double seconds = milliseconds / 1000;
        int intSeconds = (int) (seconds % 60);
        int minutes = (int) (seconds % 3600) / 60;
        int hours = (int) (seconds / 3600);
        return String.format(Locale.ENGLISH, "%02d:%02d:%02d", hours, minutes, intSeconds);

    }

    void show() {
        overlayPane.setOpacity(1);
    }

    void hide() {
        overlayPane.setOpacity(0);
    }
//        videoPlayerPositionSlider.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
//                if (t1) {
//                    Pane thumb = (Pane) videoPlayerPositionSlider.lookup(".thumb");
//                    logger.log(Level.INFO, String.valueOf(thumb.getLayoutX()));
//                    logger.log(Level.INFO, String.valueOf(thumb.getLayoutY()));
////                    popup.setAnchorX(thumb.getLayoutX());
////                    popup.setAnchorY(thumb.getLayoutY());
//                    double x = manager.getWindow().getX();
//                    double y = manager.getWindow().getY();
////                    Bounds parentBounds = thumb.localToParent(thumb.getBoundsInLocal());
//
////                    point = thumb.sceneToLocal(point.getX(),point.getY());
//                    Bounds localBounds = thumb.localToScene(thumb.getBoundsInLocal());
//                    popup.show(thumb.getParent(),x + (localBounds.getMaxX()-localBounds.getMinX()), y + (localBounds.getMaxY()-localBounds.getMinY()));
////                    popup.show(thumb.getParent(),x + parentBounds.getMinX(), y + parentBounds.getMinX());
////                    popup.show(thumb.getParent(), (parentBounds.getMaxX()/2) ,(parentBounds.getMaxY()/2));
//                } else {
//                    popup.hide();
//                }
//            }
//        });
//        manager.getWindow().addEventHandler(WindowEvent.WINDOW_SHOWN, (ev) -> {
//
//                    double offset = 10;
//
//                    videoPlayerPositionSlider.setOnMouseMoved(e -> {
//                        NumberAxis axis = (NumberAxis) videoPlayerPositionSlider.lookup(".thumb");
//                        Point2D locationInAxis = axis.sceneToLocal(e.getSceneX(), e.getSceneY());
//                        double mouseX = locationInAxis.getX();
//                        double value = axis.getValueForDisplay(mouseX).doubleValue();
//                        if (value >= videoPlayerPositionSlider.getMin() && value <= videoPlayerPositionSlider.getMax()) {
//                            label.setText(String.format("Value: %.1f", value));
//                        } else {
//                            label.setText("Value: ---");
//                        }
//                        popup.setAnchorX(e.getScreenX());
//                        popup.setAnchorY(e.getScreenY() + offset);
//                    });
//
//                    videoPlayerPositionSlider.setOnMouseEntered(e -> popup.show(videoPlayerPositionSlider, e.getScreenX(), e.getScreenY() + offset));
//                    videoPlayerPositionSlider.setOnMouseExited(e -> popup.hide());
//                });
//        videoPlayerPositionSlider.valueProperty().bindBidirectional(videoPlayer.timeProperty());
//        videoPlayerPositionSlider.maxProperty().bind(videoPlayer.lengthProperty());

//        manager.getWindow().addEventHandler(WindowEvent.WINDOW_SHOWN, (e) -> {
//            videoPlayerPositionSlider.setUp(videoPlayer.lengthProperty(), videoPlayer.timeProperty(), manager.getWindow());
//                });
//        videoPlayerPositionSlider.setUp(videoPlayer.lengthProperty(), videoPlayer.positionProperty());




}
