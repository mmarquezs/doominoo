package com.doominoo.screens;

import javafx.beans.property.LongProperty;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by marcmarquez on 06/02/17.
 */
public class VideoPlayerPositionSlider  extends Slider {

    private Logger logger=Logger.getLogger(getClass().getName());
    public void setUp(LongProperty lengthProperty, LongProperty timeProperty, Stage window){
        setMin(0);
        setValue(0);

        //videoPlayerPositionSlider.valueProperty().bindBidirectional(videoPlayer.timeProperty());
//        videoPlayerPositionSlider.maxProperty().bind(videoPlayer.lengthProperty());
        //setMax(lengthProperty.doubleValue());
        setMax(10000);
        logger.log(Level.INFO, "Length"+String.valueOf(lengthProperty.doubleValue()));
        applyCss();
        layout();
        logger.log(Level.INFO, String.valueOf(this.getChildren()));
        window.addEventHandler(WindowEvent.WINDOW_SHOWN, (e) -> {
            logger.log(Level.INFO, "Adding text to thumb.");
            Pane thumb = (Pane) lookup(".thumb");
            if (thumb != null) {
                logger.log(Level.INFO, "Thumb found");
//                Label text = new Label();
//                PopOver popOver = new PopOver(text);
//                popOver.setDetachable(false);
////                popOver.setDetached(true);
//                popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
//                popOver.setCornerRadius(4);
//                popOver.show(this, this.getLayoutX(), this.getLayoutY());
//
////                text.setLayoutX(thumb.getLayoutX() + thumb.getWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
////                text.setLayoutY(thumb.getLayoutX());
////                text.textProperty().bind(timeInHoursMinutes(valueProperty()));
////                text.textProperty().bind((timeInHoursMinutes(valueProperty());
//                valueProperty().addListener((observableValue, oldNumber, newNumber) -> {
////                    popOver.setX(thumb.getLayoutX() + thumb.getWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
////                    popOver.setY(thumb.getLayoutY());
////                    popOver.setY(thumb.getLayoutY());
////                    popOver.setX(thumb.getLayoutX());
//                    text.textProperty().setValue(timeInHoursMinutes(newNumber.doubleValue()));
//                });
////                text.textProperty().bind((valueProperty()).asString("%f"));
////                thumb.getChildren().add(text);
////            text.setLayoutY(thumb.getLayoutY());
            }
            logger.log(Level.INFO, "Not found thumb.");
        });


        valueProperty().bindBidirectional(timeProperty);
        maxProperty().bind(lengthProperty);
    }

//    public VideoPlayerPositionSlider(double length, DoubleProperty positionProperty) {
//        super(0,length,0);
//    }


}
