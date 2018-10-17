package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DayCareController;

public class DayCareView extends JFrame {
	
	// ElevatorView dimensions
	private static final int VIEW_WIDTH = 800;
	private static final int VIEW_HEIGHT = 435;
	
	private ImagePanel bg;
	private JPanel topview;
	private JPanel bottomview;
	
	private JLabel enterNameLabel;
	private JButton addBaby;
	private JTextField babyName;

	private DayCareController controller;
   	
   	public DayCareView() {
   		super("TKH DAYCARE");
   		//setContentPane(new JLabel(new ImageIcon("mvc/view/images/bg.png")));
   		controller = new DayCareController();
   		
   		bg = new ImagePanel("mvc/view/images/bg.png");
   		topview = new JPanel();
   		bottomview = new JPanel();
   		
   		//topview.setBackground(new Color(41f, 163f, 163f, 0.5f));
   		bottomview.setBackground(new Color(41, 163, 163));

   		//getContentPane().add(bg, BorderLayout.CENTER );
   		getContentPane().add( topview, BorderLayout.CENTER );
        getContentPane().add( bottomview, BorderLayout.SOUTH );
        //getContentPane().add(new JLabel("ZXCVBN"), BorderLayout.EAST );
        
        enterNameLabel = new JLabel("Enter Baby's name");
        bottomview.add(enterNameLabel);
   		
        babyName = new JTextField(8);
   		bottomview.add(babyName);
		
		addBaby = new JButton("Add Baby");
		bottomview.add(addBaby);
		
		babyName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					JPanel babyView = controller.addBabyAction(babyName);
					if(babyView != null) {
						topview.add(babyView);
						topview.revalidate();
					}
						
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		addBaby.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel babyView = controller.addBabyAction(babyName);
				if(babyView != null) {
					topview.add(babyView);
					topview.revalidate();
				}
			}
		});
		
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		pack();
   	}
   	
   	public Dimension getPreferredSize() {
   		return new Dimension(VIEW_WIDTH, VIEW_HEIGHT);
   	}
   	
   	public Dimension getMinimumSize() {
   		return getPreferredSize();
   	}
   	
   	public Dimension getMaximunSize() {
   		return getPreferredSize();
   	}
   	
   	
   	class ImagePanel extends JPanel {
   		private Image img;
   		
   		public ImagePanel(String img) {
   			this(new ImageIcon(img).getImage());
   		}
   		
   		public ImagePanel(Image img) {
   			this.img = img;
   			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
   		    setPreferredSize(size);
   		    setMinimumSize(size);
   		    setMaximumSize(size);
   		    setSize(size);
   		    setLayout(null);
   		}
   		
   		public void paintComponent(Graphics g) {
   			//super.paintComponent(g);
   			g.drawImage(img, 0, 0, this);
   		}
   	}
   	
}


