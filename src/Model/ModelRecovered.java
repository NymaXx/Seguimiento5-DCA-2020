package Model;

import processing.core.PApplet;

public class ModelRecovered extends ModelPerson {

	public ModelRecovered(PApplet app) {
		super(app);
	}

	public void pintar() {
		app.fill(0,0,255);
		app.stroke(0,0,255);
		app.ellipse(posX, posY, radius*2, radius*2);
		app.fill(0);
		app.text("R", posX, posY);
	}
}