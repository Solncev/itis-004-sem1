package com.solncev.fx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class JavaFxApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Line line = new Line();
        line.setStartX(50);
        line.setStartY(50);
        line.setEndX(150);
        line.setEndY(150);

        Text text = new Text();
        text.setFont(new Font(40));
        text.setX(100);
        text.setY(200);
        text.setText("Test text");


        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Some form");
        GridPane pane = new GridPane();
        ButtonType sendButtonType = new ButtonType("Send", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sendButtonType);
        TextField username = new TextField();
        username.setPromptText("Username");
        pane.add(username, 1, 0);
        dialog.getDialogPane().setContent(pane);

        dialog.setResultConverter(
                button -> {
                    if (button.equals(sendButtonType)) {
                        return username.getText();
                    }
                    return null;
                }
        );

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(System.out::println);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        Button click = new Button("click me");
        click.setOnAction(event -> alert.show());


        Group root = new Group();

        ObservableList list = root.getChildren();

        list.addAll(line, text, click);

        Scene mainScene = new Scene(root, 600, 600);
        primaryStage.setTitle("My first JavaFx Application");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        mainScene.setOnKeyPressed(
                key -> {
                    switch (key.getCode()) {
                        case A:
                            alert.show();
                            break;
                        case Q:
                            primaryStage.close();
                            break;
                    }
                }
        );
    }
}
