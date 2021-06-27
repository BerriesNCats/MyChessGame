package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MajorPieceMove extends Move {

    /**
     * To construct a Major Piece Move, send in the current board, the piece being moved and the tile coordinate the
     * moving piece intends to move to.
     *
     * @param board the game board
     * @param movedPiece the piece being moved
     * @param destinationCoordinate the coordinate of the tile the piece is moving to
     */
    public MajorPieceMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

}
