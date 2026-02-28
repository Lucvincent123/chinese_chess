package org.example.xiangqi.gui.v1;

import org.example.xiangqi.core.CapturedLine;

import java.awt.Graphics;

public class CapturedLineGuiV1 extends CapturedLine {
    public CapturedLineGuiV1(boolean isRed) {
        super(isRed);
    }

    public void paint(Graphics g) {
//        System.out.println("Painting captured line for " + (isRed ? "Red" : "Black"));
    }
}
