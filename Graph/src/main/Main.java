package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	private static int OFFSET_X = 8;
	private static int OFFSET_Y = 31;

	public static int WINDOW_SIZE = 500;
	public static double HALF_WINDOW_SIZE = WINDOW_SIZE / 2.0d;
	
	public static double[][] map = new double[WINDOW_SIZE+1][WINDOW_SIZE+1];
	private boolean draw = false;
	
	private static boolean FILL_MODE = false;
	
	public static double frame = 1;
	
	public Main() {
		init();
		draw = true;
		repaint();
	}
	
	private double calc(double x, double y) {
		return x*x*x*x*x-350*x*x*x+9999*x-6500*y; // change graphic here
		// it graphs the pixels where the value is zero
		
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WINDOW_SIZE+OFFSET_X+8, WINDOW_SIZE+OFFSET_Y+8);
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Graph");
		setVisible(true);
	}

	@Override
	public void paint(Graphics h) {
		if(!draw)
			return;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				map[i][j] = calc(i-HALF_WINDOW_SIZE, HALF_WINDOW_SIZE-j);
			}
		}
		BufferedImage buffer = new BufferedImage(WINDOW_SIZE, WINDOW_SIZE, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
		
		g.setColor(Color.WHITE);
		
		for(int i=0; i<WINDOW_SIZE; i++) {
			for(int j=0; j<WINDOW_SIZE; j++) {
				if (FILL_MODE) {
					if(map[i][j] <= 0) {
						//g.setColor(Color.getHSBColor((float) (map[i][j]/GraphicLibrary.MANDELBROT_PRECISION), 1, 1));
						g.fillRect(i, j, 1, 1);
					}
				} else if (((map[i][j] <= 0) != (map[i+1][j] <= 0)) || ((map[i][j] <= 0) != (map[i][j+1] <= 0)) ||((map[i][j] <= 0) != (map[i+1][j+1] <= 0))) {
					g.fillRect(i, j, 1, 1);
				}
			}
		}
		
		h.drawImage(buffer, OFFSET_X, OFFSET_Y, WINDOW_SIZE, WINDOW_SIZE, null);
		//frame++;
		frame ++;
		repaint();
	}

	public static void main(String[] args) {
		new Main();

	}

}
