package com.example.tic_tac_toe_4x4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;


public class Info {
    private StackPane pane;
    private Label message;
    private Button startGameButton;

    public Info(){
        pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH,UIConstants.INFO_CENTER_HEIGHT);
        pane.setTranslateX((UIConstants.APP_WIDTH/2));
        pane.setTranslateY(UIConstants.INFO_CENTER_HEIGHT/2);

        message = new Label(UIConstants.X_TURN);
        message.setMinSize(UIConstants.APP_WIDTH,UIConstants.INFO_CENTER_HEIGHT);
        message.setFont(Font.font(24));
        message.setAlignment(Pos.CENTER);

        message.setTranslateY(-20);

        pane.getChildren().add(message);

        startGameButton = new Button(UIConstants.START_NEW_GAME);
        startGameButton.setMinSize(135,30);
        startGameButton.setTranslateY(20);
        startGameButton.setStyle("-fx-background-color:  #22CCF2 ;-fx-border: none;-fx-font-size: 14px;-fx-text-fill: white;");
        pane.getChildren().add(startGameButton);
        startGameButton.setVisible(false);
    }

    public StackPane getStackPane(){
        return pane;
    }

    public void updateMessage(String message){
        this.message.setText(message);
    }
    public void showStartButton(){
        startGameButton.setVisible(true);
    }
    public void hideStartButton(){
        startGameButton.setVisible(false);
    }

    public void setStartGameButtonOnAction(EventHandler<ActionEvent> onAction){
        startGameButton.setOnAction(onAction);
    }

}
