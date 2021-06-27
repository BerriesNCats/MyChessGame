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

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + this.destinationCoordinate;
        result = prime * result + this.movedPiece.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Move)) return false;
        Move otherMove = (Move) other;
        return getDestinationCoordinate() == otherMove.getDestinationCoordinate() &&
                getMovedPiece().equals(otherMove.getMovedPiece());
    }

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
        // Move the moved piece
        boardBuilder.setPiece(this.movedPiece.movePiece(this));
        boardBuilder.setNextMoveAlliance(this.board.getCurrentPlayer().getOpponent().getAlliance());

        return boardBuilder.build();
    }

    public int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }

    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public Piece getAttackedPiece() {
        return null;
    }
}
