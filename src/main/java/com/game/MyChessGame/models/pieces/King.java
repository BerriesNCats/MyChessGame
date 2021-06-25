package com.game.MyChessGame.models.pieces;

import com.game.MyChessGame.models.board.*;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class King extends Piece{

    private final static int ONE_SPACE_DOWN = 8;
    private final static int ONE_SPACE_UP = -8;
    private final static int ONE_SPACE_LEFT = -1;
    private final static int ONE_SPACE_RIGHT = 1;
    private final static int ONE_SPACE_LEFT_DIAGONAL_DOWN = 7;
    private final static int ONE_SPACE_RIGHT_DIAGONAL_UP = -7;
    private final static int ONE_SPACE_RIGHT_DIAGONAL_DOWN = 9;
    private final static int ONE_SPACE_LEFT_DIAGONAL_UP = -9;

    private final static int[] CANDIDATE_MOVE_COORDINATES = {
            ONE_SPACE_DOWN, ONE_SPACE_UP, ONE_SPACE_LEFT, ONE_SPACE_RIGHT, ONE_SPACE_LEFT_DIAGONAL_DOWN,
            ONE_SPACE_RIGHT_DIAGONAL_UP, ONE_SPACE_RIGHT_DIAGONAL_DOWN, ONE_SPACE_LEFT_DIAGONAL_UP
    };

    public King(final Alliance pieceAlliance, final int piecePosition) {
        super(pieceAlliance, piecePosition);
    }

    // TODO FIX
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateDestinationOffset : King.CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition + currentCandidateDestinationOffset;
            if(isFirstColumnExclusion(this.piecePosition, currentCandidateDestinationOffset) ||
                    isEighthColumnExclusion(this.piecePosition, currentCandidateDestinationOffset)) {
                continue;
            }
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorPieceMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove
                                (board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    // If a King is on the 1st column it cannot move left in any direction
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == ONE_SPACE_LEFT_DIAGONAL_UP ||
                candidateOffset == ONE_SPACE_LEFT || candidateOffset == ONE_SPACE_LEFT_DIAGONAL_DOWN);
    }

    // If a King is on the 8th column it cannot move right in any direction
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == ONE_SPACE_RIGHT_DIAGONAL_UP ||
                candidateOffset == ONE_SPACE_RIGHT || candidateOffset == ONE_SPACE_RIGHT_DIAGONAL_DOWN);
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
}
