package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class EmptyTile extends Tile {

    /**
     * To construct an Empty Tile, send in the coordinate at which the empty tile is located.
     *
     * @param tileCoordinate the coordinate of the empty tile on the board
     */
    public EmptyTile(final int tileCoordinate) { super(tileCoordinate); }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public String toString() {
        return "-";
    }

}
