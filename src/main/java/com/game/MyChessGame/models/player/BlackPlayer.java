package com.game.MyChessGame.models.player;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.Tile;
import com.game.MyChessGame.models.board.move.KingSideCastleMove;
import com.game.MyChessGame.models.board.move.Move;
import com.game.MyChessGame.models.board.move.QueenSideCastleMove;
import com.game.MyChessGame.models.pieces.Alliance;
import com.game.MyChessGame.models.pieces.Piece;
import com.game.MyChessGame.models.pieces.Rook;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class BlackPlayer extends Player{

    /**
     * To construct a White Player, send in the game board, a collection of the players legal moves and a
     * collection of the players opponents legal moves.
     *
     * @param board the game board
     * @param blackStandardLegalMoves a collection of moves available to this player
     * @param whiteStandardLegalMoves a collection of moves available to this players opponent
     */
    public BlackPlayer(final Board board, final Collection<Move> blackStandardLegalMoves,
                       final Collection<Move> whiteStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }

    /**
     * Calculates the legal castling moves available to the black player.
     *
     * @param playersLegalMoves an array list of the black players legal moves
     * @param opponentsLegalMoves an array list of the black players opponents legal moves
     * @return the legal castling moves available to the black player
     */
    @Override
    protected Collection<Move> calculateCastlingLegalMoves(final Collection<Move> playersLegalMoves,
                                                           final Collection<Move> opponentsLegalMoves) {
        final List<Move> castlingLegalMoves = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {

            // Creating the move for Black to castle on the king side if it can do so legally
            if (!this.board.getTile(5).isTileOccupied() &&
                    !this.board.getTile(6).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(7);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(5, opponentsLegalMoves).isEmpty() &&
                            Player.calculateAttacksOnTile(6, opponentsLegalMoves).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {

                        castlingLegalMoves.add(new KingSideCastleMove(this.board,
                                                                      this.playerKing,
                                                     6,
                                                                      (Rook)rookTile.getPiece(),
                                                                      rookTile.getTileCoordinate(),
                                                     5));
                    }
                }
            }

            // Creating the move for Black to castle on the queen side if it can do so legally
            if (!this.board.getTile(1).isTileOccupied() &&
                    !this.board.getTile(2).isTileOccupied() &&
                    !this.board.getTile(3).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(0);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {

                    castlingLegalMoves.add(new QueenSideCastleMove(this.board,
                                                                   this.playerKing,
                                                  58,
                                                                   (Rook)rookTile.getPiece(),
                                                                   rookTile.getTileCoordinate(),
                                                  59));
                }
            }
        }
        return ImmutableList.copyOf(castlingLegalMoves);
    }
}
