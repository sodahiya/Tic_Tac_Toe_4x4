package com.example.tic_tac_toe_4x4;

import javafx.scene.paint.Color;

public class UIConstants {
    public static final int TILE_WIDTH = 100;
    public static final int TILE_HEIGHT = 100;
    public static final int INFO_CENTER_HEIGHT = 100;
    public static final int NO_OF_TILES = 4;


    public static final int BOARD_HEIGHT = NO_OF_TILES * TILE_HEIGHT;
    public static final int APP_WIDTH = NO_OF_TILES * TILE_WIDTH;
    public static final int APP_HEIGHT = NO_OF_TILES * TILE_HEIGHT + INFO_CENTER_HEIGHT;


    public static final String TITLE ="Tic-Tac-Toe";
    public static final String START_NEW_GAME ="Start new Game";
    public static final String X_TURN ="X's Turn";
    public static final String O_TURN ="O's Turn";

    public static final Color X_COLOR = Color.web("#40E0D0");
    public static final Color O_COLOR = Color.web("#FF8C00");

    public static final Color BORDER_COLOR = Color.web("#D9D9D9");
}
