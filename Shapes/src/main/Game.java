package main;

import java.awt.Graphics2D;

public class Game {
	
	public Handler handler;
	
	public Game() {
		handler = new Handler();
		handler.objects.add(new Player(50, 50));
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void render(Graphics2D g) {
		handler.render(g);
	}

}
