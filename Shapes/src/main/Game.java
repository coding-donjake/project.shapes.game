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
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void render(Graphics2D g) {
		handler.render(g);
	}
	
	public static double calculateAngle(double x1, double y1, double x2, double y2) {
		double thetaX = x2 - x1;
		double thetaY = y2 - y1;
		return Math.atan2(thetaY, thetaX);
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
	
	public static double getBallCollidedAngle(double v1, double alpha1, double v2, double alpha2, double beta) {
		double numerator = v1 * Math.sin(alpha1 - beta) + v2 * Math.sin(beta - alpha2);
	    double denominator = v1 * Math.cos(alpha1 - beta) - v2 * Math.cos(beta - alpha2);
	    double angle = Math.atan(numerator / denominator);
	    return Math.atan2(v1 * Math.sin(alpha1 - angle) + v2 * Math.sin(alpha2 - angle), v1 * Math.cos(alpha1 - angle) - v2 * Math.cos(alpha2 - angle));
	}

}
