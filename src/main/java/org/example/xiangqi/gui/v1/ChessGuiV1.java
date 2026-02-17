package org.example.xiangqi.gui.v1;

import javax.swing.JFrame;
import java.awt.BorderLayout;


public class ChessGuiV1 extends JFrame {
    public static final String TITLE = "Xiangqi - Chinese Chess";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    private ChessBoardPanel chessBoardPanel;
    private ControlPanel controlPanel;

    public ChessGuiV1() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        setLayout(new BorderLayout());

        chessBoardPanel = new ChessBoardPanel();
        add(chessBoardPanel, BorderLayout.CENTER);

        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.EAST);

    }
}
