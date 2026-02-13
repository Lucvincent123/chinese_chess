package org.example.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    private final GameExample gameContainer; // Reference to the game container for accessing game state
    private boolean[] keys = new boolean[256]; // Track key states

    public KeyInput(GameExample gameContainer) {
        this.gameContainer = gameContainer;
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) return false;
        return keys[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used for character keys like W, A, S, D
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.gameContainer.moveRectangle(0, -5); // Move up
                break;
            case KeyEvent.VK_S:
                this.gameContainer.moveRectangle(0, 5); // Move down
                break;
            case KeyEvent.VK_A:
                this.gameContainer.moveRectangle(-5, 0); // Move left
                break;
            case KeyEvent.VK_D:
                this.gameContainer.moveRectangle(5, 0); // Move right
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Key Released: " + e.getKeyChar());
    }
}
