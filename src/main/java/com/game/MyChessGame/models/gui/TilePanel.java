package com.game.MyChessGame.models.gui;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.board.BoardUtils;
import com.game.MyChessGame.models.board.move.Move;
import com.game.MyChessGame.models.board.move.MoveFactory;
import com.game.MyChessGame.models.player.MoveTransition;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class TilePanel extends JPanel {


    private int tileCoordinate;
    private Table table;

    TilePanel(final Table table, final BoardPanel boardPanel, final int tileCoordinate) throws IOException {
        super(new GridBagLayout());
        this.table = table;
        this.tileCoordinate = tileCoordinate;
        setPreferredSize(Table.TILE_PANEL_DIMENSION);
        assignTileColor();
        assignTilePieceIcon(Table.chessBoard);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (isRightMouseButton(mouseEvent)) {
                    table.setOriginTile(null);
                    table.setDestinationTile(null);
                    table.setMovedPiece(null);
                } else if (isLeftMouseButton(mouseEvent)) {
                    if (table.getOriginTile() == null) {
                        table.setOriginTile(Table.chessBoard.getTile(tileCoordinate));
                        table.setMovedPiece(table.getOriginTile().getPiece());
                        if (table.getMovedPiece() == null) {
                            table.setOriginTile(null);
                        }
                    } else {
                        table.setDestinationTile(Table.chessBoard.getTile(tileCoordinate));
                        final Move move = MoveFactory.createMove(Table.chessBoard,
                                table.getOriginTile().getTileCoordinate(),
                                table.getDestinationTile().getTileCoordinate());
                        final MoveTransition moveTransition = Table.chessBoard.getCurrentPlayer().makeMove(move);
                        if (moveTransition.getMoveStatus().isDone()) {
                            Table.chessBoard = moveTransition.getTransitionBoard();
                        }
                        table.setOriginTile(null);
                        table.setDestinationTile(null);
                        table.setMovedPiece(null);
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @SneakyThrows
                        @Override
                        public void run() {
                            boardPanel.drawBoard(Table.chessBoard);
                        }
                    });
                }
            }

            @Override
            public void mousePressed(final MouseEvent mouseEvent) {
                // think about how the logic for this would need to work and look into if there is a built in mouseDragged
                // method and how the board is being rendered
//                table.setOriginTile(Table.chessBoard.getTile(tileCoordinate));
//                table.setMovedPiece(Table.chessBoard.getTile(tileCoordinate).getPiece());
            }

            @Override
            public void mouseReleased(final MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(final MouseEvent mouseEvent) {

            }
        });

        validate();
    }

    private void assignTilePieceIcon(final Board board) throws IOException {
        this.removeAll();
        if (board.getTile(this.tileCoordinate).isTileOccupied()) {
            final BufferedImage image = ImageIO.read(new File(Table.defaultPieceImagePath +
                    board.getTile(this.tileCoordinate).getPiece().getPieceAlliance().toString().charAt(0) +
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

    public void drawTile(final Board chessBoard) throws IOException {
        assignTileColor();
        assignTilePieceIcon(chessBoard);
        validate();
        repaint();
    }
}
