package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Fire extends GameObject {

	public Fire(double x, double y, double angle, double attackSpeed, double attackRange, double damage, Handler handler) {
		super(x, y, angle, attackSpeed, attackRange, damage, handler);
		
		// required attributes
		id = ID.bullet;
		color = Color.orange;
		
		// positioning and size
		radius = 8;
		width = radius * 2;
		height = radius * 2;
		
		// agility
		movementSpeed = 10;
		
		// global attributes
		damageType = "magical";
		
		calculateVelocities(angle);
	}

	@Override
	public void tick() {
		travel();
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.enemy || object.id == ID.player) {
				if (Game.checkCollision(x, y, radius, object.x, object.y, object.radius)) {
					if (object.reflection <= 0) {
						if (object.alive && object.vulnerable) {
							object.takeDamage(damageType, damage);
							alive = false;
						}
					} else {
						calculateVelocities(Game.getBallCollidedAngle(movementSpeed, angle, object.movementSpeed, object.angle, Game.calculateAngle(x, y, object.x, object.y)) + Math.toRadians(180));
						object.angle = Game.getBallCollidedAngle(movementSpeed, angle, object.movementSpeed, object.angle, Game.calculateAngle(x, y, object.x, object.y));
						object.bounceEnergy = damage / 2;
						object.reflection--;
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
	}


}
