package com.game.MyChessGame.models.pieces;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.BoardUtils;
import com.game.MyChessGame.models.board.Move;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class King extends Piece{

    private final static int ONE_SPACE_LEFT_DIAGONAL_UP = -9;
    private final static int ONE_SPACE_UP = -8;
    private final static int ONE_SPACE_RIGHT_DIAGONAL_UP = -7;
    private final static int ONE_SPACE_LEFT = -1;
    private final static int ONE_SPACE_RIGHT = 1;
    private final static int ONE_SPACE_LEFT_DIAGONAL_DOWN = 7;
    private final static int ONE_SPACE_DOWN = 8;
    private final static int ONE_SPACE_RIGHT_DIAGONAL_DOWN = 9;

    private final static int[] CANDIDATE_MOVE_COORDINATES = {
            ONE_SPACE_LEFT_DIAGONAL_UP, ONE_SPACE_UP, ONE_SPACE_RIGHT_DIAGONAL_UP, ONE_SPACE_LEFT,
            ONE_SPACE_RIGHT, ONE_SPACE_LEFT_DIAGONAL_DOWN, ONE_SPACE_DOWN, ONE_SPACE_RIGHT_DIAGONAL_DOWN
    };

    public King(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    // TODO FIX
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateDestinationCoordinate : King.CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
        }

        return ImmutableList.copyOf(legalMoves);
    }

    // TODO FIX
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -9 ||
                candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 ||
                candidateOffset == 9);
    }
}
