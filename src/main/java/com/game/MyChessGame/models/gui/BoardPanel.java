package com.game.MyChessGame.models.gui;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.BoardUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {

    List<TilePanel> boardTiles;
    private Table table;

    BoardPanel(final Table table) throws IOException {
        super(new GridLayout(8,8));
        this.table = table;
        this.boardTiles = new ArrayList<>();
        for (int i = 0; i < BoardUtils.NUMBER_OF_TILES; i++) {
            final TilePanel tilePanel = new TilePanel(table,this, i);
            this.boardTiles.add(tilePanel);
            add(tilePanel);
        }
        setPreferredSize(Table.BOARD_PANEL_DIMENSION);
        validate();
    }

    public void drawBoard(final Board chessBoard) throws IOException {
        removeAll();
        for (final TilePanel tilePanel : boardTiles) {
            tilePanel.drawTile(chessBoard);
            add(tilePanel);
        }
        validate();
        repaint();
    }
}
