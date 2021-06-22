package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.*;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Board {

    private List<Tile> gameBoard;

    Board(BoardBuilder boardBuilder) {
        this.gameBoard = createGameBoard(boardBuilder);
    }

    private static List<Tile> createGameBoard(final BoardBuilder boardBuilder) {
        final Tile[] tiles = new Tile[BoardUtils.NUMBER_OF_TILES];
        for (int i = 0; i < BoardUtils.NUMBER_OF_TILES; i++) {
            tiles[i] = Tile.createTile(i, boardBuilder.boardConfiguration.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static Board createStandardBoard() {
        final BoardBuilder boardBuilder = new BoardBuilder();

        // Black Major Pieces
        boardBuilder.setPiece(new Rook(Alliance.BLACK, 0));
        boardBuilder.setPiece(new Knight(Alliance.BLACK, 1));
        boardBuilder.setPiece(new Bishop(Alliance.BLACK, 2));
        boardBuilder.setPiece(new Queen(Alliance.BLACK, 3));
        boardBuilder.setPiece(new King(Alliance.BLACK, 4));
        boardBuilder.setPiece(new Bishop(Alliance.BLACK, 5));
        boardBuilder.setPiece(new Knight(Alliance.BLACK, 6));
        boardBuilder.setPiece(new Rook(Alliance.BLACK, 7));
        // Black Pawns
        boardBuilder.setPiece(new Pawn(Alliance.BLACK, 8));
        boardBuilder.setPiece(new Pawn(Alliance.BLACK, 9));
        boardBuilder.setPiece(new Pawn(Alliance.BLACK, 10));
        boardBuilder.setPiece(new Pawn(Alliance.BLACK, 11));
        boardBuilder.setPiece(new Pawn(Alliance.BLACK, 12));
        boardBuilder.setPiece(new Pawn(Alliance.BLACK, 13));
        boardBuilder.setPiece(new Pawn(Alliance.BLACK, 14));
        boardBuilder.setPiece(new Pawn(Alliance.BLACK, 15));

        // White Major Pieces
        boardBuilder.setPiece(new Rook(Alliance.WHITE, 63));
        boardBuilder.setPiece(new Knight(Alliance.WHITE, 62));
        boardBuilder.setPiece(new Bishop(Alliance.WHITE, 61));
        boardBuilder.setPiece(new King(Alliance.WHITE, 60));
        boardBuilder.setPiece(new Queen(Alliance.WHITE, 59));
        boardBuilder.setPiece(new Bishop(Alliance.WHITE, 58));
        boardBuilder.setPiece(new Knight(Alliance.WHITE, 57));
        boardBuilder.setPiece(new Rook(Alliance.WHITE, 56));
        // White Pawns
        boardBuilder.setPiece(new Pawn(Alliance.WHITE, 55));
        boardBuilder.setPiece(new Pawn(Alliance.WHITE, 54));
        boardBuilder.setPiece(new Pawn(Alliance.WHITE, 53));
        boardBuilder.setPiece(new Pawn(Alliance.WHITE, 52));
        boardBuilder.setPiece(new Pawn(Alliance.WHITE, 51));
        boardBuilder.setPiece(new Pawn(Alliance.WHITE, 50));
        boardBuilder.setPiece(new Pawn(Alliance.WHITE, 49));
        boardBuilder.setPiece(new Pawn(Alliance.WHITE, 48));

        boardBuilder.setNextMoveAlliance(Alliance.WHITE);

        return boardBuilder.build();
    }

    public Tile getTile(final int tileCoordinate) {
        return null;
    }
}
