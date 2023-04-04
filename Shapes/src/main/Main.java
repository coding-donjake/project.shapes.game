package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 2827408947974430996L;
	
	private Game game;
	
	// for game loop
	private Thread thread;
    private boolean running;
    private int fps;
    private int tps;
    private double updateInterval;
    private double fpsInterval;
    private long lastFpsTime;
    private int fpsCount;
    private double delta;
    private double nsPerTick;
    private long lastTime;
    private int ticks;
    
    public final int WIDTH = 800, HEIGHT = 500;

	public static void main(String[] args) {
		new Main(60, 60);
	}
	
	public Main(int fps, int tps) {
		this.fps = fps;
        this.tps = tps;
        this.updateInterval = 1000000000.0 / tps;
        this.fpsInterval = 1000000000.0 / fps;
        this.lastFpsTime = 0;
        this.fpsCount = 0;
        this.delta = 0;
        this.nsPerTick = 1000000000.0 / tps;
        this.lastTime = System.nanoTime();
        this.ticks = 0;
        game = new Game();
        new Window(this);
	}
	
	public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void run() {
		while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta -= 1;
                ticks++;
            }
            render();
            fpsCount++;
            if (System.nanoTime() - lastFpsTime > fpsInterval) {
                System.out.println("FPS: " + fpsCount);
                fpsCount = 0;
                lastFpsTime = System.nanoTime();
            }
        }
        stop();
	}
	
	public void tick() {
		game.tick();
    }

    public void render() {
    	BufferStrategy bs = this.getBufferStrategy();
    	if (bs == null) {
    		this.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics2D g = (Graphics2D) bs.getDrawGraphics();
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    	g.setColor(Color.green);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	// render here
    	game.render(g);
    	
    	g.dispose();
    	bs.show();
    }

}
