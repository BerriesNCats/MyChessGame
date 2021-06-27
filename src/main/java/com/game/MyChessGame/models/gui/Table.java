package com.game.MyChessGame.models.gui;

import com.game.MyChessGame.models.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Table {

    private JFrame gameFrame;
    private BoardPanel boardPanel;
    protected static Board chessBoard;
    protected static String defaultPieceImagePath = "art/holywarriors/";

    private static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    protected static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    protected static final Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);

    protected static Color lightTileColor = Color.decode("#FFFACD");
    protected static Color darkTileColor = Color.decode("#593E1A");



    public Table() throws IOException {
        this.gameFrame = new JFrame("ChessGame");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.chessBoard = Board.createStandardBoard();
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open up pgn file here");
            }
        });
        fileMenu.add(openPGN);

        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }
}
