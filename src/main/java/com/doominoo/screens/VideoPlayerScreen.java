package com.doominoo.screens;

import com.doominoo.ScreensManager;
import com.doominoo.VideoPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by marcmarquez on 06/02/17.
 */
public class VideoPlayerScreen extends StackPane implements AppScreen {
    private ScreensManager manager;
    private VideoPlayer videoPlayer;
    private VideoPlayerOverlay overlay;
    private String backScreen;
    private static final Logger Log = LoggerFactory.getLogger( VideoPlayer.class);

    public VideoPlayerScreen(ScreensManager manager) throws IOException {
        this.manager = manager;
        initVideoPlayer();
        initVideoPlayerOverlay();
        setMinSize(0,0);
        setAlignment(Pos.TOP_LEFT);
        getChildren().add(videoPlayer.getPane());
        getChildren().add(overlay.getPane());
        settingKeyboardShortcuts();
    }

    private void settingKeyboardShortcuts() {
        setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case ADD:
                    videoPlayer.increaseVolume();
                    break;
                case SUBTRACT:
                    videoPlayer.decreaseVolume();
                    break;
            }
        });
    }

    public void setBackScreen(String screen) {
        this.backScreen = screen;
        overlay.setBackScreen(screen);
    }

    public void setScreensManager(ScreensManager screensManager) {
        manager = screensManager;
        videoPlayer.setScreensManager(manager);
        overlay.setScreensManager(manager);
    }

    private void initVideoPlayer() throws IOException {
        videoPlayer = VideoPlayer.getPlayer(this);
    }

    private void initVideoPlayerOverlay() throws IOException {
        overlay = VideoPlayerOverlay.getPlayerOverlay(videoPlayer, manager);
        Timeline autoHideTimeline = new Timeline(new KeyFrame(
                Duration.seconds(10),
                actionEvent -> overlay.hide()
            ));
        autoHideTimeline.play();
        setOnKeyReleased(keyEvent -> {
            autoHideTimeline.play();
        });
        setOnKeyPressed(keyEvent -> {
            autoHideTimeline.stop();
            overlay.show();
        });

        setOnMouseMoved(keyEvent -> {
            autoHideTimeline.stop();
            autoHideTimeline.play();
            overlay.show();
        });

        overlay.setBackScreen(backScreen);

//        setOnMouseReleased(MouseEvent -> {
//            autoHideTimeline.play();
//        });
//        setOnMousePressed(MouseEvent -> {
//            autoHideTimeline.stop();
//            overlay.show();
//        });
    }

    public VideoPlayer getVideoPlayer() {
        return videoPlayer;
    }
        
    public VideoPlayerOverlay getOverlay() {
        return overlay;
    }
}
