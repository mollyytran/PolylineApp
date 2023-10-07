//Molly Tran
//CS 56 Section 1733
//Assignment 7- ProjectApp

package com.example.projectapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class ProjectApp extends Application {

    private Polyline line =  null;
    private Pane root;
    private int clickCount = 0;

    @Override
    public void start(Stage primaryStage) {

        // Creates button node and sets text to Clear
        // Event handler removes all nodes of root Pane not of Button object and sets line object to null
        Button btn = new Button();
        btn.setText("Clear");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent clearWindow) {
                root.getChildren().removeIf(node -> !(node instanceof Button));
                line = null;
            }
        });

        // Creates root AnchorPane, adds and sets button to bottom of AnchorPane
        root = new AnchorPane();
        root.getChildren().add(btn);
        AnchorPane.setBottomAnchor(btn,5.0);

        // Calls mouseClick method when mouse is clicked
        root.setOnMouseClicked(this::mouseClick);

        //Creates scene and passes root Pane to the scene.
        Scene scene = new Scene(root, 400, 350);

        //Scene object is passed to the stage then displays it
        primaryStage.setTitle("ProjectApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // mouseClick method detects number of clicks from user. Single click creates a polyline from the previous point added to the list.
    //Double click ends the current polyline then sets line back to null.
    private void mouseClick(MouseEvent e) {
        if (e.getClickCount() == 1) {
            if (line == null) {
                line = new Polyline();
                line.setStroke(Color.BLACK);
                double x = e.getX();
                double y = e.getY();
                line.getPoints().addAll(x, y);
                root.getChildren().add(line);
            } else {
                line.getPoints().addAll(e.getX(),e.getY());
            }
        } else if (e.getClickCount() == 2 && line != null) {
            line = null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
