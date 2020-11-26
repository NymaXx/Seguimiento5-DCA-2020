package Model;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Logic implements Runnable {

	private PApplet app;
	//private ArrayList<ModelPerson> persons;
	private ArrayList<ModelHealthy> health;
	private ArrayList<ModelInfected> infect;
	private ArrayList<ModelRecovered> recov;
	private ArrayList<NumsTo> Arr;
	private NumValueCompare valueCompare;
	String[] data;
	
	int counter;
	public static int sanoCount, infecCount, recCount;
	
	
	
	public Logic(PApplet app) {
		this.app = app;
		counter=15;
		
		valueCompare= new NumValueCompare();
		//persons = new ArrayList<ModelPerson>();
		health = new ArrayList <ModelHealthy>();
		infect = new ArrayList <ModelInfected>();
		recov = new ArrayList <ModelRecovered>();
		Arr = new ArrayList <NumsTo>();
		Arr.add(new NumsTo(sanoCount, app));
		Arr.add(new NumsTo(recCount, app));
		Arr.add(new NumsTo(infecCount, app));
		
		String[] data = app.loadStrings("data.txt");
		String[] sanos = data[0].split(":",2);
		int nSanos = Integer.parseInt(sanos[1]);
		for (int i = 0; i < nSanos; i++) {
			ModelHealthy h = new ModelHealthy(app);
			health.add(h);
			//OJO PACHO
			sanoCount = health.size();
			new Thread(h).start();
		}
		String[] infectados = data[1].split(":",2);
		int nInfectados = Integer.parseInt(infectados[1]);
		for (int i = 0; i < nInfectados; i++) {
			ModelInfected f = new ModelInfected(app);
			infect.add(f);
			//OJO PACHO X2
			infecCount = infect.size();
			new Thread(f).start();
		}
		String[] recuperados = data[2].split(":",2);
		int nRecuperados = Integer.parseInt(recuperados[1]);
		for (int i = 0; i < nRecuperados; i++) {
			ModelRecovered r = new ModelRecovered(app);
			recov.add(r);
			//OJO PACHO X3
			recCount = recov.size();
			new Thread(r).start();
		
		}
		
	
		
		
	}

	public void sortCounters(char  c) {
		
		/*for(int i=0; i< Arr.size(); i++) {
			app.text(Arr.get(i).getValue(), 10, 20 + (20 * i));
		}*/
		
		switch(c) {
		case 'n':
			Collections.sort(Arr);
			
		
			break;
			
		case 'p':
			Collections.sort(Arr,valueCompare);
			break;
		default:
			break;
		}
		
			
	}
	
	
	
	
	public void pintar() {
		
		for (int i = 0; i < health.size(); i++) {
			health.get(i).pintar();
		}
		for (int i = 0; i < infect.size(); i++) {
			infect.get(i).pintar();
		}
		for (int i = 0; i < recov.size(); i++) {
			recov.get(i).pintar();
		}
		app.noStroke();
		app.rect(0,0,700,70);
		app.fill(255);
		app.textSize(15);
		app.text("Sanos    " + sanoCount, 20,20);
		app.text("Infectados    "+ infecCount , 20,40);
		app.text("Recuperados    " + recCount, 20,60);
		for(int i=0; i< Arr.size(); i++) {
			app.text(Arr.get(i).getValue(), 10, 20 + (20 * i));
			System.out.println(Arr.get(i));
		}
	
		
		
	}
	
	public void Recoveredtimer(){
		if(app.frameCount% 40 == 0) {
			counter --;
			//System.out.println(counter);
			}
		if(counter == -1) {
			counter = 15;
			
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
		
		
	}
	public void run() {
	
		while(true) {
				for (int i = 0; i < infect.size(); i++) {
					ModelInfected a = infect.get(i);
					for (int j = 0; j < health.size(); j++) {
						
						ModelHealthy b = health.get(j);
						if (PApplet.dist(a.getPosX(), a.getPosY(), b.getPosX(), b.getPosY())<=a.getRadio()*2) {
							a.setSpeedX(a.getSpeedX()*-1);
							a.setSpeedY(a.getSpeedY()*-1);
							b.setSpeedX(b.getSpeedX()*-1);
							b.setSpeedY(b.getSpeedY()*-1);
							/*if (a instanceof ModelInfected && b instanceof ModelHealthy) {*/
								float posibility = app.random(0,1);
								int x = b.posX;
								int y = b.posY;
								if(posibility > 0.1) {
								b.setVivo(false);
								health.remove(b);
								ModelInfected c = new ModelInfected(app);
								a.setPosX(b.getPosX());
								a.setPosY(b.getPosY());
								infect.add(c);
								c.setPosX(b.getPosX());
								c.setPosY(b.getPosY());
								sanoCount = health.size();
								infecCount = infect.size();
								new Thread(c).start();
								break;
							}  
						//}	
					}
						
				}
					
				for (int q = 0; q < recov.size(); q++) {
					ModelRecovered b = recov.get(q);
					if(counter == 0 ) {
					if (a.isVivo() == false) {
						int x = a.getPosX();
						int y = a.getPosY();
						infect.remove(a);
						b.setPosX(x);
						b.setPosY(y);
						recov.add(b);
						new Thread(b).start();
						recCount = infect.size();
						
								}
							}
						}
					}
				}
		
	
	}
	public void validateExcep() throws PercentException{
		if(sanoCount <= 30) {
			throw new PercentException("Mas del 30% ha sido infectado");
		
		}
	}

	public static int getSanoCount() {
		return sanoCount;
	}

	public static void setSanoCount(int sanoCount) {
		Logic.sanoCount = sanoCount;
	}

	public static int getInfecCount() {
		return infecCount;
	}

	public static void setInfecCount(int infecCount) {
		Logic.infecCount = infecCount;
	}

	public static int getRecCount() {
		return recCount;
	}

	public static void setRecCount(int recCount) {
		Logic.recCount = recCount;
	}
	
	
}
