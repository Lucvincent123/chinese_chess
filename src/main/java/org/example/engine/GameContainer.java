package org.example.engine;

public class GameContainer implements Runnable {
    private boolean running = false;
    private Thread gameThread;
    private final GameWindow window;
    private final int TARGET_FPS;
    private final int TARGET_UPS;
    private int currentFPS = 0;
    private int currentUPS = 0;


    public GameContainer(GameWindow window, int targetFPS, int targetUPS) {
        this.window = window;
        this.TARGET_FPS = targetFPS;
        this.TARGET_UPS = targetUPS;
    }


    public void start() {
        if (running) return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stop() {
        if (!running) return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / TARGET_FPS;
        double timePerUpdate = 1_000_000_000.0 / TARGET_UPS;

        long prevTime = System.nanoTime();

        int frameCount = 0;
        int updateCount = 0;

        long lastTimeCheck = System.currentTimeMillis();

        float deltaF = 0;
        float deltaU = 0;

        while (running) {

            long currentTime = System.nanoTime();

            deltaF += (float) ((currentTime - prevTime) / timePerFrame);
            deltaU += (float) ((currentTime - prevTime) / timePerUpdate);
            prevTime = currentTime;

            if (deltaU >= 1) {
                // Here you would update your game logic, e.g. move objects, check collisions, etc.
                deltaU--;
                updateCount++;
            }

            if (deltaF >= 1) {
                // Render
                window.render();
                deltaF--;
                frameCount++;
            }


            if (System.currentTimeMillis() - lastTimeCheck >= 1_000) {
                currentFPS = frameCount;
                currentUPS = updateCount;
                frameCount = 0;
                updateCount = 0;
                lastTimeCheck = System.currentTimeMillis();
                System.out.println("FPS: " + currentFPS + " | UPS: " + currentUPS);
            }
    }
}

    public int getCurrentFPS() {
        return currentFPS;
    }

    public int getCurrentUPS() {

        return currentUPS;
    }

    public static void main(String[] args) {
        GameWindow window = new GameWindow(800, 600);
        window.setVisible(true);
        GameContainer gameContainer = new GameContainer(window, 120, 200);
        gameContainer.start();

    }
}
