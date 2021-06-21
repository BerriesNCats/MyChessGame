package com.game.MyChessGame.models.pieces;

import com.game.MyChessGame.models.Alliance;
import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.Move;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
public abstract class Piece {

    protected int piecePosition;

    protected Alliance pieceAlliance;

    Piece() {}
    public abstract List<Move> calculateLegalMoves(final Board board);

}
