package com.game.MyChessGame.models.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Tile {

    int tileCoordinate;

    public abstract boolean isTileOccupied();


}
