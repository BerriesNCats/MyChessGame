package com.game.MyChessGame.models.board;

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

}
