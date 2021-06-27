package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.BoardBuilder;
import com.game.MyChessGame.models.pieces.Pawn;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PawnJumpMove extends Move {

    public PawnJumpMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();
        for (Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                boardBuilder.setPiece(piece);
            }
        }
        for (Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            boardBuilder.setPiece(piece);
        }
        Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
        boardBuilder.setPiece(movedPawn);
        boardBuilder.setEnPassantPawn(movedPawn);
        boardBuilder.setNextMoveAlliance(this.board.getCurrentPlayer().getOpponent().getAlliance());
        return boardBuilder.build();

    }

}
