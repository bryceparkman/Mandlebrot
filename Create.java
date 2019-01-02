import org.apache.commons.math3.complex.Complex;
import processing.core.PApplet;

public class Create extends PApplet {
	float w;
	boolean zoom;
	int[][] visited;
	public static void main(String[] args) {
		PApplet.main("Create");
    }
	
	public void settings(){
		size(640, 360);
		//noLoop();
	}
	
	public void setup() {
		frameRate(60);
		colorMode(HSB,255);
		zoom = true;
		//colorMode(RGB,255);
		visited = new int[width][height];
		w = 6f;
	}

	public void draw() {
	float h = (w * height) / width;
	float xmin = -w/2;
	float ymin = -h/2;
	int maxiterations = 100;
	loadPixels();
	
	float y = ymin;
	for (int j = 0; j < height; j++) {
	  float x = xmin;
	  for (int i = 0; i < width; i++) {
	    // z = z^2 + c tend towards outside of Mandelbrot range?
		  //x - 0.7399 y+0.19
		Complex z0 = new Complex(x-1.75,y);
		Complex z = z0;
		int n;
		for(n=0;n<maxiterations;n++) {
			if(z.abs()>2.0) break;
			z = new Complex(Math.abs(z.getReal()),Math.abs(z.getImaginary()));
			z = z.multiply(z).add(z0);
		}
	    if (n == maxiterations) {
	      pixels[i+j*width] = color(0,0,0);
	      //pixels[i+j*width] = color(255f,255f,255f);
	    } else {
	      pixels[i+j*width] = color(n * 2.55f,255f,255f);
	      //pixels[i+j*width] = color((float)(n * 2.55f*((1+Math.cos(2*Math.PI*Math.log(n)/Math.log(2)))/2)),255f,255f);
	    }
	    x += w/width;
	  }
	  y += h/height;
	}
	updatePixels();
	if(zoom) {
		if(w>0.1f) {
			w-=0.1f;
		}
		else if(w>0.01f) {
			w-=0.01f;
		}
		else if(w>0.001f){
			w-=0.001f;
		}
		else if(w>0.0001f){
			w-=0.0001f;
		}
		else {
			w-=0.00001f;
		}
	}
	}
}
