package com.game.MyChessGame.models.gui;

import com.game.MyChessGame.models.board.BoardUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {

    List<TilePanel> boardTiles;

    BoardPanel() throws IOException {
        super(new GridLayout(8,8));
        this.boardTiles = new ArrayList<>();
        for (int i = 0; i < BoardUtils.NUMBER_OF_TILES; i++) {
            final TilePanel tilePanel = new TilePanel(this, i);
            this.boardTiles.add(tilePanel);
            add(tilePanel);
        }
        setPreferredSize(Table.BOARD_PANEL_DIMENSION);
        validate();
    }
}
