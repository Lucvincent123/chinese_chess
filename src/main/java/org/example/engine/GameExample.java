package org.example.engine;

import java.awt.Color;

public class GameExample extends GameContainer {
    private final GameRectangle rectangle;

    public GameExample(GameWindow window, int targetFPS, int targetUPS) {
        super(window, targetFPS, targetUPS);
        rectangle = new GameRectangle(100, 100, 50, 50, Color.BLUE);
        window.getCanvas().addEntity(rectangle);
        KeyInput keyInput = new KeyInput(this);
        window.addKeyListener(keyInput);
    }

    @Override
    protected void update() {
//        System.out.println("Updating...");
    }

    @Override
    protected void render() {
//        System.out.println("Rendering...");
        window.render();
//        GameCanvas canvas = window.getCanvas();
//        canvas.getGraphics().setColor(rectangle.getColor());
//        canvas.getGraphics().fillRect((int) rectangle.getX(), (int) rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public void moveRectangle(int dx, int dy) {
        rectangle.move(dx, dy);
    }

    public static void main(String[] args) {
        GameWindow window = new GameWindow(800, 600, 100);
        window.setVisible(true);
        GameExample gameContainer = new GameExample(window, 60, 120);
        KeyInput keyInput = new KeyInput(gameContainer);
        window.addKeyListener(keyInput);
        gameContainer.start();
    }
}
