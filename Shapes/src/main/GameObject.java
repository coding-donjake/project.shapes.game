package main;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class GameObject {
	
	// final values
	public final int limiter = 25;
	public final int levelMultiplier = 10, maxLevel = 30;
	
	// required attributes
	public ID id;
	public Color color;
	public Handler handler;
	
	// positioning and size
	public double x, y, mx, my, angle;
	public double radius, width, height;
	
	// global increase
	public int level, points;
	public double damage;
	
	// kill increase
	public int experience;
	
	// strength
	public int strength;
	public double health, maxHealth;
	public double regen;
	public double magicResistance;
	
	// agility
	public int agility;
	public double armor;
	public double attackSpeed, attackRange;
	public double movementSpeed;
	
	// intelligence
	public int intelligence;
	public double mana, maxMana;
	public double magicDamage;
	public double cooldownReduction;
	
	// abilities
	public boolean ability1, ability2, ability3;
	public int cast1, cast2, cast3;
	public int cd1, cd2, cd3;
	public int reflection;
	
	// global attributes
	public boolean alive = true, vulnerable = true;
	public boolean moveUp, moveDown, moveLeft, moveRight;
	public boolean shooting;
	public int channeling, slowed, poisoned, stunned;
	public double velocityX, velocityY, travelledDistance;
	public double attackCD, bounceEnergy;
	public String damageType = "physical";
	
	// all objects
	public GameObject(double x, double y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
	}
	
	// for objects that requires angle
	public GameObject(double x, double y, double angle, Handler handler) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.handler = handler;
	}
	
	// for bullets
	public GameObject(double x, double y, double angle, double attackSpeed, double attackRange, double damage, Handler handler) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.attackSpeed = attackSpeed;
		this.attackRange = attackRange;
		this.damage = damage;
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
	
	public void bounce(double angle) {
		velocityX = (bounceEnergy * Math.cos(angle));
		velocityY = (bounceEnergy * Math.sin(angle));
		if (bounceEnergy > 0.5) {
			bounceEnergy -= bounceEnergy * 0.06;
		} else {
			bounceEnergy = 0;
			vulnerable = true;
		}
	}
	
	public void calculateVelocities(double angle) {
		velocityX = (movementSpeed * Math.cos(angle));
		velocityY = (movementSpeed * Math.sin(angle));
	}
	
	public void cast1() {
		if (cast1 > 0) {
			cast1--;
		}
	}
	
	public void cooldown() {
		if (cd1 < 4000) {
			cd1 += 1 + cooldownReduction;
		}
		if (cd2 < 8000) {
			cd2 += 1 + cooldownReduction;
		}
		if (cd3 < 12000) {
			cd3 += 1 + cooldownReduction;
		}
	}
	
	public void die() {
		alive = false;
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (id == ID.enemy && object.id == ID.player) {
				object.takeExperience(experience);
				object.takeMana(experience);
			}
		}
	}
	
	public void followPoint(double x, double y) {
		double thetaX = x - this.x;
		double thetaY = y - this.y;
		angle = Math.atan2(thetaY, thetaX);
		calculateVelocities(angle);
	}
	
	public void heal(double heal) {
		health += heal;
		if (health > maxHealth) {
			health = maxHealth;
		}
	}
	
	public void increaseStrength() {
		if (points > 0) {
			points--;
			damage++;
			strength++;
			health += 10;
			maxHealth += 10;
			regen += 0.008;
			magicResistance++;
		}
	}
	
	public void increaseAgility() {
		if (points > 0) {
			points--;
			damage++;
			agility++;
			armor++;
			attackSpeed += 2;
			attackRange += 30;
			movementSpeed += 0.05;
		}
	}
	
	public void increaseIntelligence() {
		if (points > 0) {
			points--;
			damage++;
			intelligence++;
			mana += 5;
			maxMana += 5;
			magicDamage++;
			cooldownReduction++;
		}
	}
	
	public void levelUp() {
		if (experience >= ((level + 1) * levelMultiplier) + (((level + 1) * levelMultiplier) * (level * 1.5)) && level < maxLevel) {
			experience -= ((level + 1) * levelMultiplier) + (((level + 1) * levelMultiplier) * (level * 1.5));
			level++;
			points++;
		}
		if (level >= 5) {
			ability1 = true;
		}
	}
	
	public void regen() {
		health += regen;
		if (health > maxHealth) {
			health = maxHealth;
		}
	}
	
	public void reload() {
		if (attackCD <= 0) {
			if (shooting) {
				shoot();
				attackCD = 100;
			}
		} else {
			attackCD -= attackSpeed;
		}
	}
	
	public void shoot() {
		double thetaX = (Main.WIDTH / 2) - mx;
		double thetaY = (Main.HEIGHT / 2) - my;
		angle = Math.atan2(thetaY, thetaX);
		double x = ((radius + 15) * Math.cos(angle)) * -1;
		double y = ((radius + 15) * Math.sin(angle)) * -1;
		if (cast1 <= 0) {
			handler.objects.add(new Bullet(this.x + x, this.y + y, Game.calculateAngle(Main.WIDTH / 2, Main.HEIGHT / 2, mx, my), attackSpeed, attackRange, damage, handler));
		} else {
			handler.objects.add(new Fire(this.x + x, this.y + y, Game.calculateAngle(Main.WIDTH / 2, Main.HEIGHT / 2, mx, my), attackSpeed, attackRange, damage + magicDamage, handler));
			x = ((radius + 15) * Math.cos(angle + Math.toRadians(45))) * -1;
			y = ((radius + 15) * Math.sin(angle + Math.toRadians(45))) * -1;
			handler.objects.add(new Fire(this.x + x, this.y + y, Game.calculateAngle(Main.WIDTH / 2, Main.HEIGHT / 2, mx, my), attackSpeed, attackRange, damage + magicDamage, handler));
			x = ((radius + 15) * Math.cos(angle - Math.toRadians(45))) * -1;
			y = ((radius + 15) * Math.sin(angle - Math.toRadians(45))) * -1;
			handler.objects.add(new Fire(this.x + x, this.y + y, Game.calculateAngle(Main.WIDTH / 2, Main.HEIGHT / 2, mx, my), attackSpeed, attackRange, damage + magicDamage, handler));
			cast1();
		}
	}
	
	public void takeDamage(String damageType, double damage) {
		if (vulnerable && health > 0) {
			if (damageType.equals("physical")) {
				health -= Math.max(0, (damage - armor));
			}
			if (damageType.equals("magical")) {
				health -= Math.max(0, (damage - magicResistance));
			}
			if (damageType.equals("pure")) {
				health -= damage;
			}
		}
	}
	
	public void takeExperience(double experience) {
		this.experience += experience;
	}
	
	public void takeMana(double mana) {
		this.mana += mana;
		if (this.mana > maxMana) {
			this.mana = maxMana;
		}
	}
	
	public void travel() {
		if (id == ID.player) {
			if (moveUp && moveLeft) {
				x -= movementSpeed * 0.8;
				y -= movementSpeed * 0.8;
			} else if (moveUp && moveRight) {
				x += movementSpeed * 0.8;
				y -= movementSpeed * 0.8;
			} else if (moveDown && moveLeft) {
				x -= movementSpeed * 0.8;
				y += movementSpeed * 0.8;
			} else if (moveDown && moveRight) {
				x += movementSpeed * 0.8;
				y += movementSpeed * 0.8;
			} else if (moveUp) {
				y -= movementSpeed;
			} else if (moveDown) {
				y += movementSpeed;
			} else if (moveLeft) {
				x -= movementSpeed;
			} else if (moveRight) {
				x += movementSpeed;
			}
		} else {
			x += velocityX;
			y += velocityY;
			travelledDistance += movementSpeed;
			if (id == ID.bullet) {
				if (travelledDistance >= attackRange) {
					alive = false;
				}
			}
		}
	}

}
