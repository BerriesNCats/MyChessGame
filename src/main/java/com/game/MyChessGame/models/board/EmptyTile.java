package com.game.MyChessGame.models.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EmptyTile extends Tile {

    EmptyTile(int tileCoordinate) { super(tileCoordinate); }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

}
