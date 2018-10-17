package model;

import java.util.ArrayList;

public class DayCare {
	
	private ArrayList<Baby> playpin;
	
	public DayCare(){
		playpin = new ArrayList<Baby>();
	}
	
	public Baby getBaby(String name) {
		for(int i = 0; i < playpin.size(); i++) {
			if( playpin.get(i).getName().equalsIgnoreCase(name) ) {
				return playpin.get(i);
			}
		}
		return null;
	}
	
	public Baby addBaby(String name) {
		for(int i = 0; i < playpin.size(); i++) {
			if( playpin.get(i).getName().equalsIgnoreCase(name) ) {
				System.out.println(name + " is already in the playpin");
				return null;
			}
		}
		
		Baby b = new Baby(name);
		playpin.add(b);
		System.out.println(b.getName() + " was added to the playpin");
		
		Thread t = new Thread(b);
		t.start();
		
		return b;
	}
	
	public boolean saveBaby(String name) {
		Baby b = null;
		for(int i = 0; i < playpin.size(); i++) {
			if( playpin.get(i).getName().equalsIgnoreCase(name) ) {
				//check if baby is alive
				if( playpin.get(i).getIsAlive() ) {
					b = playpin.get(i);
					b.setIsSafe(true);
					System.out.println(b.getName() + " is safe...for now!");
					return true;
				}else {
					System.out.println(name + " is already dead. Let them rest in peace!");
					return false;
				}
			}
		}
		System.out.println("That baby does not exist");
		return false;
	}
}
