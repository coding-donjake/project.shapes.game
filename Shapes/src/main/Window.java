package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	public Window(Main main) {
		JFrame frame = new JFrame("SHAPES");
		frame.setPreferredSize(new Dimension(main.WIDTH, main.HEIGHT));
		frame.setMaximumSize(new Dimension(main.WIDTH, main.HEIGHT));
		frame.setMinimumSize(new Dimension(main.WIDTH, main.HEIGHT));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(main);
		main.start();
	}

}
