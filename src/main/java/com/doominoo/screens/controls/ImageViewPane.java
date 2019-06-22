package com.doominoo.screens.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;


/**
 * Created by marcmarquez on 27/03/17.
 */
public class ImageViewPane extends Region {
    private ObjectProperty<ImageView> imageViewProperty = new SimpleObjectProperty<>();
//    private Rectangle clip = new Rectangle();

    public ObjectProperty<ImageView> imageViewProperty() {
        return imageViewProperty;
    }

    public ImageView getImageView() {
        return imageViewProperty.get();
    }

    public void setImageView(ImageView imageView) {
        imageViewProperty.set(imageView);
    }

    public ImageViewPane() {
        this(new ImageView());
    }

    public ImageViewPane(ImageView imageView) {
//        setClip(clip);
        imageViewProperty.addListener(new ChangeListener<ImageView>() {
            @Override
            public void changed(ObservableValue<? extends ImageView> observableValue, ImageView oldImageView, ImageView newImageView) {
                if (oldImageView != null) {
                    getChildren().remove(oldImageView);
                }
                if (newImageView != null) {
                    getChildren().add(newImageView);
                }
            }
        });
        this.imageViewProperty.set(imageView);
    }

    @Override
    protected void layoutChildren() {
//        clip.setHeight(getHeight());
//        clip.setWidth(getWidth());
        ImageView imageView = imageViewProperty.get();
        if (imageView != null) {
            imageView.setFitHeight(getHeight());
//            imageView.setFitWidth(getWidth());
//            setWidth(getWidth());
//            setHeight(getHeight());
//            setPrefWidth(imageView.getBoundsInParent().getWidth());
            setPrefWidth(imageView.getBoundsInParent().getWidth());
            setWidth(imageView.getBoundsInParent().getWidth());
            setMaxWidth(Double.MAX_VALUE);
//            clip.setWidth(imageView.getBoundsInParent().getWidth());
//            System.out.println("view fitwidth "+imageView.getFitWidth());
//            System.out.println("image getWitdh "+imageView.getImage().getWidth());
//            System.out.println("view boundsinparent width"+imageView.getBoundsInParent().getWidth());
//            layoutInArea(imageView,0,0,getWidth(),getHeight(),0, HPos.CENTER, VPos.CENTER);
        }
        super.layoutChildren();
    }
}
