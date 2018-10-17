package caring;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("There are currently no babies in your daycare\n"
				+ "To add a baby to your daycare type \"add\" followed by the baby's name\n"
				+ "\tExample: add Elvis\n"
				+ "There cannot be multiple babies with the same name\n\n"
				+ "When baby is in daycare it will die in random amount of seconds\n"
				+ "Type \"save\" followed by the baby's name to keep it from dying\n"
				+ "\tExample: save Elvis\n"
				+ "Type \"exit\" to stop program.");
		
		System.out.println("Enter command: ");
		String cmd = sc.nextLine();
		
		DayCare dc = new DayCare();
		
		while( !cmd.equalsIgnoreCase("exit") ) {
			String[] str = cmd.split(" ");
			if( str.length == 2 && str[0].equalsIgnoreCase("add") ) {
				dc.addBaby(str[1]);
			}else if( str.length == 2 && str[0].equalsIgnoreCase("save") ) {
				dc.saveBaby(str[1]);
			}else {
				System.out.println("Incorrect input!");
			}
			System.out.println("Enter command: ");
			cmd = sc.nextLine();
		}
		
		System.out.println("Program ended");
		sc.close();
		
	}

}
