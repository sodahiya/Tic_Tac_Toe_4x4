package com.example.tic_tac_toe_4x4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    private Board board;
    private Info info;
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        initLayout(root);
        stage.setTitle(UIConstants.TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    private void initLayout(BorderPane root) {
        initInfo(root);
        initTileBoard(root);
    }

    private void initTileBoard(BorderPane root) {
        board = new Board(info);
        root.getChildren().add(board.getStackPane());
    }

    private void initInfo(BorderPane root) {
        info = new Info();
        info.setStartGameButtonOnAction(startNewGame());
        root.getChildren().add(info.getStackPane());
    }
    private EventHandler<ActionEvent> startNewGame(){
        return  new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                info.hideStartButton();
                info.updateMessage(UIConstants.X_TURN);
                board.startNewGame();
            }
        };
    }
    public static void main(String[] args) {
        launch();
    }
}