package driver;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import controller.*;
import model.*;
import view.*;

public class Main{
	
	// model, view and controller
//   private DayCare model;
//   private DayCareView view;
//   private DayCareController controller;

   // constructor instantiates model, view, and controller
   /*
   public Main(){
      super( "TKH DAYCARE" );

	  // instantiate model, view and ,controller
	  model = new DayCare();
	  view = new DayCareView();
	  controller = new DayCareController( model );
	
	  // register View for Model events
	  //model.setElevatorSimulationListener( view );
	
	  // add view and controller to ElevatorSimulation
      getContentPane().add( view, BorderLayout.CENTER );
      getContentPane().add( controller, BorderLayout.SOUTH );

   }
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DayCareView view = new DayCareView();
		view.setVisible(true);
	}

}
