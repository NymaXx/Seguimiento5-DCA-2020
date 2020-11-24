package Model;

import processing.core.PApplet;

public class ModelInfected extends ModelPerson {

	public ModelInfected(PApplet app) {
		super(app);
		/*Runnable curar =
			    new Runnable(){
			        public void run(){
			        	while (vivo) {
			    			try {
			    				Thread.sleep(14000);
			    				vivo=false;
			    			} catch (InterruptedException e) {
			    				e.printStackTrace();
			    			}
			    		}
			        }
			    };
			    
			    new Thread(curar).start();*/
	}

	@Override
	public void pintar() {
		app.fill(255,0,0);
		app.stroke(255,0,0);
		app.ellipse(posX, posY, radius*2, radius*2);
		app.fill(0);
		app.text("I", posX, posY);
	}
	

}

