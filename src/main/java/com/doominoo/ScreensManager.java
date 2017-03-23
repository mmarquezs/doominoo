package com.doominoo;

import com.doominoo.screens.AppScreen;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;

/**
 * Created by marcmarquez on 06/02/17.
 */
public class ScreensManager extends StackPane {
    /**
     * Manages the screens of the program, based on :
     * https://blogs.oracle.com/acaicedo/entry/managing_multiple_screens_in_javafx1
     */
    private Stage window;
    // Screen list.
    private static HashMap<String, AppScreen> screens = new HashMap<>();

    public ScreensManager() {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    void setWindow(Stage window) {
        /**
         * Sets the stage to be used as "window".
         */
        this.window = window;
    }

    Stage getWindow() {
        /**
         * Gets the "window" used by the screen manager.
         */
        return window;
    }

    void addScreen(String name, AppScreen screen) {
        /**
         * Set this manager as the screen manager and adds it to the list.
         */
        screen.setScreensManager(this);
        screens.put(name, screen);
    }

    void removeScreen(String name) {
        /**
         * Removes one screen from the manager.
         */
        screens.remove(name);
    }
    public AppScreen getScreen(String name) {
        /**
         * Gets one screen from the manager.
         */
        return screens.get(name);
    }

    public void setActiveScreenTo(String name) {
        /**
         * Sets the active screen on the stage. To do that fades out the old one and fades in the new one.
         */
        if (getChildren().isEmpty()) {
            //setOpacity(0);
            getChildren().add((StackPane) screens.get(name));
            //fadeIn();
        } else {
            fadeOut(actionEvent -> {
                getChildren().remove(0);
                getChildren().add((StackPane) getScreen(name));
                fadeIn();
            });

        }
    }

    private void fadeOut(EventHandler<ActionEvent> e) {
        /**
         * Fade out effect.
         */
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), this);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e);
        fadeOut.play();
    }

    private void fadeIn() {
        /**
         * Fade in effect
         */
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), this);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }
}
