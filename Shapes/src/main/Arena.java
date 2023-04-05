package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Arena extends GameObject {
	
	public Arena(double x, double y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		id = ID.arena;
		color = Color.black;
		radius = 300;
		width = radius * 2;
		height = radius * 2;
	}

	@Override
	void tick() {
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				if (!Game.checkCollision(x, y, radius, object.x, object.y, object.radius)) {
					if (object.health > 0) {
						object.health -= 0.1;
					}
				}
				break;
			}
		}
	}

	@Override
	void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
	}

}
