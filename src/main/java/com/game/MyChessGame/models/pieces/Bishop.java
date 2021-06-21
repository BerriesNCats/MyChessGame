package com.game.MyChessGame.models.pieces;

import com.game.MyChessGame.models.Alliance;
import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.Move;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class Bishop extends Piece{

    public Bishop(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }
}
