package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttackMove extends Move {

    private Piece attackedPiece;

    public AttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }

    @Override
    public Board execute() {
        return null;
    }

    @Override
    public boolean isAttack() {
        return true;
    }

    @Override
    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }

    @Override
    public int hashCode() {
        return this.attackedPiece.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof AttackMove)) return false;
        AttackMove otherAttackMove = (AttackMove) other;
        return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
    }
}
