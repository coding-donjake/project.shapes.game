package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends GameObject {
	
	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		color = Color.red;
		radius = 10;
		width = radius * 2;
		height = radius * 2;
	}

	@Override
	void tick() {
		x++;
		y++;
	}

	@Override
	void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
	}

}
