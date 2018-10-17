package caring;

public class DayCare {
	private Baby[] playpin;
	
	public DayCare(){
		playpin = new Baby[0];
	}
	
	public void addBaby(String name) {
		//created for increasing size of playpin array
		Baby[] playpinNew = new Baby[playpin.length + 1];
		for(int i = 0; i < playpin.length; i++) {
			//if baby found, stop creating new array cause we don't want to add duplicates 
			if( playpin[i].getName().equalsIgnoreCase(name) ) {
				System.out.println(name + " is already in the playpin");
				return;
			}
			//copying old playpin elements into new playpin
			playpinNew[i] = playpin[i];
		}
		
		Baby b = new Baby(name);
		playpinNew[playpinNew.length - 1] = b;
		playpin = playpinNew.clone();
		System.out.println(b.getName() + " was added to the playpin");
		
		Thread t = new Thread(b);
		t.start();
	}
	
	public void saveBaby(String name) {
		Baby b = null;
		for(int i = 0; i < playpin.length; i++) {
			//check if baby is in playpin
			if( playpin[i].getName().equalsIgnoreCase(name) ) {
				//check if baby is alive
				if( playpin[i].getIsAlive() ) {
					b = playpin[i];
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
