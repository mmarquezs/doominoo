package com.doominoo.screens.controls;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by marcmarquez on 28/03/17.
 */
public class GenericCarousel<T> extends Region {
    private static final Interpolator INTERPOLATOR = Interpolator.EASE_BOTH;
    private static final double SCALE_SMALL = 0.7;
    //    private Group main = new Group();
    private Group centered = new Group();
    private Group left = new Group();
    private Group center = new Group();
    private Group right = new Group();
    private IntegerProperty centerIndex = new SimpleIntegerProperty();
    private Timeline timeline;
    private static final int SPACING = 0;
    private static final int LEFT_PADDING = 40;
    private static final int RIGHT_PADDING = 0;
    private static final Duration DURATION = Duration.millis(500);
    private Rectangle clip = new Rectangle();
    private Callback<GenericCarousel<T>, CarouselElement<T>> cellFactory;
    private List<CarouselElement<T>> carouselElementList = new ArrayList<>();
    private Callback<T, Void> actionCallback;
    private ObjectProperty<ObservableList<T>> items = new SimpleObjectProperty<>();

    public ObservableList<T> getItems() {
        return items.get();
    }

    public ObjectProperty<ObservableList<T>> itemsProperty() {
        return items;
    }

    public void setItems(ObservableList<T> items) {
        this.itemsProperty().set(items);
        System.out.print(items.size());
        GenericCarousel<T> gC =  this;
        carouselElementList.clear();
        for (T observable : items) {
            CarouselElement<T> element = cellFactory.call(gC);
            element.updateItem(observable,false);
            carouselElementList.add(element);
        }
        items.addListener(new ListChangeListener<T>() {
            @Override
            public void onChanged(Change<? extends T> change) {
                carouselElementList.clear();
                for (T observable : itemsProperty().get()) {
                    CarouselElement<T> element = cellFactory.call(gC);
                    element.updateItem(observable,false);
                    carouselElementList.add(element);
                }
                refreshUi();
            }
        });
        refreshUi();
    }

    public IntegerProperty selectedIndexProperty() {
        return centerIndex;
    }

    public int getSelectedIndex() {
        return selectedIndexProperty().get();
    }

    public ObservableList<T> getObservableList() {
        return items.get();
    }


    public GenericCarousel() {
        this(FXCollections.observableArrayList());
    }

    public void setOnAction(Callback<T, Void> actionCallback) { this.actionCallback = actionCallback;}


