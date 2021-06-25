package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@NoArgsConstructor
class OccupiedTile extends Tile {

    private Piece pieceOnTile;

    OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public String toString() {
        // White Pieces are Upper Case // Black Pieces are lower case
        return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase(Locale.ROOT) :
                getPiece().toString();
    }

}
