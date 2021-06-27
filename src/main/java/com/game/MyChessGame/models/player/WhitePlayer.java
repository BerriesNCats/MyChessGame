package com.game.MyChessGame.models.player;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.Tile;
import com.game.MyChessGame.models.board.move.Move;
import com.game.MyChessGame.models.pieces.Alliance;
import com.game.MyChessGame.models.pieces.Piece;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class WhitePlayer extends Player{

    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

    @Override
    protected Collection<Move> calculateCastlingLegalMoves(Collection<Move> playersLegalCastlingMoves,
                                                           Collection<Move> opponentsLegalCastlingMoves) {
        final List<Move> castlingLegalMoves = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {

            // White King Side Castle
            if (!this.board.getTile(61).isTileOccupied() &&
                    !this.board.getTile(62).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(63);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(61, opponentsLegalCastlingMoves).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentsLegalCastlingMoves).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        // TODO
                        castlingLegalMoves.add(null);
                    }

                }
            }

            // White Queen Side Castle
            if (!this.board.getTile(59).isTileOccupied() &&
                    !this.board.getTile(58).isTileOccupied() &&
                    !this.board.getTile(57).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    // TODO
                    castlingLegalMoves.add(null);
                }

            }
        }
        return ImmutableList.copyOf(castlingLegalMoves);
    }
}
