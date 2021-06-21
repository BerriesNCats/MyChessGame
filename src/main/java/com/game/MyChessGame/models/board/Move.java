package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Piece;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Move {

    protected Board board;
    protected Piece movedPiece;
    protected int destinationCoordinate;

}
