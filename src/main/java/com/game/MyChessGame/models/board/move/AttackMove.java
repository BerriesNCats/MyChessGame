package com.game.MyChessGame.models.board.move;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.pieces.Piece;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttackMove extends Move {

    private Piece attackedPiece;

    /**
     * To construct an Attack Move, send in the current board, the attacking piece being moved, the tile coordinate
     * the attacking piece intends to move to and the piece that resides on the destination coordinate being attacked.
     *
     * @param board the game board
     * @param movedPiece the attacking piece being moved
     * @param destinationCoordinate the coordinate of the tile the attacking piece is moving to
     * @param attackedPiece the piece on the board being attacked
     */
    public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate,
                      final Piece attackedPiece) {
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
