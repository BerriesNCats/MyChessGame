package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;

public class MoveFactory {

    private MoveFactory() {
        throw new RuntimeException("You cannot create a MoveFactory object.");
    }

    public static Move createMove(Board board, int currentCoordinate, int destinationCoordinate) {
        for (final Move move : board.getAllLegalMoves()) {
            if (move.getCurrentCoordinate() == currentCoordinate &&
                    move.getDestinationCoordinate() == destinationCoordinate) {
                return move;
            }
        }
        return Move.NULL_MOVE;
    }
}
