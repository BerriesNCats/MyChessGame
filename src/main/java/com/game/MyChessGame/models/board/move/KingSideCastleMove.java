package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.pieces.Piece;
import com.game.MyChessGame.models.pieces.Rook;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KingSideCastleMove extends CastleMove {

    /**
     * To construct a king side Castling Move, send in the current board, the piece (king) on the board being moved,
     * the tile coordinate the king intends to move to, the queen side rook, the tile coordinate that rook is located
     * on and the destination the rook will be placed on after castling.
     *
     * @param board the game board
     * @param movedPiece the piece (king) being moved
     * @param destinationCoordinate the coordinate of the tile the king will be placed on
     * @param kingSideRook the rook on the queens side
     * @param castleRookOrigin the coordinate of the tile the rook is located on before castling
     * @param castleRookDestination the coordinate of the tile the rook will be placed on after castling
     */
    public KingSideCastleMove(final Board board, final Piece movedPiece, final int destinationCoordinate,
                              final Rook kingSideRook, final int castleRookOrigin, final int castleRookDestination) {
        super(board, movedPiece, destinationCoordinate, kingSideRook, castleRookOrigin, castleRookDestination);
    }

    @Override
    public String toString() {
        return "O-O";
    }
}