    public GenericCarousel(ObservableList<T> initialObservableList) {
        setClip(clip);
        setItems(initialObservableList);
        GenericCarousel<T> gC = this;

        this.itemsProperty().get().addListener(new ListChangeListener<T>() {
            @Override
            public void onChanged(Change<? extends T> change) {
                System.out.print(items.get().size());
                carouselElementList.clear();
                for (T observable : items.get()) {
                    CarouselElement<T> element = cellFactory.call(gC);
                    element.updateItem(observable,false);
                    carouselElementList.add(element);
                }
                refreshUi();
            }
        });
        carouselElementList.clear();
        for (T observable : items.get()) {
            CarouselElement<T> element = cellFactory.call(gC);
            element.updateItem(observable,false);
            carouselElementList.add(element);
        }
        centered.getChildren().addAll(left,center,right);
//        getChildren().addAll(left,center,right);
        getChildren().addAll(centered);
        setFocusTraversable(true);
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    move(-1);
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    move(1);
                }
            }
        });
        if (items.get().size() % 2 == 0) {
            centerIndex.setValue(items.get().size()/2);
        } else {
            centerIndex.setValue((items.get().size()-1)/2);
        }

        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                refreshUi();
            }
        });
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                refreshUi();
            }
        });

        setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                actionCallback.call(items.get().get(centerIndex.get()));
            }
        });

        refreshUi();
    }

    @Override
    protected void layoutChildren() {
        clip.setHeight(getHeight());
        clip.setWidth(getWidth());
//        centered.setLayoutY(getHeight()/2);
//        centered.setLayoutX((getWidth()/2)-((ImageView) center.getChildren().get(0)).getImage().getWidth()/2);
        if (center.getChildren().size() != 0) {
            centered.setLayoutX((getWidth()/2)-center.getChildren().get(0).getBoundsInLocal().getWidth()/2);
        } else {
            centered.setLayoutX((getWidth()/2));
        }
        for (CarouselElement<T> element : carouselElementList) {
            // Resizing content
            if (element.getContent() instanceof Region) {
                ((Region) element.getContent()).setMaxHeight(getHeight());
                ((Region) element.getContent()).setPrefHeight(getHeight());
//                ((Pane) element.getContent()).setMinWidth(0);
            }
        }
    }

    public void setCellFactory(Callback<GenericCarousel<T>,CarouselElement<T>> cellFactory) {
        this.cellFactory = cellFactory;
        carouselElementList.clear();
        for (T observable : items.get()) {
            CarouselElement<T> element = cellFactory.call(this);
            element.updateItem(observable,false);
            carouselElementList.add(element);
        }
    }
    private synchronized void refreshUi() {
        System.out.println("Refreshing ui....");
        left.getChildren().clear();
        center.getChildren().clear();
        right.getChildren().clear();

        if (carouselElementList.size() == 0) {
            return;
        }

        if (timeline != null) {
            timeline.stop();
            timeline.getKeyFrames().clear();
        } else {
            timeline = new Timeline();
        }

        double totalLeftWidth = 0;
        for (int i = 0; i<centerIndex.get(); i++){
            totalLeftWidth += ( carouselElementList.get(i).getContent().getBoundsInLocal().getWidth())+SPACING;
        }
        ObservableList<KeyFrame> keyFrames = timeline.getKeyFrames();
        for (int i = 0; i< carouselElementList.size(); i++) {
           if (i < centerIndex.get()) {
               double newX = -totalLeftWidth+SPACING;
               if (left.getChildren().size()>0) {
                   for (int x = 0; x < left.getChildren().size(); x++) {
                       newX += SPACING + (left.getChildren().get(x).getBoundsInLocal().getWidth());
                   }
               }
               Node element = carouselElementList.get(i).getContent();
               if (-newX>((getWidth()/2)+element.getBoundsInLocal().getWidth())){
                   totalLeftWidth = totalLeftWidth-element.getBoundsInLocal().getWidth();
                   continue;
               }
               keyFrames.add(new KeyFrame(DURATION,
                       new KeyValue(element.layoutXProperty(), newX, INTERPOLATOR),
                       new KeyValue(element.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),
                       new KeyValue(element.scaleYProperty(), SCALE_SMALL, INTERPOLATOR)));
               left.getChildren().add(element);
           }else if (i == centerIndex.get()) {
               Node element = carouselElementList.get(i).getContent();
               center.getChildren().add(element);
               keyFrames.add(new KeyFrame(DURATION,
                       new KeyValue(element.layoutXProperty(), 0, INTERPOLATOR),
                       new KeyValue(element.scaleXProperty(), 1, INTERPOLATOR),
                       new KeyValue(element.scaleYProperty(), 1, INTERPOLATOR)));
           } else {
               double newX = SPACING;
               if (right.getChildren().size()>0) {
                   for (int x = 0; x < right.getChildren().size(); x++) {
                       newX += SPACING + (right.getChildren().get(x).getBoundsInLocal().getWidth());
                   }
               }
               Node centerElement = carouselElementList.get(centerIndex.get()).getContent();
               newX = newX+centerElement.getBoundsInLocal().getWidth();
               Node element = carouselElementList.get(i).getContent();
               if (newX>((getWidth()/2)+element.getBoundsInLocal().getWidth())){
                   continue;
               }
               keyFrames.add(new KeyFrame(DURATION,
                       new KeyValue(element.layoutXProperty(), newX, INTERPOLATOR),
                       new KeyValue(element.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),
                       new KeyValue(element.scaleYProperty(), SCALE_SMALL, INTERPOLATOR)));
               right.getChildren().add(element);
           }
        }
        timeline.play();
    }

    public void select(T item) {
        centerIndex.setValue(items.get().indexOf(item));
        refreshUi();
    }
    public void select(int index) {
        centerIndex.setValue(index);
        refreshUi();
    }

    private void move(int direction) {
//        centerIndex = (((centerIndex + direction) % items.get().size() ) + items.get().size()) % items.get().size();
        if (centerIndex.get()+direction<items.get().size() && centerIndex.get()+direction>=0) {
            centerIndex.setValue(centerIndex.get()+direction);
            refreshUi();
        }
    }
}
