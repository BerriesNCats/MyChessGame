package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@NoArgsConstructor
class OccupiedTile extends Tile {

    private Piece pieceOnTile;

    /**
     * To construct an Occupied Tile, send in the coordinate at which the occupied tile is located and the piece that
     * resides on that tile.
     *
     * @param tileCoordinate the coordinate of the occupied tile on the board
     * @param pieceOnTile the piece on the occupied tile
     */
    public OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.pieceOnTile;
    }

    @Override
    public String toString() {
        // White Pieces are Upper Case // Black Pieces are lower case
        return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase(Locale.ROOT) :
                getPiece().toString();
    }

}
