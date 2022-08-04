package Gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class gui {
	int x,y;

	public static void main(String[] args) 
	{
		
		jfra os = new jfra();
		
		
		
	}

}

	
	class jfra extends JFrame 
	{
		public jfra()
		{
			
			setLayout(null);
			setVisible(true);
			setSize(850, 650);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			addMouseListener(this);
			
			JPanel pan1 = new JPanel();
			pan1.setBackground(Color.white);
			pan1.setBounds(50,150,700,500);

			pan1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int x =e.getX();
				 int y = e.getX();
					
					Graphics g = getGraphics();
					g.fillOval(3, 3, 6, 6);
					
				}

				
			});
			
		
			
			
			add(pan1);
			
	
		
		}

		
		
		
	}
	
	

	

