package View;

import Model.Logic;
import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(Main.class.getName());
		
		
	}
	
	private Logic log;
	
	public void settings() {
		size(700,500);
		
	}
	
	
	public void setup() {
		log= new Logic(this);
		new Thread(log).start();
		noStroke();
		
	}
	
	
	public void draw() {
		background(255);
		//log.Recoveredtimer();
		log.pintar();
		
		
		
	}
	
	public void keyPressed() {
		log.sortCounters(key);
	}
}
