package com.game.MyChessGame.models.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] SEVENTH_ROW = initRow(48);

    public static final int NUMBER_OF_TILES = 64;
    public static final int NUMBER_OF_TILES_PER_ROW = 8;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate a Board Utils object.");
    }

    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[NUMBER_OF_TILES];
        do {
            column[columnNumber] = true;
            columnNumber += NUMBER_OF_TILES_PER_ROW;
        } while (columnNumber < NUMBER_OF_TILES);
        return column;
    }

    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[NUMBER_OF_TILES];
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while (rowNumber % NUMBER_OF_TILES_PER_ROW != 0);
        return row;
    }

    public static boolean isValidTileCoordinate(final int coordinate) {
        return (coordinate >= 0 && coordinate < 64);
    }
}
