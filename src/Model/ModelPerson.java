package Model;

import processing.core.PApplet;

public class ModelPerson   implements Runnable{

	protected PApplet app;
	protected int posX;
	protected int posY;
	protected int speedX;
	protected int speedY;
	protected boolean vivo;
	protected int radius;
	
	
	ModelPerson(PApplet app) {
		// TODO Auto-generated constructor stub
		this.app = app;
		speedX = 2;
		speedY = 2;
		radius = 7;
		posX = (int) app.random(radius,app.width-radius);
		posY = (int) app.random(radius+70,app.height-radius);
		float r = app.random(0,1);
		if (r <= 0.5){
			speedX *= -1;
		}
		r = app.random(0,1);
		if (r <= 0.5){
			speedY *= -1;
		}
		vivo = true;
	}
	
	public void pintar() {
	
	}
	
	@Override
	public void run() {
		while(vivo) {
			try {
				posX += speedX;
				posY += speedY;
				if (posX < radius || posX > app.width-radius) {
					speedX *= -1;
				}
				if (posY < radius+70 || posY > app.height-radius) {
					speedY *= -1;
				}
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getRadio() {
		return radius;
	}

	public void setRadio(int radius) {
		this.radius = radius;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	


}
