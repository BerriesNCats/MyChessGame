package com.game.MyChessGame.models.pieces;

import com.game.MyChessGame.models.Alliance;
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

    private final static int[] CANDIDATE_MOVE_COORDINATES = {8};

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateDestinationOffset : Pawn.CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition +
                    (this.getPieceAlliance().getDirection() * currentCandidateDestinationOffset);
            if(!BoardUtils.isValidTileCoordinate(currentCandidateDestinationOffset)) {
                continue;
            }
            if(currentCandidateDestinationOffset == 8 && board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                // TODO FIX
                legalMoves.add(new MajorPieceMove(board, this, candidateDestinationCoordinate));
            }

        }

        return ImmutableList.copyOf(legalMoves);
    }
}
