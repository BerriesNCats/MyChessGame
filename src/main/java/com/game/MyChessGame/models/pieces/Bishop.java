package com.game.MyChessGame.models.pieces;

import com.game.MyChessGame.models.board.*;
import com.game.MyChessGame.models.board.move.AttackMove;
import com.game.MyChessGame.models.board.move.MajorPieceMove;
import com.game.MyChessGame.models.board.move.Move;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class Bishop extends Piece{

    private final static int ONE_SPACE_RIGHT_DIAGONAL_UP = -7;
    private final static int ONE_SPACE_LEFT_DIAGONAL_UP = -9;
    private final static int ONE_SPACE_LEFT_DIAGONAL_DOWN = 7;
    private final static int ONE_SPACE_RIGHT_DIAGONAL_DOWN = 9;

    private final static int[] CANDIDATE_MOVE_COORDINATES = {
            ONE_SPACE_LEFT_DIAGONAL_DOWN, ONE_SPACE_RIGHT_DIAGONAL_UP,
            ONE_SPACE_RIGHT_DIAGONAL_DOWN, ONE_SPACE_LEFT_DIAGONAL_UP
    };

    public Bishop(final Alliance pieceAlliance, final int piecePosition) {
        super(pieceAlliance, piecePosition, PieceType.BISHOP);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateDestinationOffset : Bishop.CANDIDATE_MOVE_COORDINATES) {
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
                    } else {  // If a Bishop is blocked by any piece it cannot consider pieces past it
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

    @Override
    public Bishop movePiece(Move move) {
        return new Bishop(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    // If a Bishop is on the 1st column it cannot move left
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == ONE_SPACE_LEFT_DIAGONAL_UP ||
                candidateOffset == ONE_SPACE_LEFT_DIAGONAL_DOWN);
    }

    // If a Bishop is on the 8th column it cannot move right
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == ONE_SPACE_RIGHT_DIAGONAL_UP ||
                candidateOffset == ONE_SPACE_RIGHT_DIAGONAL_DOWN);
    }


    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }

}
