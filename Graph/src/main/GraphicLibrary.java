package main;

public class GraphicLibrary {
	public static int MANDELBROT_PRECISION = 100;
	public static double calcMandelbrot(double x, double y) {
		final double temp = Math.pow(Main.frame, 1.1d);
		x/=temp;
		y/=temp;
		x += 0.25;
		int iterations = 0;
		
		double zReal = 0;
		double zImaginary = 0;
		
		while(iterations < MANDELBROT_PRECISION) {
			if(zReal*zReal+zImaginary*zImaginary >= 4)
				break;
			final double a = zReal, b = zImaginary;
			zReal = a*a - b*b + x;
			zImaginary = 2*a*b + y;
			iterations ++;
		}
		
		return iterations-MANDELBROT_PRECISION+1;
	}
	public static double triangle(double x, double y) {
		if(x == -Main.HALF_WINDOW_SIZE) {
			Main.map[0][0] = 1;
			return 0;
		}
		int h = get((int) (x-1+Main.HALF_WINDOW_SIZE), (int) (Main.HALF_WINDOW_SIZE-y-1));
		h <<= 1;
		h += get((int) (x-1+Main.HALF_WINDOW_SIZE), (int) (Main.HALF_WINDOW_SIZE-y));
		h <<= 1;
		h += get((int) (x-1+Main.HALF_WINDOW_SIZE), (int) (Main.HALF_WINDOW_SIZE-y+1));
		if (h > 1 && h < 6) {
			return 1;
		}
		return 0;
	}

	private static int get(int x, int y) {
		try {
			return (int) Main.map[x][y];
		}catch(IndexOutOfBoundsException ioobe) {
			return 0;
		}
	}
	
	public static double calcSin(double x, double y) {
		return Math.sin(x/Main.HALF_WINDOW_SIZE*Math.PI)*Main.HALF_WINDOW_SIZE-y;
	}
	
	public static double calcCircle(double x, double y) {
		return x*x+y*y-Main.HALF_WINDOW_SIZE*Main.HALF_WINDOW_SIZE;
	}
	
	public static double calcQuadratic(double x, double y) {
		return x * x - y;
	}
}
