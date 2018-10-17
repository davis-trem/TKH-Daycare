package caring;

public class Baby implements Runnable{
	
	private String name;
	private long timeTilDead;
	private boolean isSafe;
	private boolean isAlive;
	
	public Baby(String name) {
		this.name = name;
		this.isSafe = false;
		this.isAlive = true;
		updateTimeTilDead();
		
		System.out.println(name + " was born");
	}

	public String getName() {
		return name;
	}
	
	public boolean getIsAlive() {
		return this.isAlive;
	}
	
	public void updateTimeTilDead() {
		this.timeTilDead = 10 - Math.round(Math.random()*5);
	}
	
	public void setIsSafe(boolean status) {
		this.isSafe = status;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isAlive) {
			System.out.println(name + " will die in " + timeTilDead + " seconds");
			pauseThread(timeTilDead * 1000);
			if(!isSafe) {
				isAlive = false;
			}
			isSafe = false;
			updateTimeTilDead();
		}
		
		System.out.println(name + " is dead. Why have you forsaken them!");
		
	}
	
	public void pauseThread(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
