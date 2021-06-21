package com.game.MyChessGame.models.board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class EmptyTile extends Tile {

    EmptyTile(int tileCoordinate) { super(tileCoordinate); }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

}
