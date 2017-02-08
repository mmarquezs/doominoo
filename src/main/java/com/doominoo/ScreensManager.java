package com.doominoo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.HashMap;

/**
 * Created by marcmarquez on 06/02/17.
 */
public class ScreensManager extends StackPane {
    // Screen list.
    private static HashMap<String, Node> screens = new HashMap<>();

    void addScreen(String name, Node screen) {
        // I don't like this too much.
        ((AppScreen) screen).setScreensManager(this);
        screens.put(name, screen);
    }

    void removeScreen(String name) {
        screens.remove(name);
    }
    Node getScreen(String name) {
        return screens.get(name);
    }

    void setActiveScreenTo( String name) {
        if (getChildren().isEmpty()) {
            //setOpacity(0);
            getChildren().add(screens.get(name));
            //fadeIn();
        } else {
            fadeOut(actionEvent -> {
                getChildren().remove(0);
                getChildren().add(getScreen(name));
                fadeIn();
            });

        }
    }

    private void fadeOut(EventHandler<ActionEvent> e) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(opacityProperty(),1)),
                new KeyFrame(new Duration(1e3), e, new KeyValue(opacityProperty(), 0))
        );
        timeline.play();

    }

    private void fadeIn() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(opacityProperty(),0)),
                new KeyFrame(new Duration(1e3), new KeyValue(opacityProperty(), 1))
        );
        timeline.play();
    }
}
