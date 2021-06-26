package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NullMove extends Move {

    public NullMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(null, null, -1);
    }

    @Override
    public Board execute() {
        throw new RuntimeException("Cannot execute a null move");
    }

}
