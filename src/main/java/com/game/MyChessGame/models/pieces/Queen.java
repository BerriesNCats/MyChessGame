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
public class Queen extends Piece{

    private final static int ONE_SPACE_FORWARD = 8;
    private final static int ONE_SPACE_BACKWARD = -8;
    private final static int ONE_SPACE_LEFT = -1;
    private final static int ONE_SPACE_RIGHT = 1;
    private final static int ONE_SPACE_LEFT_DIAGONAL_FORWARD = 7;
    private final static int ONE_SPACE_LEFT_DIAGONAL_BACKWARD = -7;
    private final static int ONE_SPACE_RIGHT_DIAGONAL_FORWARD = 9;
    private final static int ONE_SPACE_RIGHT_DIAGONAL_BACKWARD = -9;

    private final static int[] CANDIDATE_MOVE_COORDINATES = {
            ONE_SPACE_FORWARD, ONE_SPACE_BACKWARD, ONE_SPACE_LEFT, ONE_SPACE_RIGHT, ONE_SPACE_LEFT_DIAGONAL_FORWARD,
            ONE_SPACE_LEFT_DIAGONAL_BACKWARD, ONE_SPACE_RIGHT_DIAGONAL_FORWARD, ONE_SPACE_RIGHT_DIAGONAL_BACKWARD
    };

    public Queen(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateDestinationOffset : Queen.CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if(isFirstColumnExclusion(candidateDestinationCoordinate, currentCandidateDestinationOffset) ||
                        isEighthColumnExclusion(candidateDestinationCoordinate, currentCandidateDestinationOffset)) {
                    break;
                }
                candidateDestinationCoordinate += currentCandidateDestinationOffset;
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new MajorPieceMove(board, this, candidateDestinationCoordinate));
                    } else {
                        // If a Queen is blocked by any piece it cannot consider pieces past it
                        final Piece pieceAtDestination  = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new AttackMove
                                    (board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == ONE_SPACE_LEFT ||
                candidateOffset == ONE_SPACE_RIGHT_DIAGONAL_BACKWARD ||
                candidateOffset == ONE_SPACE_LEFT_DIAGONAL_FORWARD);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == ONE_SPACE_LEFT_DIAGONAL_BACKWARD ||
                candidateOffset == ONE_SPACE_RIGHT || candidateOffset == ONE_SPACE_RIGHT_DIAGONAL_FORWARD);
    }
}
