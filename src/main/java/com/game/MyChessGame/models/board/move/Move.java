package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.BoardBuilder;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Move {

    protected Board board;
    protected Piece movedPiece;
    protected int destinationCoordinate;

    public static Move NULL_MOVE = new NullMove();

    public Board execute() {
        // To make a move, you do not mutate the current board, you construct a new board
        BoardBuilder boardBuilder = new BoardBuilder();
        // Setting the pieces of the current player
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            // TODO hashcode and equals for pieces
            if (!this.movedPiece.equals(piece)) {
                boardBuilder.setPiece(piece);
            }
        }  // Setting the pieces of the current opponent player
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            boardBuilder.setPiece(piece);
        }
        // TODO
        // Move the moved piece
        boardBuilder.setPiece(null);
        boardBuilder.setNextMoveAlliance(this.board.getCurrentPlayer().getOpponent().getAlliance());

        return boardBuilder.build();
    }

    public int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }
}
