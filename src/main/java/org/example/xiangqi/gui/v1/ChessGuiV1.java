package org.example.xiangqi.gui.v1;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class ChessGuiV1 extends JFrame {
    public static final String TITLE = "Xiangqi - Chinese Chess";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private ChessPanel chessPanel;
    private ControlPanel controlPanel;

    public ChessGuiV1() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        setResizable(false);


        setLayout(new BorderLayout());



        chessPanel = new ChessPanel(new ChessGameGuiV1(new BoardGuiV1(), new CapturedLineGuiV1(true), new CapturedLineGuiV1(false)));
        add(chessPanel, BorderLayout.CENTER);

        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.EAST);
        setVisible(true);
    }
}
