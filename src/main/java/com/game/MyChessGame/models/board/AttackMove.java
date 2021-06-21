package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Piece;

public class AttackMove extends Move{

    Piece attackedPiece;

    public AttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }

}
