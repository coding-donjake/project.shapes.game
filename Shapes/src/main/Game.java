package main;

import java.awt.Graphics2D;

public class Game {
	
	public Handler handler;
	public Main main;
	
	public Game(Main main) {
		this.main = main;
		handler = new Handler();
		main.addKeyListener(new KeyInput(handler));
		main.addMouseListener(new MouseInput(handler));
		main.addMouseMotionListener(new MouseInput(handler));
		handler.objects.add(new Arena(0, 0, handler));
		handler.objects.add(new Player(0, 0, handler));
		handler.objects.add(new Enemy1(100, 100, 10, handler));
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void render(Graphics2D g) {
		handler.render(g);
	}
	
	public static boolean checkCollision(double x1, double y1, double radius1, double x2, double y2, double radius2) {
		double diffX = x2 - x1;
		double diffY = y2 - y1;
		double distance = Math.sqrt((diffX * diffX) + (diffY * diffY));
		if (distance < (radius1 + radius2))
			return true;
		else
			return false;
	}

}
