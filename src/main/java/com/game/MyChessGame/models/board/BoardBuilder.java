package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Alliance;
import com.game.MyChessGame.models.pieces.Pawn;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class BoardBuilder extends Board {

    /** The board itself consists of a configuration of pieces mapped by their location. */
    Map<Integer, Piece> boardConfiguration;

    Alliance nextMoveAlliance;
    Pawn enPassantPawn;

    /**
     * To construct the Board create a new board builder and place the pieces on the board using the setPiece method.
     */
    public BoardBuilder() {
        this.boardConfiguration = new HashMap<>();
    }

    /**
     * Places a piece onto the board.
     *
     * @param piece the piece being placed onto the board
     * @return the board with the new piece now placed into the configuration
     */
    public BoardBuilder setPiece(final Piece piece) {
        this.boardConfiguration.put(piece.getPiecePosition(), piece);
        return this;
    }

    /**
     * Sets the alliance associated with the player whose turn it is to move.
     *
     * @param alliance the alliance of the player
     * @return the board with the player whose turn it is to moves alliance now set
     */
    public BoardBuilder setNextMoveAlliance(final Alliance alliance) {
        this.nextMoveAlliance = alliance;
        return this;
    }

    /**
     * Builds the board after it has been constructed.
     *
     * @return the built board
     */
    public Board build() {
        return new Board(this);
    }

}
