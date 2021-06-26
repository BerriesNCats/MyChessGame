package com.game.MyChessGame.models.player;

public enum MoveStatus {
    DONE {
        @Override
        boolean isDone() {
            return true;
        }
    };

    abstract boolean isDone();
}
