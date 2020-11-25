package Model;


import processing.core.PApplet;

public class NumsTo implements Comparable <NumsTo>{
	
	public Logic log;
	public int value;
	private PApplet app;
	
	NumsTo(int value, PApplet app){
		this.app=app;
		this.value=value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(NumsTo SigCount) {
		// TODO Auto-generated method stub
		return  this.value - SigCount.getValue();
	}

}