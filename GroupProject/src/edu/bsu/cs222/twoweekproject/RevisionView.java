package edu.bsu.cs222.twoweekproject;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RevisionView extends VBox {

    public RevisionView(Revision revision) {
        Button button = new Button("User: " + revision.user);
        getChildren().addAll(
                button,
                new Label("Timsetamp: " + revision.timestamp));
        button.setOnAction(e->clickedOnUser());
    }
    
    private void clickedOnUser() {

        System.out.println("We did it!");
    }
}
