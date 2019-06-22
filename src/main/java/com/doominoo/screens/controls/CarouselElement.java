package com.doominoo.screens.controls;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Created by marcmarquez on 29/03/17.
 */
public abstract class CarouselElement<T>{
   private Node content;
   private Label label = new Label();
   public CarouselElement() {

   }

   public void setGraphic(Node content) {
      this.content = content;
   }
   public void setText(String text) {
      label.setText(text);
   }

   public Label getLabel() {
      return label;
   }

   public Node getContent() {
      return content;
   }

   protected void updateItem(final T movie, boolean bln) {

   }

}
