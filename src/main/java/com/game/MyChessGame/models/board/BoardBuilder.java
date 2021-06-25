package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Alliance;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
public class BoardBuilder extends Board {

    Map<Integer, Piece> boardConfiguration;
    Alliance nextMoveAlliance;

    public BoardBuilder() {
        this.boardConfiguration = new HashMap<>();
    }

    public BoardBuilder setPiece(final Piece piece) {
        this.boardConfiguration.put(piece.getPiecePosition(), piece);
        return this;
    }

    public BoardBuilder setNextMoveAlliance(final Alliance alliance) {
        this.nextMoveAlliance = nextMoveAlliance;
        return this;
    }

    public Board build() {
        return new Board(this);
    }
}
