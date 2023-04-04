package main;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			object.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			object.render(g);
		}
	}

}
