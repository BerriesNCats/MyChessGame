package com.game.MyChessGame.models.player;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.Move;
import com.game.MyChessGame.models.pieces.Piece;

import java.util.Collection;

public class BlackPlayer extends Player{

    public BlackPlayer(Board board,
                       Collection<Move> whiteStandardLegalMoves,
                       Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }
}
