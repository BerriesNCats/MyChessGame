package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MajorPieceMove extends Move{

    public MajorPieceMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

}
