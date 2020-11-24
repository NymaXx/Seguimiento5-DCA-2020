package Model;

import java.util.ArrayList;

import processing.core.PApplet;

public class Logic implements Runnable {

	private PApplet app;
	private ArrayList<ModelPerson> persons;
	String[] data;
	int counter;
	int sanoCount, infecCount, recCount;
	
	public Logic(PApplet app) {
		this.app = app;
		counter=15;
		
		persons = new ArrayList<ModelPerson>();
		
		String[] data = app.loadStrings("data.txt");
		String[] sanos = data[0].split(":",2);
		int nSanos = Integer.parseInt(sanos[1]);
		for (int i = 0; i < nSanos; i++) {
			ModelPerson s = new ModelHealthy(app);
			persons.add(s);
			//OJO PACHO
			sanoCount = nSanos;
			new Thread(s).start();
		}
		String[] infectados = data[1].split(":",2);
		int nInfectados = Integer.parseInt(infectados[1]);
		for (int i = 0; i < nInfectados; i++) {
			ModelPerson s = new ModelInfected(app);
			persons.add(s);
			//OJO PACHO X2
			infecCount = nInfectados;
			new Thread(s).start();
		}
		String[] recuperados = data[2].split(":",2);
		int nRecuperados = Integer.parseInt(recuperados[1]);
		for (int i = 0; i < nRecuperados; i++) {
			ModelPerson s = new ModelRecovered(app);
			persons.add(s);
			//OJO PACHO X3
			recCount = nRecuperados;
			
			new Thread(s).start();
		
		}
		
	}

	public void pintar() {
		
		for (int i = 0; i < persons.size(); i++) {
			persons.get(i).pintar();
		}
		app.noStroke();
		app.rect(0,0,700,70);
		app.fill(255);
		app.textSize(15);
		app.text("Sanos    " + sanoCount, 20,20);
		app.text("Infectados    " + infecCount, 20,35);
		app.text("Recuperados    " + recCount, 20,50);
		
	}
	
	public void Recoveredtimer(){
		if(app.frameCount% 50 == 0) {
			counter --;
			//System.out.println(counter);
			}
		
		/*for(int i =0; i < persons.size(); i++) {
				for(int j=0; j < persons.size(); j++) {
						ModelPerson a = persons.get(i);
						ModelPerson b = persons.get(j);
		if(counter == 0) {
		if(a instanceof ModelInfected && b instanceof ModelRecovered) {
				int x = a.posX;
				int y = a.posY;
				persons.remove(a);
				ModelPerson c =new ModelRecovered(app);
				c.setPosX(x);
				c.setPosY(y);
				persons.add(c);
								}
						}
		if(counter == -1) {
			counter = 15;
		}
			}
		}*/
		System.out.println(counter);
		
	}
	public void run() {
	
		while(true) {
				for (int i = 0; i < persons.size(); i++) {
					for (int j = 0; j < persons.size(); j++) {
						ModelPerson a = persons.get(i);
						ModelPerson b = persons.get(j);
						if (PApplet.dist(a.getPosX(), a.getPosY(), b.getPosX(), b.getPosY())<=a.getRadio()*2) {
							a.setSpeedX(a.getSpeedX()*-1);
							a.setSpeedY(a.getSpeedY()*-1);
							b.setSpeedX(b.getSpeedX()*-1);
							b.setSpeedY(b.getSpeedY()*-1);
							if (a instanceof ModelInfected && b instanceof ModelHealthy) {
								float posibility = app.random(0,1);
								int x = b.posX;
								int y = b.posX;
								if(posibility > 0.1) {
								b.setVivo(false);
								persons.remove(b);
								ModelPerson c = new ModelInfected(app);
								c.setPosX(x);
								c.setPosY(y);
								persons.add(c);
								
								//sanoCount = persons.size();
								
								new Thread(c).start();}
								break;
							} else if (b instanceof ModelInfected && a instanceof ModelHealthy) {
								float posibility = app.random(0,1);
								int x = a.posX;
								int y = a.posX;
								if(posibility > 0.1) {
								a.setVivo(false);
								persons.remove(a);
								ModelPerson c = new ModelInfected(app);
								c.setPosX(x);
								c.setPosY(y);
								persons.add(c);
								new Thread(c).start();}
								break;
							} 
						}
						
						
					
					}
				
				
				for (int k = 0; k < persons.size(); k++) {
					ModelPerson a = persons.get(k);
					if(counter == 0 && a instanceof ModelInfected) {
					if (a.isVivo() == false) {
						int x = a.getPosX();
						int y = a.getPosY();
						persons.remove(a);
						ModelPerson b = new ModelRecovered(app);
						b.setPosX(x);
						b.setPosY(y);
						persons.add(b);
						new Thread(b).start();
					}
				  }
				}
			}
		  }
		}
	
	public void vailadeExcep() throws PercentException{
		if(sanoCount <= 30) {
			throw new PercentException("Mas del 30% ha sido infectado");
		
		}
	}
	}
				
				
		
	
	
	


