package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public abstract class Tile {

    protected int tileCoordinate;

    Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    private static Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();



    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    public abstract boolean isTileOccupied();


    public abstract Piece getPiece();
}
