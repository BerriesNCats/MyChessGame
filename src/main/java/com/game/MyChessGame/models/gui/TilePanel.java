package com.game.MyChessGame.models.gui;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.BoardUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JPanel {


    private int tileCoordinate;

    TilePanel(final BoardPanel boardPanel, final int tileCoordinate) throws IOException {
        super(new GridBagLayout());
        this.tileCoordinate = tileCoordinate;
        setPreferredSize(Table.TILE_PANEL_DIMENSION);
        assignTileColor();
        assignTilePieceIcon(Table.chessBoard);
        validate();
    }

    private void assignTilePieceIcon(final Board board) throws IOException {
        this.removeAll();
        if (board.getTile(this.tileCoordinate).isTileOccupied()) {
            final BufferedImage image = ImageIO.read(new File(Table.defaultPieceImagePath +
                    board.getTile(this.tileCoordinate).getPiece().getPieceAlliance().toString().substring(0, 1) +
                    board.getTile(this.tileCoordinate).getPiece().toString() + ".gif"));
            add(new JLabel(new ImageIcon(image)));
        }
    }

    private void assignTileColor() {
        if (BoardUtils.EIGHTH_RANK[this.tileCoordinate] || BoardUtils.SIXTH_RANK[this.tileCoordinate] ||
                BoardUtils.FOURTH_RANK[this.tileCoordinate] || BoardUtils.SECOND_RANK[this.tileCoordinate]) {
                        setBackground(this.tileCoordinate % 2 == 0 ? Table.lightTileColor : Table.darkTileColor);
        } else if (BoardUtils.SEVENTH_RANK[this.tileCoordinate] || BoardUtils.FIFTH_RANK[this.tileCoordinate] ||
                BoardUtils.THIRD_RANK[this.tileCoordinate] || BoardUtils.FIRST_RANK[this.tileCoordinate]) {
            setBackground(this.tileCoordinate % 2 != 0 ? Table.lightTileColor : Table.darkTileColor);
        }
    }

}
