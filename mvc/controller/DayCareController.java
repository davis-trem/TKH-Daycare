package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import model.Baby;
import model.DayCare;

public class DayCareController{
	
	
	private DayCare daycare;
	
	private Timer timer;
	private HashMap<String, TimerTask> lifeBarTasks;
	private HashMap<String, Integer> deathTimes;
	private HashMap<String, Date> startTimes;
	
	public DayCareController() {
		this.daycare = new DayCare();
		timer = new Timer();
		lifeBarTasks = new HashMap<String, TimerTask>();
		deathTimes = new HashMap<String, Integer>();
		startTimes = new HashMap<String, Date>();
	}
	
	public JPanel addBabyAction(JTextField babyName) {
		String name = babyName.getText();
		babyName.setText("");
		
		Baby baby = daycare.addBaby(name);
		
		//if baby is null then it was already added to daycare
		if(baby != null) {
			JPanel container = new JPanel();
			JLabel nameLabel = new JLabel(name + " has " + baby.getTimeTilDead() + "sec to live");
			JButton save = new JButton("Save " + name);
			JProgressBar lifeBar = new JProgressBar(0, (int)baby.getTimeTilDead());
			
			container.setBackground(new Color(153, 255, 204));
			container.add(nameLabel);
			container.add(save);
			container.add(lifeBar);
			lifeBar.setStringPainted(true);
			
			//when baby will die
			deathTimes.put(name, (int)daycare.getBaby(name).getTimeTilDead());
			
			//the time this process began
			startTimes.put(name, new Date());
			
			lifeBar.setMaximum(deathTimes.get(name));
			lifeBar.setValue(deathTimes.get(name));
			
			//begin to decrease life bar
			lifeBarTasks.put(name, createLifeBarTask(baby, lifeBar, nameLabel, save, deathTimes.get(name)));
			timer.scheduleAtFixedRate(lifeBarTasks.get(name), 1000, 1000);
			
			save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					save.setEnabled(false);
					
					boolean saved = daycare.saveBaby(name);
					//if saved is false they already died
					if(saved) {
						Baby b = daycare.getBaby(name);
						
						//stop life bar from decreasing
						lifeBarTasks.get(name).cancel();
						timer.purge();
						
						lifeBar.setValue(deathTimes.get(name));
						
						//wait for previous timeTilDeath seconds to finish
						long waitTime = deathTimes.get(name)*1000 - (new Date().getTime() - startTimes.get(name).getTime());
						timer.schedule(new TimerTask() {
							//this happens when waitTime is done
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//update when baby will die								
								deathTimes.put(name, (int)daycare.getBaby(name).getTimeTilDead());
								nameLabel.setText(name + " has " + deathTimes.get(name) + "sec to live");
								
								lifeBar.setMaximum(deathTimes.get(name));
								lifeBar.setValue(deathTimes.get(name));
								
								//begin to decrease life bar
								lifeBarTasks.put(name, createLifeBarTask(b, lifeBar, nameLabel, save, deathTimes.get(name)));
								timer.scheduleAtFixedRate(lifeBarTasks.get(name), 1000, 1000);
								
								//the time this process began
								startTimes.put(name, new Date());
								save.setEnabled(true);
								
								cancel();
								timer.purge();
							}
						}, waitTime);
						
						
					}
				}
			});
			
			return container;
		}
		
		return null;
	}
	
	private TimerTask createLifeBarTask(Baby baby, JProgressBar lifeBar, JLabel nameLabel, JButton save, int timeTilDeath) {
		//decreases timeTilDeath every second until it reaches 0
		System.out.println("timeTilDeath: " + timeTilDeath);
		return new TimerTask() {
			int currentValue = timeTilDeath;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lifeBar.setValue(--currentValue);
				System.out.println(currentValue);
				if(currentValue == 0) {
					//they dead
					lifeBar.setValue(0);
					nameLabel.setText(baby.getName() + " is DEAD!");
					save.setEnabled(false);
					cancel();
					timer.purge();
					return;
				}
			}
		};
	}

}
