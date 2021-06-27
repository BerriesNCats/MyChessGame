package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.BoardBuilder;
import com.game.MyChessGame.models.pieces.Piece;
import com.game.MyChessGame.models.pieces.Rook;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class CastleMove extends Move {

    protected Rook castleRook;
    protected int castleRookOrigin;
    protected int castleRookDestination;

    public CastleMove(final Board board, final Piece movedPiece, final int destinationCoordinate,
                      final Rook castleRook, final int castleRookOrigin, final int castleRookDestination) {
        super(board, movedPiece, destinationCoordinate);
        this.castleRook = castleRook;
        this.castleRookOrigin = castleRookOrigin;
        this.castleRookDestination = castleRookDestination;
    }

    @Override
    public boolean isCastlingMove() {
        return true;
    }

    @Override
    public Board execute() {

        final BoardBuilder boardBuilder = new BoardBuilder();
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                boardBuilder.setPiece(piece);
            }
        }
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            boardBuilder.setPiece(piece);
        }
        boardBuilder.setPiece(this.movedPiece.movePiece(this));
        boardBuilder.setPiece(new Rook(this.castleRook.getPieceAlliance(), this.castleRookDestination));
        boardBuilder.setNextMoveAlliance(this.board.getCurrentPlayer().getOpponent().getAlliance());
        return boardBuilder.build();

    }
}
