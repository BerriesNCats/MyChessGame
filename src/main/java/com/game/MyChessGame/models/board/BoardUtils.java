package com.game.MyChessGame.models.board;

public class BoardUtils {

    public static final boolean[] A_FILE = initColumn(0);
    public static final boolean[] B_FILE = initColumn(1);
    public static final boolean[] C_FILE = initColumn(2);
    public static final boolean[] D_FILE = initColumn(3);
    public static final boolean[] E_FILE = initColumn(4);
    public static final boolean[] F_FILE = initColumn(5);
    public static final boolean[] G_FILE = initColumn(6);
    public static final boolean[] H_FILE = initColumn(7);

    public static final boolean[] FIRST_RANK = initRow(56);
    public static final boolean[] SECOND_RANK = initRow(48);
    public static final boolean[] THIRD_RANK = initRow(40);
    public static final boolean[] FOURTH_RANK = initRow(32);
    public static final boolean[] FIFTH_RANK = initRow(24);
    public static final boolean[] SIXTH_RANK = initRow(16);
    public static final boolean[] SEVENTH_RANK = initRow(8);
    public static final boolean[] EIGHTH_RANK = initRow(0);

    public static final int NUMBER_OF_TILES = 64;
    public static final int NUMBER_OF_TILES_PER_ROW = 8;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate a Board Utils object.");
    }

    /**
     * Marks each tile coordinate with a boolean value that represents weather or not it is located on a given file
     * based on the column number passed in.
     *
     * @param columnNumber the column number representing a file on a board
     * @return an array of boolean values indicating if a tile coordinate is located on a given file
     */
    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[NUMBER_OF_TILES];
        do {
            column[columnNumber] = true;
            columnNumber += NUMBER_OF_TILES_PER_ROW;
        } while (columnNumber < NUMBER_OF_TILES);
        return column;
    }

    /**
     * Marks each tile coordinate with a boolean value that represents weather or not it is located on a given rank
     * based on the row number passed in.
     *
     * @param rowNumber the row number representing a rank on a board
     * @return an array of boolean values indicating if a tile coordinate is located on a given rank
     */
    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[NUMBER_OF_TILES];
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while (rowNumber % NUMBER_OF_TILES_PER_ROW != 0);
        return row;
    }

    /**
     * Indicates with a boolean value if a tile coordinate exists as one of the 64 tiles on a board.
     *
     * @param coordinate the tile coordinate being considered
     * @return a boolean value indicating if the tile exists on the board
     */
    public static boolean isValidTileCoordinate(final int coordinate) {
        return (coordinate >= 0 && coordinate < NUMBER_OF_TILES);
    }
}
