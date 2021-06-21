package com.game.MyChessGame.models.pieces;

import com.game.MyChessGame.models.Alliance;
import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.Move;

import java.util.List;

public class Rook extends Piece{


    public Rook(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        return null;
    }
}
