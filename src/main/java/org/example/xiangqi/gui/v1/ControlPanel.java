package org.example.xiangqi.gui.v1;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton newGameButton;
    private JButton undoButton;
    private JButton redoButton;
    public static final int PANEL_WIDTH = 200;


    public ControlPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, 0));
        setLayout(new FlowLayout());

        newGameButton = new JButton("New Game");
        undoButton = new JButton("Undo");
        redoButton = new JButton("Redo");

        add(newGameButton);
        add(undoButton);
        add(redoButton);

        // Add action listeners for buttons
        newGameButton.addActionListener(e -> startNewGame());
        undoButton.addActionListener(e -> undoMove());
        redoButton.addActionListener(e -> redoMove());
    }

    private void startNewGame() {
        // Logic to start a new game
    }

    private void undoMove() {
        // Logic to undo the last move
    }

    private void redoMove() {
        // Logic to redo the last undone move
    }
}
