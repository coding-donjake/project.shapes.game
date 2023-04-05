package main;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick() {
		boolean clear = false;
		while (!clear) {
			clear = true;
			for (int i = 0; i < objects.size(); i++) {
				GameObject object = objects.get(i);
				if (!object.alive) {
					objects.remove(object);
					clear = false;
				}
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			object.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			if(object.id != ID.player) {
				object.render(g);
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			if(object.id == ID.player) {
				object.render(g);
				break;
			}
		}
	}

}
