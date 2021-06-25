package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.*;
import com.game.MyChessGame.models.player.BlackPlayer;
import com.game.MyChessGame.models.player.WhitePlayer;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
public class Board {

    private List<Tile> gameBoard;
    private Collection<Piece> whitePieces;
    private Collection<Piece> blackPieces;

    private WhitePlayer whitePlayer;
    private BlackPlayer blackPlayer;

    Board(BoardBuilder boardBuilder) {
        this.gameBoard = createGameBoard(boardBuilder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);

        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces) {
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return ImmutableList.copyOf(legalMoves);
    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    private static List<Tile> createGameBoard(final BoardBuilder boardBuilder) {
        final Tile[] tiles = new Tile[BoardUtils.NUMBER_OF_TILES];
        for (int i = 0; i < BoardUtils.NUMBER_OF_TILES; i++) {
            tiles[i] = Tile.createTile(i, boardBuilder.boardConfiguration.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();
        for (final Tile tile : gameBoard) {
            if(tile.isTileOccupied()) {
                Piece piece = tile.getPiece();
                if(piece.getPieceAlliance() == alliance) {
                    activePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activePieces);
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

        // Sets white to have the first move
        boardBuilder.setNextMoveAlliance(Alliance.WHITE);

        return boardBuilder.build();
    }

    public Tile getTile(final int tileCoordinate) {
        return gameBoard.get(tileCoordinate);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < BoardUtils.NUMBER_OF_TILES; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            stringBuilder.append(String.format("%3s", tileText));
            if ((i + 1) % BoardUtils.NUMBER_OF_TILES_PER_ROW == 0) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
