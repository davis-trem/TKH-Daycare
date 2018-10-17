package caring;
import java.util.ArrayList;

public class DayCareArrayList {
	private ArrayList<Baby> playpin;
	
	public DayCareArrayList(){
		playpin = new ArrayList<Baby>();
	}
	
	public void addBaby(String name) {
		for(int i = 0; i < playpin.size(); i++) {
			if( playpin.get(i).getName().equalsIgnoreCase(name) ) {
				System.out.println(name + " is already in the playpin");
				return;
			}
		}
		
		Baby b = new Baby(name);
		playpin.add(b);
		System.out.println(b.getName() + " was added to the playpin");
		
		Thread t = new Thread(b);
		t.start();
	}
	
	public void saveBaby(String name) {
		Baby b = null;
		for(int i = 0; i < playpin.size(); i++) {
			//check if baby is in playpin
			if( playpin.get(i).getName().equalsIgnoreCase(name) ) {
				//check if baby is alive
				if( playpin.get(i).getIsAlive() ) {
					b = playpin.get(i);
					b.setIsSafe(true);
					System.out.println(b.getName() + " is safe...for now!");
					return;
				}else {
					System.out.println(name + " is already dead. Let them rest in peace!");
					return;
				}
				
			}
		}
		//Only happens if baby was not found during loop
		System.out.println("That baby does not exist");
	}
}
