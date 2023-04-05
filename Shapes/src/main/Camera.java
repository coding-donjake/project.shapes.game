package main;

public class Camera {
	
	public static double calcX(double x, Handler handler) {
		double value = 0;
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				value = x - object.x;
				value = (Main.WIDTH / 2) + value;
				break;
			}
		}
		return value;
	}
	
	public static double calcY(double y, Handler handler) {
		double value = 0;
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				value = y - object.y;
				value = (Main.HEIGHT / 2) + value;
				break;
			}
		}
		return value;
	}

}
