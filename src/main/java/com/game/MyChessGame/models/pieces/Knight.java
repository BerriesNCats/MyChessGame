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
public class Knight extends Piece{

    private final static int JUMP_BACKWARD_LEFT = -15;
    private final static int JUMP_BACKWARD_LEFT_BACKWARD = -17;
    private final static int JUMP_BACKWARD_RIGHT = -6;
    private final static int JUMP_BACKWARD_RIGHT_BACKWARD = -10;
    private final static int JUMP_FORWARD_LEFT = 6;
    private final static int JUMP_FORWARD_LEFT_FORWARD = 10;
    private final static int JUMP_FORWARD_RIGHT = 15;
    private final static int JUMP_FORWARD_RIGHT_FORWARD = 17;

    private final static int[] CANDIDATE_MOVE_COORDINATES = {
            JUMP_BACKWARD_LEFT, JUMP_BACKWARD_LEFT_BACKWARD, JUMP_BACKWARD_RIGHT, JUMP_BACKWARD_RIGHT_BACKWARD,
            JUMP_FORWARD_LEFT, JUMP_FORWARD_LEFT_FORWARD, JUMP_FORWARD_RIGHT, JUMP_FORWARD_RIGHT_FORWARD
    };

    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateDestinationOffset : Knight.CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition + currentCandidateDestinationOffset;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateDestinationOffset) ||
                    isSecondColumnExclusion(this.piecePosition, currentCandidateDestinationOffset) ||
                    isSeventhColumnExclusion(this.piecePosition, currentCandidateDestinationOffset) ||
                    isEighthColumnExclusion(this.piecePosition, currentCandidateDestinationOffset)) {
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorPieceMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove
                                (board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] &&
                (candidateOffset == JUMP_BACKWARD_LEFT_BACKWARD || candidateOffset == JUMP_BACKWARD_RIGHT_BACKWARD ||
                candidateOffset == JUMP_FORWARD_LEFT || candidateOffset == JUMP_FORWARD_RIGHT);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] &&
                (candidateOffset == JUMP_BACKWARD_RIGHT_BACKWARD || candidateOffset == JUMP_FORWARD_LEFT);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == JUMP_BACKWARD_RIGHT ||
                candidateOffset == JUMP_FORWARD_LEFT_FORWARD);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] &&
                (candidateOffset == JUMP_BACKWARD_LEFT || candidateOffset == JUMP_BACKWARD_RIGHT ||
                candidateOffset == JUMP_FORWARD_LEFT_FORWARD || candidateOffset == JUMP_FORWARD_RIGHT_FORWARD);
    }
}
