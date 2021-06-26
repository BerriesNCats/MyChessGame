package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.move.AttackMove;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PawnAttackMove extends AttackMove {

    public PawnAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackPiece) {
        super(board, movedPiece, destinationCoordinate, attackPiece);
    }

}
