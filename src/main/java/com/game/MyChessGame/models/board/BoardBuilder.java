package com.game.MyChessGame.models.board;

import com.game.MyChessGame.models.pieces.Alliance;
import com.game.MyChessGame.models.pieces.Pawn;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
public class BoardBuilder extends Board {

    Map<Integer, Piece> boardConfiguration;
    Alliance nextMoveAlliance;
    Pawn enPassantPawn;

    public BoardBuilder() {
        this.boardConfiguration = new HashMap<>();
    }

    public BoardBuilder setPiece(final Piece piece) {
        this.boardConfiguration.put(piece.getPiecePosition(), piece);
        return this;
    }

    public BoardBuilder setNextMoveAlliance(final Alliance alliance) {
        this.nextMoveAlliance = alliance;
        return this;
    }

    public Board build() {
        return new Board(this);
    }

}
