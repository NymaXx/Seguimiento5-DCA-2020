package Model;

import processing.core.PApplet;

public class ModelHealthy extends ModelPerson {

	public ModelHealthy(PApplet app) {
		super(app);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
		app.fill(0,255,0,95);
		app.stroke(0,255,0);
		app.ellipse(posX, posY, radius*2, radius*2);
		app.fill(0);
		app.text("S", posX, posY);
	}

}