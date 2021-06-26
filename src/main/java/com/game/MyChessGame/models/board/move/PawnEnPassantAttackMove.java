package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PawnEnPassantAttackMove extends PawnAttackMove{

    public PawnEnPassantAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackPiece) {
        super(board, movedPiece, destinationCoordinate, attackPiece);
    }

}
