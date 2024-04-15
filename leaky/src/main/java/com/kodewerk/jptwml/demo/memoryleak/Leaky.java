package com.kodewerk.jptwml.demo.memoryleak;

/********************************************
 * Copyright (c) 2019 Kirk Pepperdine
 * All right reserved
 ********************************************/

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.management.ManagementFactory;

public class Leaky extends Application {

    private LeakyModel model;
    private TextField heapOccupancyTextField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Leaky");
        model = new LeakyModel();

        TextField integerField = new TextField("1000000");

        Button button = new Button("Do Stuff");
        button.setOnAction((event)-> model.leak(Integer.valueOf(integerField.getText())));

        HBox controls = new HBox(5, integerField, button);

        heapOccupancyTextField = new TextField("0");
        HBox views = new HBox( 5, new Label("Heap Occupancy (KB)", heapOccupancyTextField));
        VBox root = new VBox(5, views, controls);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 450, 90);
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), (ActionEvent actionEvent) ->
                        heapOccupancyTextField.setText(Long.toString(
                                ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() / 1024))));
        timeline.setCycleCount(1000);
        timeline.setAutoReverse(true);  //!?
        timeline.play();
    }
}
