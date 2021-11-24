package com.solncev.fxml.controller;

import com.solncev.fxml.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;

public class UserController {

    @FXML
    private TextField nickname;

    @FXML
    private Button search;

    @FXML
    private TableView tableView;

    @FXML
    private Label label;

    @FXML
    private VBox users;

    private final ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<User> resultList = FXCollections.observableArrayList();

    public UserController() {
        userList.add(new User(1, "Ivan", 50));
        userList.add(new User(3, "Petr", 60));
        userList.add(new User(2, "Artem", 70));
    }

    @FXML
    private void initialize() {
        search.setText("Search");
        search.setStyle("-fx-background-color: #ff0000");

        search.setOnAction(event -> loadData());

        nickname.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                loadData();
            }
        });

        nickname.textProperty().addListener(((observable, oldValue, newValue) ->
                label.setText(newValue)
        ));

        initTable();
    }

    private void loadData() {
        String searchText = nickname.getText();

        Task<ObservableList<User>> task = new Task<ObservableList<User>>() {
            @Override
            protected ObservableList<User> call() {
                return FXCollections.observableArrayList(
                        userList.stream()
                                .filter(u -> u.getNickname().toLowerCase().contains(searchText.toLowerCase()))
                                .collect(Collectors.toList())
                );
            }
        };


        task.setOnSucceeded(event -> {
            resultList = task.getValue();
            tableView.setItems(resultList);
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void initTable() {
        tableView = new TableView(FXCollections.observableList(userList));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn nickname = new TableColumn("NICKNAME");
        nickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));

        TableColumn points = new TableColumn("POINTS");
        points.setCellValueFactory(new PropertyValueFactory<>("points"));

        tableView.getColumns().addAll(id, nickname, points);

        users.getChildren().add(tableView);
    }
}
