package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy4 extends GameObject {

	public Enemy4(double x, double y, Handler handler) {
		super(x, y, handler);
		
		// required attributes
		id = ID.enemy;
		color = Color.pink;
		
		// positioning and size
		radius = 40;
		width = radius * 2;
		height = radius * 2;
		
		// global increase
		damage = 70;
		
		// kill increase
		experience = 100;
		
		// strength
		health = 500;
		maxHealth = 500;
		
		// agility
		armor = 5;
		movementSpeed = 0.5;
		
	}

	@Override
	public void tick() {
		if (health <= 0) {
			angle = Math.toRadians((Math.random() * 120) + 0);
			handler.objects.add(new Enemy5(x, y, angle, handler));
			angle = Math.toRadians((Math.random() * 120) + 120);
			handler.objects.add(new Enemy5(x, y, angle, handler));
			angle = Math.toRadians((Math.random() * 120) + 240);
			handler.objects.add(new Enemy5(x, y, angle, handler));
			die();
		} else {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					followPoint(object.x, object.y);
					travel();
					if (Game.checkCollision(x, y, radius, object.x, object.y, object.radius)) {
						if (object.reflection <= 0) {
							if (object.alive && object.vulnerable) {
								object.takeDamage(damageType, damage);
								object.takeExperience(2);
								alive = false;
							}
						} else {
							
						}
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
		
		g.setColor(Color.black);
		g.drawOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);

		// health bar
		g.setColor(Color.red);
		g.fillRect((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)) - 10, (int)((health / maxHealth) * width), 5);
		g.setColor(Color.black);
		g.drawRect((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)) - 10, (int)width, 5);
	}

}
