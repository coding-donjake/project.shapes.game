package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 2827408947974430996L;
	
	private Thread thread;
	
	private Game game;
	
	// for game loop
    private boolean running;
    private int ticksPerSecond, framesPerSecond;
    private double timePerTick, timePerRender;
    
    private int tps, fps;
    
    public static final int WIDTH = 1136, HEIGHT = 640;
	
	public Main(int ticksPerSecond, int framesPerSecond) {
		this.ticksPerSecond = ticksPerSecond;
		this.framesPerSecond = framesPerSecond;
        timePerTick = 1000000000 / this.ticksPerSecond;
        timePerRender = 1000000000 / this.framesPerSecond;
        game = new Game(this);
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
		double deltaTick = 0, deltaRender = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0, renders = 0;
		while (running) {
            now = System.nanoTime();
            deltaTick += (now - lastTime) / timePerTick;
            deltaRender += (now - lastTime) / timePerRender;
            timer += now - lastTime;
            lastTime = now;
            if (deltaTick >= 1) {
                tick();
                ticks++;
                deltaTick--;
            }
            if (deltaRender >= 1) {
                render();
                renders++;
                deltaRender--;
            }
            if (timer >= 1000000000) {
            	tps = ticks;
            	fps = renders;
            	ticks = 0;
            	renders = 0;
            	timer = 0;
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
    	g.setColor(Color.black);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	// render here
    	game.render(g);
    	
    	g.setColor(Color.gray);
    	g.fillRect(WIDTH - 130, HEIGHT - 30, 120, 20);
    	g.setColor(Color.black);
    	g.drawString("TPS: " + tps + " FPS: " + fps, WIDTH - 125, HEIGHT - 15);
    	
    	g.dispose();
    	bs.show();
    }
    
    public static void main(String[] args) {
		new Main(100, 120);
	}

}
