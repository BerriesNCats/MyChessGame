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
public class WhitePlayer extends Player{

    /**
     * To construct a White Player, send in the game board, a collection of the players legal moves and a
     *  collection of the players opponents legal moves.
     *
     * @param board the game board
     * @param whiteStandardLegalMoves a collection of moves available to this player
     * @param blackStandardLegalMoves a collection of moves available to this players opponent
     */
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

    /**
     * Calculates the legal castling moves available to the white player.
     *
     * @param playersLegalMoves an array list of the white players legal moves
     * @param opponentsLegalMoves an array list of the white players opponents legal moves
     * @return the legal castling moves available to the white player
     */
    @Override
    protected Collection<Move> calculateCastlingLegalMoves(final Collection<Move> playersLegalMoves,
                                                           final Collection<Move> opponentsLegalMoves) {
        final List<Move> castlingLegalMoves = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {

            // Creating the move for White to castle on the king side if it can do so legally
            if (!this.board.getTile(61).isTileOccupied() &&
                    !this.board.getTile(62).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(63);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(61, opponentsLegalMoves).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentsLegalMoves).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {

                        castlingLegalMoves.add(new KingSideCastleMove(this.board,
                                                                      this.playerKing,
                                                     62,
                                                                      (Rook)rookTile.getPiece(),
                                                                      rookTile.getTileCoordinate(),
                                                     61));
                    }
                }
            }

            // Creating the move for White to castle on the queen side if it can do so legally
            if (!this.board.getTile(59).isTileOccupied() &&
                    !this.board.getTile(58).isTileOccupied() &&
                    !this.board.getTile(57).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() &&
                        Player.calculateAttacksOnTile(58, opponentsLegalMoves).isEmpty() &&
                        Player.calculateAttacksOnTile(59, opponentsLegalMoves).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()) {

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
