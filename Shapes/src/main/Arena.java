package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Arena extends GameObject {
	
	private int timer = 0;
	
	public Arena(double x, double y, Handler handler) {
		super(x, y, handler);
		
		// required attributes
		id = ID.arena;
		color = Color.gray;
		
		// positioning and size
		radius = 500;
		width = radius * 2;
		height = radius * 2;
		
		// health and damage
		damage = 0.005;
	}
	
	@Override
	public void tick() {
		timer++;
		
		if (timer % 50 == 0) {
			angle = 6.28319 * Math.random();
			double x = ((radius + 300) * Math.cos(angle)) * -1;
			double y = ((radius + 300) * Math.sin(angle)) * -1;
			handler.objects.add(new Enemy6(x, y, handler));
		}
		
		if (timer > 2000 && timer % 200 == 0) {
			angle = 6.28319 * Math.random();
			double x = ((radius + 300) * Math.cos(angle)) * -1;
			double y = ((radius + 300) * Math.sin(angle)) * -1;
			handler.objects.add(new Enemy2(x, y, handler));
		}
		
		if (timer > 8000 && timer % 1000 == 0) {
			angle = 6.28319 * Math.random();
			double x = ((radius + 300) * Math.cos(angle)) * -1;
			double y = ((radius + 300) * Math.sin(angle)) * -1;
			handler.objects.add(new Enemy3(x, y, handler));
		}
		
		if (timer > 14000 && timer % 1000 == 0) {
			angle = 6.28319 * Math.random();
			double x = ((radius + 300) * Math.cos(angle)) * -1;
			double y = ((radius + 300) * Math.sin(angle)) * -1;
			handler.objects.add(new Enemy4(x, y, handler));
		}
		
		if (timer > 20000 && timer % 1000 == 0) {
			angle = 6.28319 * Math.random();
			double x = ((radius + 300) * Math.cos(angle)) * -1;
			double y = ((radius + 300) * Math.sin(angle)) * -1;
			handler.objects.add(new Enemy6(x, y, handler));
		}
		
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				if (!Game.checkCollision(x, y, radius, object.x, object.y, object.radius * -1)) {
					if (object.health > 0 && object.vulnerable) {
						object.takeDamage("pure", object.maxHealth * damage);
					}
				}
				break;
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
		g.setColor(Color.black);
		g.drawOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
	}

}
