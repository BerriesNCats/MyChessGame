package com.game.MyChessGame.models.pieces;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.BoardUtils;
import com.game.MyChessGame.models.board.MajorPieceMove;
import com.game.MyChessGame.models.board.Move;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class Pawn extends Piece{

    private final static int ONE_SPACE_FORWARD = 8;
    private final static int TWO_SPACES_FORWARD = 16;
    private final static int ATTACK_LEFT = 7;
    private final static int ATTACK_RIGHT = 9;

    private final static int[] CANDIDATE_MOVE_COORDINATES = {
            ONE_SPACE_FORWARD, TWO_SPACES_FORWARD, ATTACK_LEFT, ATTACK_RIGHT
    };

    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(pieceAlliance, piecePosition);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateDestinationOffset : Pawn.CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition +
                    (this.pieceAlliance.getDirection() * currentCandidateDestinationOffset);
            if(!BoardUtils.isValidTileCoordinate(currentCandidateDestinationOffset)) {
                continue;
            }
            if(currentCandidateDestinationOffset == ONE_SPACE_FORWARD &&
                    board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                // TODO FIX
                legalMoves.add(new MajorPieceMove(board, this, candidateDestinationCoordinate));
            } else if(currentCandidateDestinationOffset == TWO_SPACES_FORWARD && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition +
                        (this.pieceAlliance.getDirection() * ONE_SPACE_FORWARD);
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new MajorPieceMove(board, this, candidateDestinationCoordinate));
                } else if(currentCandidateDestinationOffset == ATTACK_LEFT &&
                        !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                        (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {
                    if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                        final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                            // TODO
                            legalMoves.add(new MajorPieceMove(board, this, candidateDestinationCoordinate));
                        }
                    }
                } else if(currentCandidateDestinationOffset == ATTACK_RIGHT &&
                        !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                        (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {
                    if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                        final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                            // TODO
                            legalMoves.add(new MajorPieceMove(board, this, candidateDestinationCoordinate));
                        }
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

}
