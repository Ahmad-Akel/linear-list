package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Obadah Al Hariri
 */
public class ControlPanelVBox extends VBox {

    public ControlPanelVBox() {
        setPadding(new Insets(0, 0, 0, 15));
        setSpacing(35);
    }

    public void addButton(String name, EventHandler<ActionEvent> handler) {
        Button button = new Button(name);
        button.setPrefSize(100, 30);
        button.setOnAction(handler);
        getChildren().add(button);
    }

    public void addLabel(String text) {
        Label label = new Label(text);
        getChildren().add(label);
    }

}
