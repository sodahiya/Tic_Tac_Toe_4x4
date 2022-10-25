package com.example.tic_tac_toe_4x4;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private final StackPane pane;
    Info info;
    int NO_OF_TILES = UIConstants.NO_OF_TILES;
    private int playersTurn =1;
    private Boolean isEndOfGame= false;

    private List<Tile> winningTiles = new ArrayList<Tile>();
    private Tile[][] tiles = new Tile[NO_OF_TILES][NO_OF_TILES];
    public Board(Info info){
        this.info = info;
        pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH,UIConstants.BOARD_HEIGHT);
        pane.setTranslateX(UIConstants.APP_WIDTH/2);
        pane.setTranslateY((UIConstants.BOARD_HEIGHT/2) + UIConstants.INFO_CENTER_HEIGHT);

        addALLTiles();

    }
    private void addALLTiles() {
        for(int row =0;row<NO_OF_TILES;row++)
            for(int col =0; col<NO_OF_TILES;col++){
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((col * UIConstants.TILE_WIDTH) - ((UIConstants.NO_OF_TILES -1) * (UIConstants.TILE_WIDTH/2)));
                tile.getStackPane().setTranslateY((row * UIConstants.TILE_HEIGHT) - ((UIConstants.NO_OF_TILES -1) * (UIConstants.TILE_HEIGHT/2)));

                pane.getChildren().add(tile.getStackPane());
                tiles[row][col] = tile;
            }
    }
    public void startNewGame(){
        System.out.println("Start Function called");
        isEndOfGame = false;
        playersTurn =1;
        for(int row =0 ; row<NO_OF_TILES;row++)
            for(int col =0;col<NO_OF_TILES;col++) {
                tiles[row][col].setValue("");
                tiles[row][col].border.setStroke(UIConstants.BORDER_COLOR);
            }

    }
    public void changePlayerTurn(){
        if (playersTurn == 1) {
            playersTurn = 0;
            info.updateMessage(UIConstants.O_TURN);
        }
        else{
            playersTurn=1;
            info.updateMessage(UIConstants.X_TURN);
        }
    }
    public int getPlayersTurn(){
        return playersTurn;
    }

    public StackPane getStackPane(){
        return pane;
    }
    public void checkForWinner(){
        checkRows();
        checkCols();
        checkDiagonals();
        checkCorners();
        checkSquares();
        checkStaleMate();
    }
    private void checkRows() {
        boolean flag;
        String val;
        if(!isEndOfGame) {
            for (int row = 0; row < NO_OF_TILES; row++) {
                flag = true;
                winningTiles.clear();
                if (!tiles[row][0].getValue().isEmpty())
                    val = tiles[row][0].getValue();
                else
                    continue;
                for (int col = 0; col < NO_OF_TILES; col++) {
                    if (!tiles[row][col].getValue().equals(val)) {
                        flag = false;
                        break;
                    }
                    else
                        winningTiles.add(tiles[row][col]);
                }

                System.out.println("Row "+row+": "+flag);
                if (flag) {
                    endGame(val,winningTiles);
                }
            }
        }
    }
    private void checkCols(){
        boolean flag;
        String val;
        if(!isEndOfGame) {
            for (int col = 0; col < NO_OF_TILES; col++) {
                flag = true;
                winningTiles.clear();
                if (!tiles[0][col].getValue().isEmpty())
                    val = tiles[0][col].getValue();
                else
                    continue;
                for (int row = 0; row < NO_OF_TILES; row++) {
                    if (!tiles[row][col].getValue().equals(val)) {
                        flag = false;
                        break;
                    }
                    else
                        winningTiles.add(tiles[row][col]);
                }
                System.out.println("Col "+col+": "+flag);
                if (flag) {
                    endGame(val,winningTiles);
                }
            }
        }
    }
    private void checkDiagonals(){
        boolean flag = true;
        String val;
        winningTiles.clear();
        if (!tiles[0][0].getValue().isEmpty())
             val = tiles[0][0].getValue();
        else
            val= null;
        if(!isEndOfGame) {
            for (int row = 0; row < NO_OF_TILES; row++) {
                    if (!tiles[row][row].getValue().equals(val)) {
                        flag = false;
                        break;
                    }
                    else
                        winningTiles.add(tiles[row][row]);
                }
            System.out.println("Diagonal : "+flag);
            if (flag) {
                endGame(val,winningTiles);
            }
        }

        flag = true;
        winningTiles.clear();
        if (!tiles[0][NO_OF_TILES -1].getValue().isEmpty())
            val = tiles[0][NO_OF_TILES -1].getValue();
        else
            val= null;
        if(!isEndOfGame) {
            for (int row = 0; row < NO_OF_TILES; row++) {
                if (!tiles[row][NO_OF_TILES- row-1].getValue().equals(val)) {
                    flag = false;
                    break;
                }
                else
                    winningTiles.add(tiles[row][NO_OF_TILES-row-1]);
            }
            System.out.println("Diagonal : " + flag);
            if (flag) {
                endGame(val,winningTiles);
            }
        }
    }
    private void checkCorners(){
        winningTiles.clear();
        if(tiles[0][0].getValue().equals(tiles[NO_OF_TILES-1][0].getValue()) && tiles[0][0].getValue().equals(tiles[0][NO_OF_TILES-1].getValue()) && tiles[0][0].getValue().equals(tiles[NO_OF_TILES-1][NO_OF_TILES-1].getValue()) && !tiles[0][0].getValue().isEmpty() && !isEndOfGame)
        {
            String winner = tiles[0][0].getValue();
            winningTiles.add(tiles[0][0]);
            winningTiles.add(tiles[0][NO_OF_TILES-1]);
            winningTiles.add(tiles[NO_OF_TILES-1][0]);
            winningTiles.add(tiles[NO_OF_TILES-1][NO_OF_TILES -1]);
            endGame(winner,winningTiles);
        }
    }
    private void checkSquares(){
        winningTiles.clear();
        for(int row =0;row<NO_OF_TILES-1;row++){
            for (int col =0;col<NO_OF_TILES-1;col++){
                if(tiles[row][col].getValue().equals(tiles[row+1][col].getValue()) && tiles[row][col].getValue().equals(tiles[row][col+1].getValue()) && tiles[row][col].getValue().equals(tiles[row+1][col+1].getValue()) && !tiles[row][col].getValue().isEmpty() && !isEndOfGame)
                {
                    String winner = tiles[row][row].getValue();
                    winningTiles.add(tiles[row][col]);
                    winningTiles.add(tiles[row+1][col]);
                    winningTiles.add(tiles[row][col+1]);
                    winningTiles.add(tiles[row+1][col+1]);
                    endGame(winner,winningTiles);
                    break;
                }
            }
        }
    }


    private void checkStaleMate(){
        if(!isEndOfGame){
            for (int row =0 ;row<NO_OF_TILES;row++)
            {
                for(int col =0 ;col<NO_OF_TILES;col++){
                    if(tiles[row][col].getValue().isEmpty())
                        return;
                }
            }
            isEndOfGame = true;
            info.updateMessage("Cat's Eye...");
            info.showStartButton();
        }
    }

    private void endGame(String Winner,List<Tile> winningTiles){
        isEndOfGame = true;
        info.updateMessage("Winner is player: "+Winner);
        info.showStartButton();

        for (Tile winningTile : winningTiles) {
            if (Objects.equals(Winner, "X"))
                winningTile.getBorder().setStroke(UIConstants.X_COLOR);
            else
                winningTile.getBorder().setStroke(UIConstants.O_COLOR);
        }

    }
    private class Tile {
        private StackPane pane;
        private Label label;
        private Rectangle border;

        public Tile() {
            pane = new StackPane();
            pane.setMinSize(UIConstants.TILE_WIDTH, UIConstants.TILE_HEIGHT);

            border = new Rectangle();
            border.setHeight(UIConstants.TILE_HEIGHT);
            border.setWidth(UIConstants.TILE_WIDTH);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(UIConstants.BORDER_COLOR);
            pane.getChildren().add(border);

            label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(36));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(mouseEvent -> {
                if (label.getText().isEmpty() && !isEndOfGame) {
                    if (getPlayersTurn() == 1){
                        label.setTextFill(UIConstants.X_COLOR);
                        label.setText("X");
                    }
                    else{
                        label.setTextFill(UIConstants.O_COLOR);
                        label.setText("O");
                    }

                    changePlayerTurn();
                    checkForWinner();
                }
            });
        }

        public StackPane getStackPane() {
            return pane;
        }

        public String getValue() {
            return label.getText();
        }

        public void setValue(String value) {
            label.setText(value);
        }
        public Rectangle getBorder(){
            return border;
        }
    }
}
