package com.game.MyChessGame.models.player;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.Move;
import com.game.MyChessGame.models.pieces.King;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;

import java.util.Collection;

@Data
public abstract class Player {

    protected Board board;
    protected King playerKing;
    protected Collection<Move> legalMoves;

    Player(Board board,
           Collection<Move> legalMoves,
           Collection<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    private King establishKing() {
        for(Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("The state of this board is invalid.");
    }

    public abstract Collection<Piece> getActivePieces();

}
