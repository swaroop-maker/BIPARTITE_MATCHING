package Gui;


import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class MakingPolyLines extends JFrame implements MouseListener
{
int cost;
int x,y;
int count=-1;
int n1 =count+1;
//String b = Integer.toString(count);
//LinkedList<Integer> adjlist[];
//adjlist = new LinkedList[10];
boolean adj[][]= new boolean[15][15]; //12
//public double[][] array;
public double[][] array = new double [15][15];    //12
double[][] distanceMatrix1 = new double[15][15];   //12
//this array storing int 
public int ar[][]= new int[15][15];       //12

//array for kunh mukers
double[][] cost1 = new double[15][15];   //12


//this adj list for bfs travel
public LinkedList<Integer> bfs[]=new LinkedList[18];

/*** this array for Travelling sales prob ****/

int c[][] = new int[15][15], tour[] = new int[10];  ///10

/*
     {
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 4, 2, 1},
        {0, 0, 0, 3, 1, 2, 2}
    };


*/

public static void main(String[] args) 
{
	
	
	MakingPolyLines m = new MakingPolyLines();
	
	
}


public MakingPolyLines() 
{	
	//this list for storing x and y positions of vertex
	LinkedList<Integer> adjlist[];
	adjlist = new LinkedList[15];  //12
	
	//this is for storing edge weights
	LinkedList<Integer> adjlist1[];
	adjlist1 = new LinkedList[15];          //12
    
	//add vertices button
	JButton but = new JButton("Add Vertices", new ImageIcon( this.getClass().getResource("/images/add.png")));
	but.setBounds(30,30,150,30);
	
	//add edge with weight button
	JButton but2 = new JButton("Add weighted Edge", new ImageIcon( this.getClass().getResource("/images/connect.png")));
	but2.setBounds(190,30,180,30);
	
	//max match without weight
	JButton maxbut = new JButton(" Max matching");
	maxbut.setBounds(380,30,150,30);
	
	
	//max match button
	JButton but3 = new JButton("Weighted Max Matching");
	but3.setBounds(540, 30, 190, 30);
	
	//buttton for tsp//
	JButton but5= new JButton("Salesman Problem");
	but5.setBounds(750, 80, 180, 30);
	
	
	//for Kunh mukers algorithm
	JButton kunhbut= new JButton("Kuhn Munkres");
	kunhbut.setBounds(750, 30, 180, 30);
	
	
	//for Bfs Travel//
	JButton bfsbut = new JButton("BFS");
	bfsbut.setBounds(540,80,190,30);
	
	//for dfs travel//
	JButton dfsbut = new JButton("DFS");
	dfsbut.setBounds(380,80,150,30);
	
	//refresh button
	JButton but4 = new JButton("Refresh", new ImageIcon( this.getClass().getResource("/images/refresh.png")));
	but4.setBounds(30, 80, 150, 30);
	but4.setForeground(Color.RED);
	
	
	//output =2
	JLabel jl =new JLabel();
	jl.setBounds(145, 110, 50, 30);
	jl.setForeground(Color.RED);
	
	// Maximu matching
	JLabel j2 = new JLabel();
	j2.setBounds(30,100,200,50);
	j2.setFont(new Font("Courier New", Font.BOLD, 13));
	
	//output for perfecr matching or not(perfectmatching)
	JLabel j3 = new JLabel();
	j3.setBounds(30,120,200,50);
	j3.setFont(new Font("Courier New", Font.BOLD, 13));
	
	//label(true or false)
	JLabel jLprot = new JLabel();
	jLprot.setBounds(180,120,200,50);
	jLprot.setFont(new Font("Courier New", Font.BOLD, 13));
	jLprot.setForeground(Color.RED);
	
	
	
	JLabel jLconedge = new JLabel();
	jLconedge.setBounds(220,55,70,50);
	jLconedge.setText("----->");
	
	//for addedge wieght label
	JLabel jLweiedge = new JLabel();
	jLweiedge.setBounds(280,55,50,50);
	jLweiedge.setText("W");
	
	//for max weight (the Max weight of graph=)
	JLabel jlmxwt = new JLabel();
	jlmxwt.setBounds(30,140,500,50);
	jlmxwt.setFont(new Font("Courier New", Font.BOLD, 13));
	
	//for max weight output =o.o
	JLabel jlmxwtot = new JLabel();
	jlmxwtot.setBounds(165,140,200,50);
	jlmxwtot.setFont(new Font("Courier New", Font.BOLD, 13));
	jlmxwtot.setForeground(Color.RED);
	
	
	/***** Drop  down Button******/

	JComboBox comboBox = new JComboBox();
	//comboBox.setEditable(true);
	comboBox.setToolTipText("algo");
	//comboBox.setBounds(750, 30, 140, 30);
	//comboBox.setRenderer(new MyComboBoxRenderer("COUNTRY"));
    
	comboBox.addItem("Tracking problem");
	
	JMenuBar menubar = new JMenuBar();
	menubar.setBounds(750, 30, 140, 30);
	
	
	JMenu menu = new JMenu("Algorithims");
	menu.setBackground(Color.red);
	menubar.add(menu);
	menu.setBounds(750, 30, 140, 30);
	
	//intializing menu items
	JMenuItem Tsp = new JMenuItem("Tsp");
	JMenuItem Tld = new JMenuItem("Tld");
	menu.add(Tsp);
	menu.add(Tsp);
	
	
	JPanel pan1 = new JPanel();
	pan1.setBackground(Color.white);
	pan1.setBounds(30, 180, 900, 400);
	Graphics g = getGraphics();
	
	Border LineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
	pan1.setBorder(LineBorder);

	
	//display popup
	JDialog dail = new JDialog();
	
	
	
	
	//button = add vertix inside panel
	
	but.addMouseListener(new MouseAdapter() 
	{
		
		public void mouseClicked(MouseEvent e) 
		{
			
			
			pan1.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
            
			
					count++;
					String b = Integer.toString(count);
					{
					 x =e.getX();
					 y = e.getY();
					Graphics g = getGraphics();
					Graphics2D g2 =(Graphics2D) g;
					//g.drawString(b, x+35, y+205);
					g2.setPaint(Color.green);
					g.fillOval(x+32, y+200, 20, 20);
					g2.setPaint(Color.BLACK);
					g.drawString(b, x+32, y+200);
					g2.setPaint(Color.DARK_GRAY);
					g.drawOval(x+32, y+200, 20, 20);
					
					 adjlist[count]=new LinkedList<>();
					 bfs[count]=new LinkedList<>();
					 adjlist[count].add(x);
					 adjlist[count].add(y);
					
					
					 /*			
					for(int j=0;j<15;j++) {
							 System.out.println(j+" is connected--->"+adjlist[j]);
						 }
					*/
					}
					
				}
			}
			
			
			);
			
			
			
			
			
			
			/*
			
			//org
			count++;
			String b = Integer.toString(count);
			{
			 x =e.getX();
			 y = e.getY();
			Graphics g = getGraphics();
			Graphics2D g2 =(Graphics2D) g;
			//g.drawString(b, x+35, y+205);
			g.drawString(b, x+15, y+50);
			g2.setPaint(Color.green);
			//g.fillOval(x+32, y+205, 20, 20);
			
			g.fillOval(x+15, y+50, 20, 20);
			g2.setPaint(Color.RED);
			g.drawOval(x+15, y+50, 20, 20);
			
			
			//LinkedList<Integer> adjlist[];
		//	adjlist = new LinkedList[10];
			 
			 adjlist[count]=new LinkedList<>();
			 adjlist[count].add(x);
			 adjlist[count].add(y);
			
			}
			 			
			for(int j=0;j<10;j++) {
					 System.out.println(j+" is connected--->"+adjlist[j]);
				 }
			
			
			
			//org
			  
			 */
		}

		
	});
	
	

	
	
	
	
	
	
	
	
	
	
	/* jpanel
	JPanel pan1 = new JPanel();
	pan1.setBackground(Color.white);
	pan1.setBounds(30, 180, 600, 400);
	Graphics g = getGraphics();
	
	Border LineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
	pan1.setBorder(LineBorder);
	*/
	
	
	/*
	pan1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {


			count++;
			String b = Integer.toString(count);
			{
			 x =e.getX();
			 y = e.getY();
			Graphics g = getGraphics();
			Graphics2D g2 =(Graphics2D) g;
			g.drawString(b, x+35, y+205);
			g2.setPaint(Color.green);
			g.fillOval(x+32, y+205, 20, 20);
			
			
			}
		}
	});

	*/
	
	
	JTextField jtf = new JTextField(20);
	jtf.setBounds(200,70,25,25);
	jtf.setVisible(true);
	jtf.setBackground(Color.WHITE);
	
	
	
	JTextField jtf1 = new JTextField(20);
	jtf1.setBounds(250,70,25,25);
	jtf1.setVisible(true);
	jtf1.setBackground(Color.WHITE);
	

	JTextField jtfwei = new JTextField(20);
	jtfwei.setBounds(300,70,25,25);
	jtfwei.setVisible(true);
	jtfwei.setBackground(Color.WHITE);
	
	
	
	
	//Jframe//
	//JFrame fr = new JFrame();//
	getContentPane().setBackground(Color.CYAN);
	setSize(1000,650);
	setLayout(null);
	setTitle("Matching");
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	addMouseListener(this);
	add(pan1);
	add(but);
	add(but2);
	add(but3);
	add(but4);
	add(maxbut);
	add(but5);
	add(kunhbut);
	add(bfsbut);
	add(dfsbut);
	add(jtf);
	add(jtf1);
	add(jtfwei);
	add(jl);
	add(jLprot);
	add(j2);
	add(j3);
	add(jLconedge);
	add(jLweiedge);
	add(jlmxwt);
	add(jlmxwtot);
	add(comboBox);
	//add(menu);
	//add(menubar);
	add(Tsp);
	//add(dail);
	
	//add edge function
	
	but2.addMouseListener  (new MouseAdapter() 
	{
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			{
				
		// jtf.setVisible(true);
		// jtf1.setVisible(true);
		 
		 String x = jtf.getText();
		
		String y = jtf1.getText();
		
		
		//jtfwei.setText("-1");
		String wei=jtfwei.getText();
		
		
		if(wei.isEmpty()) 
		{
			
			jtfwei.setText("-1");
		}
		
		
		
		int i = Integer.parseInt(x);
		int j = Integer.parseInt(y);
		int weit =Integer.parseInt(wei);
		int m =i;
		int n = j;
		
		//add weights to adjacency list of index
		
		//x and y positions first vertex
		int s =adjlist[m].get(0);
		int d =adjlist[m].get(1);
		
		//x and y positions of 2nds vertex
		
		int s1 =adjlist[n].get(0);
		int d1 =adjlist[n].get(1);
		
		int midx =((s+42)+(s1+42))/2 ;
		int midy=+((d+211)+(d1+211))/2;
		
	
		
		Graphics g = getGraphics();
		Graphics2D g2 =(Graphics2D)g;
		g2.setPaint(Color.RED);
		g2.setStroke(new BasicStroke(3));
		
		//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
		g2.draw(new Line2D.Float(s+42, d+211, s1+42, d1+211));
		g2.setPaint(Color.BLACK);
		
		
		if(weit>-1) 
		{
			
		g2.fillRect(midx-5, midy-8, 18, 18);
		g2.setPaint(Color.WHITE);
		g2.drawString(wei, midx-2, midy+4);
		}
		//g2.draw(new Line2D.Float(s+44, d+213, s1+38, d1+213));
		
		System.out.println("Source points ="+s);
		System.out.println("dest points ="+d);
		System.out.print(""+i);
		System.out.print("--"+j);
		System.out.println();
		System.out.println(count);
		
		//m and n are given from userinput
		
		adj[m][n]=true;
		
		array[m][n]=weit;
		ar[m][n]=1;
		
		// this array is filled for kunh algorithm
	
		n1=(n)-((count+1)/2);
		cost1[m][n]=weit;
		
		
		//this linklst for bfs
		bfs[m].add(n);
		bfs[n].add(m);
		
		
		//this array is to be filled for Travelling Sales prob//
		c[m+1][n+1]=weit;
    	c[n+1][m+1]=weit;
    	distanceMatrix1[m][n]=weit;
    	distanceMatrix1[n][m]=weit;
		 /*
		for(int k=0;k<10;k++){
			for(int l=0;l<10;l++) {
				
				System.out.print(adj[k][l]+" ");
			}
			System.out.println("");
		}
		
		*/
		
		jtf.setText("");
		jtf1.setText("");
		jtfwei.setText("");
	//	g.drawLine(s, d, s1, d1);
		
			}
		
		/*
			for(int j=0;j<10;j++) {
				 System.out.println(j+" is connected--->"+adjlist[j]);
			 }
			*/
		}
	});
	
	
	
	
//non weighted max matching button
	
maxbut.addMouseListener  (new MouseAdapter() 
{
		@Override
	public void mouseClicked(MouseEvent e) 
	
	
	
	 {	
			CheckBipartite b = new CheckBipartite();
			if (b.isBipartite(ar, 0,count+1))
			{
				
				
				
			 System.out.println("yes");
			
			jlmxwtot.setText("");
			jlmxwt.setText("");
			maxgraph m = new maxgraph(12);
			System.out.println( "Maximum matching is "+m.maxBPM(adj));
			int j =m.maxBPM(adj);
			String s =String.valueOf(j);
			j2.setText("Max Matching =");
			jl.setBackground(Color.RED);
			jl.setText(s);
			//j3.setText("It's not a perfect Matching");
			j3.setText("");
			
			
			//check whether graph is perfect or not
			if ((count+1)%2==1) 
			{
				j3.setText("Perfect Matching :");
				jLprot.setText("False");
			}
			
			
				if (count>-1)
				{
				
					if ((count+1)%2==0) 
					{
						if((count+1)/2==j) 
						{
							j3.setText("perfect Matching :");
							jLprot.setText("True");
						}
						else 
						{
							j3.setText("Perfect Matching :");
							jLprot.setText("False");
						}
				
					}
			
			}
	
			
			
			
			
			
			}
			
			 else {
			 System.out.println("No");
			//JDialog dail = new JDialog();
				dail.setVisible(true);
				//dail.setSize(200,200);
				dail.setBounds(300, 300, 250, 120);
				dail.add(new JLabel("          This is not a bipartite graph"));
			 }
	
	}
});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//Max weited matching button

but3.addMouseListener  (new MouseAdapter() 
	{
		
		
		
		/*
		Graphics g1 = getGraphics();
		Graphics2D g3 =(Graphics2D)g1;
		int R = (int)(Math.random()*256);
		int G = (int)(Math.random()*256);
		int B= (int)(Math.random()*256);
		Color colo = new Color(R, G, B);
	
		g3.setPaint(colo);
        
		*/
		//random color edges
		
		
		
    //check bipartite or not for above matching    
  public void mouseClicked(MouseEvent e) 
	{
			

		CheckBipartite b = new CheckBipartite();
		if (b.isBipartite(ar, 0,count+1))
		{
			
			
			//redding edges after button click
			for(int m=0;m<count+2;m++) 
			{
				for(int n=0;n<count+2;n++) 
				{
					if(array[m][n]>0) 
					{	
						
						System.out.println(m+""+n);
						double weit=array[m][n];
						int con =(int)Math.round(weit);
						String wei =String.valueOf(con); 
						
						
						
						//x and y positions first vertex
						int s =adjlist[m].get(0);
						int d =adjlist[m].get(1);
						
						//x and y positions of 2nds vertex
						
						int s1 =adjlist[n].get(0);
						int d1 =adjlist[n].get(1);
						
						int midx =((s+42)+(s1+42))/2 ;
						int midy=+((d+211)+(d1+211))/2;
						
						Graphics g = getGraphics();
						Graphics2D g2 =(Graphics2D)g;
						g2.setPaint(Color.RED);
						g2.setStroke(new BasicStroke(3));
						
						//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
						g2.draw(new Line2D.Float(s+42, d+211, s1+42, d1+211));
						g2.setPaint(Color.BLACK);
						
						
						if(weit>-1) 
						{
							
						g2.fillRect(midx-5, midy-8, 18, 18);
						g2.setPaint(Color.WHITE);
						g2.drawString(wei, midx-2, midy+4);
						}
						//g2.draw(new Line2D.Float(s+44, d+213, s1+38, d1+213));
						
					}
				}
			}
			
			//end for readding edges
			
			
			
			HungarianAlgorithm ha = new HungarianAlgorithm(array);
			
			ha.arr("max");
			
			System.out.println("parentclass");
			
			 for (int i = 0; i < array.length; i++) {
		            for (int j = 0; j < array[i].length; j++) {
		                System.out.printf("%.2f\t", array[i][j]);
		            }
		            System.out.println();
		        }
		        System.out.println();
		        //</COMMENT>*/
		        
		        String sumType = "max";

		        double startTime = System.nanoTime();
		        int[][] assignment = new int[array.length][2];
		        assignment = ha.hgAlgorithm(array, sumType);	//Call Hungarian algorithm.
		        double endTime = System.nanoTime();

		        System.out.println("The winning assignment (" + sumType + " sum) is:\n");
		        double sum = 0;
		        for (int i = 0; i < assignment.length; i++) {
		            //<COMMENT> to avoid printing the elements that make up the assignment
		        	double eachsum =0;
		            System.out.printf("array(%d,%d) = %.2f\n", (assignment[i][0] + 0), (assignment[i][1] + 0),
		                    array[assignment[i][0]][assignment[i][1]]);
		            eachsum=array[assignment[i][0]][assignment[i][1]];
		            
		            jlmxwt.setText(""); 
		            
		            /*
		            double do1 = array[assignment[i][0]][assignment[i][1]];
	            	int v = (int)Math.round(do1);
	            	String wei=Integer.toString(v);
	            	//x and y positions first vertex
	        		int s =adjlist[assignment[i][0]].get(0);
	        		int d =adjlist[assignment[i][0]].get(1);
	        		
	        		//x and y positions of 2nd vertex
	        		
	        		int s1 =adjlist[assignment[i][1]].get(0);
	        		int d1 =adjlist[assignment[i][1]].get(1);
	        		

	        		int midx =((s+42)+(s1+42))/2 ;
	        		int midy=+((d+211)+(d1+211))/2;
	        		
	            	
	            	Graphics g = getGraphics();
	        		Graphics2D g2 =(Graphics2D)g;
	        		g2.setPaint(Color.RED);
	        		
	        		
	        		g2.setStroke(new BasicStroke(3));
	        		
	        		//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
	        		g2.draw(new Line2D.Float(s+42, d+211, s1+42, d1+211));
	        		
	        		g2.setPaint(Color.BLACK);
	        		
	        		g2.fillRect(midx-5, midy-8, 15, 15);
	        		g2.setPaint(Color.WHITE);
	        		g2.drawString(wei, midx-2, midy+4);
		            
		            
		            
		            */
		            
		            
		            

	            	
		            
		            
		            
		            
		            
		            
		            
		            //colouring edges that are connected
		            if(eachsum>0) 
		            {
		            	System.out.println(assignment[i][0]+" "+assignment[i][1]);
		            	
		            	double do2 = array[assignment[i][0]][assignment[i][1]];
		            	int v = (int)Math.round(do2);
		            	String wei=Integer.toString(v);
		            	//x and y positions first vertex
		        		int s =adjlist[assignment[i][0]].get(0);
		        		int d =adjlist[assignment[i][0]].get(1);
		        		
		        		//x and y positions of 2nd vertex
		        		
		        		int s1 =adjlist[assignment[i][1]].get(0);
		        		int d1 =adjlist[assignment[i][1]].get(1);
		        		

		        		int midx =((s+42)+(s1+42))/2 ;
		        		int midy=+((d+211)+(d1+211))/2;
		        		
		            	
		            	Graphics g1 = getGraphics();
		        		Graphics2D g3 =(Graphics2D)g1;
		        		
		        		/*
		        		//random color edges
		        		int R = (int)(Math.random()*256);
		        		int G = (int)(Math.random()*256);
		        		int B= (int)(Math.random()*256);
		        		Color colo = new Color(R, G, B);
		        		g3.setPaint(colo);
		        		*/
		        		g3.setPaint(Color.BLUE);
		        		
		        		g3.setStroke(new BasicStroke(3));
		        		
		        		//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
		        		g3.draw(new Line2D.Float(s+42, d+211, s1+42, d1+211));
		        		
		        		g3.setPaint(Color.YELLOW);
		        		
		        		g3.fillRect(midx-5, midy-8, 15, 15);
		        		g3.setPaint(Color.BLACK);
		        		g3.drawString(wei, midx-2, midy+4);
		        		

		            }
		            sum = sum + array[assignment[i][0]][assignment[i][1]];
		            //</COMMENT>
		          
		        }
		        
		        
		        /*
		        for(int j=0;j<assignment.length;j++) 
		        {
		        	for(int k=0;k<assignment.length;k++) 
		        	{
		        		System.out.print(array[j][k]);
		        	}
		        	System.out.println();
		        }
		        */
		        
		        
		        //max weight output labels declartion
		       String s1 =Double.toString(sum);
		       jlmxwt.setText("The Max Wieght =");
		       jlmxwtot.setText(s1);
		        System.out.printf("\nThe %s is: %.2f\n", sumType, sum);
		        
			
			
			
		
			
			
			
			
			maxgraph m = new maxgraph(15);
			System.out.println( "Maximum matching is "+m.maxBPM(adj));
			int j =m.maxBPM(adj);
			String s =String.valueOf(j);
			j2.setText("Max Matching =");
			jl.setBackground(Color.RED);
			jl.setText(s);
			//j3.setText("It's not a perfect Matching");
			j3.setText("");
			
			
			//check whether graph is perfect or not
			if ((count+1)%2==1) 
			{
				j3.setText("Perfect Matching :");
				jLprot.setText("False");
			}
			
			
				if (count>-1)
				{
				
					if ((count+1)%2==0) 
					{
						if((count+1)/2==j) 
						{
							j3.setText("perfect Matching :");
							jLprot.setText("True");
					
						}
						else 
						{
							j3.setText("Perfect Matching :");
							jLprot.setText("False");
						}
				
					}
			
			}
				
				
	 }
		
		
		
		 else {
			 System.out.println("No");
			//JDialog dail = new JDialog();
				dail.setVisible(true);
				//dail.setSize(200,200);
				dail.setBounds(300, 300, 250, 120);
				dail.add(new JLabel("          This is not a bipartite graph"));
			 }
			
			
	}

		private int parseInteger(int con) {
			// TODO Auto-generated method stub
			return 0;
		}
		});
	



/***** button function for kunh mukers algorithm*****/



kunhbut.addMouseListener  (new MouseAdapter() 
{
	
	


//check bipartite or not for above matching    
public void mouseClicked(MouseEvent e) 
	{
	   int length = (count+1)/2;
	  System.out.println("coint "+count+" le"+length);
	   /*
	   int kuhnmod[][]=new int[length][length];
	   for(int i=0;i<cost1.length;i++) 
	   {
		   for(int j=0;j<cost1.length;j++) 
		   {
			   System.out.println(cost1[i][j]);
		   }
	   }
	   */
	   
	  /*
	   double kuhnmod[][]= new double[length][length];
		for(int i=0;i<length;i++)
		{
			int c=0;
			for(int j=length;j<count+1;j++) 
			{
				kuhnmod[i][c]=cost1[i][j];
				
				System.out.print(kuhnmod[i][c]);
				c++;
			}
			System.out.println();
		}
	   
	   
	   */
	   
	   
	   
	
	   
		double  cost2[][]= new double[length][length];
	for(int m=0;m<length;m++) 
	{
		for(int n=0;n<length;n++) 
		{
			cost2[m][n]=0;
			cost2[m][n]=cost1[m][n];
		}
		
	}
	
	
   // HungarianBipartiteMatching hbm = new HungarianBipartiteMatching(kuhnmod);
  //  int[] result = hbm.execute();
    //System.out.println("Bipartite Matching: " + Arrays.toString(result));
    System.out.println("end of result");
   
	
	
	
	
	

		CheckBipartite b = new CheckBipartite();
		if (b.isBipartite(ar, 0,count+1)&&(count+1)%2==0)
		{
			
			
			//redding edges after button click
			for(int m=0;m<count+2;m++) 
			{
				for(int n=0;n<count+2;n++) 
				{
					if(array[m][n]>0) 
					{	
						
						System.out.println(m+""+n);
						double weit=array[m][n];
						int con =(int)Math.round(weit);
						String wei =String.valueOf(con); 
						
						
						
						//x and y positions first vertex
						int s =adjlist[m].get(0);
						int d =adjlist[m].get(1);
						
						//x and y positions of 2nds vertex
						
						int s1 =adjlist[n].get(0);
						int d1 =adjlist[n].get(1);
						
						int midx =((s+42)+(s1+42))/2 ;
						int midy=+((d+211)+(d1+211))/2;
						
						Graphics g = getGraphics();
						Graphics2D g2 =(Graphics2D)g;
						g2.setPaint(Color.RED);
						g2.setStroke(new BasicStroke(3));
						
						//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
						g2.draw(new Line2D.Float(s+42, d+211, s1+42, d1+211));
						g2.setPaint(Color.BLACK);
						
						
						if(weit>-1) 
						{
							
						g2.fillRect(midx-5, midy-8, 18, 18);
						g2.setPaint(Color.WHITE);
						g2.drawString(wei, midx-2, midy+4);
						}
						//g2.draw(new Line2D.Float(s+44, d+213, s1+38, d1+213));
						
					}
				}
			}
			
			//end for readding edges
			
			
			

			 double kuhnmod[][]= new double[length][length];
				for(int i=0;i<length;i++)
				{
					int c=0;
					for(int j=length;j<count+1;j++) 
					{
						kuhnmod[i][c]=0;
						kuhnmod[i][c]=cost1[i][j];
						
						System.out.print(kuhnmod[i][c]);
						c++;
					}
					System.out.println();
				}
			   
			   
			
			
			HungarianAlgorithm ha = new HungarianAlgorithm(kuhnmod);
			
			ha.arr("min");
			
			System.out.println("parentclass");
			
			 for ( int i = 0; i < array.length; i++) {
		            for (int j = 0; j < array[i].length; j++) {
		                System.out.printf("%.2f\t", array[i][j]);
		            }
		            System.out.println();
		        }
		        System.out.println();
		        //OMMENT
		        
		        String sumType = "min";

		        double startTime = System.nanoTime();
		        int[][] assignment = new int[kuhnmod.length][2];
		        assignment = ha.hgAlgorithm(kuhnmod, sumType);	//Call Hungarian algorithm.
		        double endTime = System.nanoTime();

		        System.out.println("The winning assignment (" + sumType + " sum) is:\n");
		        double sum = 0;
		        for (int i = 0; i < assignment.length; i++) {
		            //<COMMENT> to avoid printing the elements that make up the assignment
		        	double eachsum =0;
		            System.out.printf("kuhnmod(%d,%d) = %.2f\n", (assignment[i][0] + 0), (assignment[i][1] + length),
		                    kuhnmod[assignment[i][0]][assignment[i][1]]);
		            eachsum=kuhnmod[assignment[i][0]][assignment[i][1]];
		            
		            jlmxwt.setText(""); 
		            
		            /*
		            double do1 = array[assignment[i][0]][assignment[i][1]];
	            	int v = (int)Math.round(do1);
	            	String wei=Integer.toString(v);
	            	//x and y positions first vertex
	        		int s =adjlist[assignment[i][0]].get(0);
	        		int d =adjlist[assignment[i][0]].get(1);
	        		
	        		//x and y positions of 2nd vertex
	        		
	        		int s1 =adjlist[assignment[i][1]].get(0);
	        		int d1 =adjlist[assignment[i][1]].get(1);
	        		

	        		int midx =((s+42)+(s1+42))/2 ;
	        		int midy=+((d+211)+(d1+211))/2;
	        		
	            	
	            	Graphics g = getGraphics();
	        		Graphics2D g2 =(Graphics2D)g;
	        		g2.setPaint(Color.RED);
	        		
	        		
	        		g2.setStroke(new BasicStroke(3));
	        		
	        		//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
	        		g2.draw(new Line2D.Float(s+42, d+211, s1+42, d1+211));
	        		
	        		g2.setPaint(Color.BLACK);
	        		
	        		g2.fillRect(midx-5, midy-8, 15, 15);
	        		g2.setPaint(Color.WHITE);
	        		g2.drawString(wei, midx-2, midy+4);
		            
		            
		            
		            */
		            
		            
		            

	            	
		            
		            
		            
		            
		            
		            
		            
		            //colouring edges that are connected
		            if(eachsum>=0) 
		            {
		            	System.out.println(assignment[i][0]+" "+(assignment[i][1]+length));
		            	
		            	double do2 = array[assignment[i][0]][assignment[i][1]+length];
		            	int v = (int)Math.round(do2);
		            	String wei=Integer.toString(v);
		            	//x and y positions first vertex
		        		int s =adjlist[assignment[i][0]].get(0);
		        		int d =adjlist[assignment[i][0]].get(1);
		        		
		        		//x and y positions of 2nd vertex
		        		
		        		int s1 =adjlist[length+assignment[i][1]].get(0);
		        		int d1 =adjlist[length+assignment[i][1]].get(1);
		        		

		        		int midx =((s+42)+(s1+42))/2 ;
		        		int midy=+((d+211)+(d1+211))/2;
		        		
		            	
		            	Graphics g1 = getGraphics();
		        		Graphics2D g3 =(Graphics2D)g1;
		        		
		        		/*
		        		//random color edges
		        		int R = (int)(Math.random()*256);
		        		int G = (int)(Math.random()*256);
		        		int B= (int)(Math.random()*256);
		        		Color colo = new Color(R, G, B);
		        		g3.setPaint(colo);
		        		*/
		        		g3.setPaint(Color.BLUE);
		        		
		        		g3.setStroke(new BasicStroke(3));
		        		
		        		//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
		        		g3.draw(new Line2D.Float(s+42, d+211, s1+42, d1+211));
		        		
		        		g3.setPaint(Color.YELLOW);
		        		
		        		g3.fillRect(midx-5, midy-8, 15, 15);
		        		g3.setPaint(Color.BLACK);
		        		g3.drawString(wei, midx-2, midy+4);
		        		

		            }
		            sum = sum + kuhnmod[assignment[i][0]][assignment[i][1]];
		            //</COMMENT>
		          
		        }
		        
		        
		        /*
		        for(int j=0;j<assignment.length;j++) 
		        {
		        	for(int k=0;k<assignment.length;k++) 
		        	{
		        		System.out.print(array[j][k]);
		        	}
		        	System.out.println();
		        }
		        */
		        
		        
		        //max weight output labels declartion
		       String s1 =Double.toString(sum);
		       jlmxwt.setText("The Min Wieght =");
		       jlmxwtot.setText(s1);
		        System.out.printf("\nThe %s is: %.2f\n", sumType, sum);
		        
			
			
			
		
			
			
			
			
			maxgraph m = new maxgraph(15);
			System.out.println( "Maximum matching is "+m.maxBPM(adj));
			int j =m.maxBPM(adj);
			String s =String.valueOf(j);
			j2.setText("Max Matching =");
			jl.setBackground(Color.RED);
			jl.setText(s);
			//j3.setText("It's not a perfect Matching");
			j3.setText("");
			
			
			//check whether graph is perfect or not
			if ((count+1)%2==1) 
			{
				j3.setText("Perfect Matching :");
				jLprot.setText("False");
			}
			
			
				if (count>-1)
				{
				
					if ((count+1)%2==0) 
					{
						if((count+1)/2==j) 
						{
							j3.setText("perfect Matching :");
							jLprot.setText("True");
					
						}
						else 
						{
							j3.setText("Perfect Matching :");
							jLprot.setText("False");
						}
				
					}
			
			}
				
				
	 }
		
		
		
		 else {
			 System.out.println("No");
			//JDialog dail = new JDialog();
				dail.setVisible(true);
				//dail.setSize(200,200);
				dail.setBounds(300, 300, 250, 120);
				dail.add(new JLabel("          This is not a bipartite graph"));
			 }
		 
		 
		
			
			
	}


		//private int parseInteger(int con) {
			// TODO Auto-generated method stub
		//	return 0;
			
			
      //}
});
	















	
/***** function for tsp button
but5.addMouseListener(new MouseAdapter()
{			
	public void MouseClicked(MouseEvent e)
	{
	    System.out.println("entered tsp");
		int n =count++;
		int cost=0;
		 for (int i = 1; i <= n; i++)
	        {
	            tour[i] = i;
	        }
	        // Calling the above Method 1 to
		 Tsp ch = new Tsp();
	        ch.tspdp(c, tour, 1, n);
	 
	        // Now, coming to logic to print the optimal tour
	 
	        // Display message for better readability
	        System.out.print("The Optimal Tour is: ");
	 
	         
	        for (int i = 1; i <= n; i++)
	        {
	 
	            // Printing across which cities should Salesman
	            // travel
	            System.out.print(tour[i] + "->");
	        }
	        // Starting off with the city 1->
	        System.out.println("1");
	 
	        // Print and display the (minimum)cost of the path
	        // traversed
	        System.out.println("Minimum Cost: " + cost);
		
		
	}
			
			
			
			
});
	
*/	

//tsp

bfsbut.addMouseListener  (new MouseAdapter() 
{
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		jlmxwt.setText("");
		
		//this forloop for restoring colour of green to vertices//
		for(int j=0;j<count+1;j++) 
        {
        	
        
        	int s1 =adjlist[j].get(0);
			int d1 =adjlist[j].get(1);
        
        	String b = Integer.toString(j);
			
			 
			Graphics g = getGraphics();
			Graphics2D g2 =(Graphics2D) g;
			//g.drawString(b, x+35, y+205);
			g2.setPaint(Color.green);
			g.fillOval(s1+32, d1+200, 20, 20);
			g2.setPaint(Color.BLACK);
			g.drawString(b, s1+32, d1+200);
			g2.setPaint(Color.DARK_GRAY);
			g.drawOval(s1+32, d1+200, 20, 20);
			
	
        }
		
		
		
		
		LinkedList<Integer> bfsout=new LinkedList<>();
		int bfsst[]=new int[12];
		
		
		
		
		
		 class Bfsdfs 
		{
			 int k=0;
			 
			
			 int V;   // No. of vertices
		   // private LinkedList<Integer> adj[]; //Adjacency Lists
		 
		    // Constructor
		    Bfsdfs(int v)
		    {
		        V = v;
		       // adj = new LinkedList[v];
		        //for (int i=0; i<v; ++i)
		          //  adj[i] = new LinkedList();
		    }
		 
		    // Function to add an edge into the graph
		    void addEdge(int v,int w)
		    {
		      //  adj[v].add(w);
		    }
		   
		   
		   
		    // prints BFS traversal from a given source s
		    void BFS(int s ,LinkedList adj[]) 
		    {
		    	 
		        // Mark all the vertices as not visited(By default
		        // set as false)
		        boolean visited[] = new boolean[V];
		 
		        // Create a queue for BFS
		        LinkedList<Integer> queue = new LinkedList<Integer>();
		 
		        // Mark the current node as visited and enqueue it
		        visited[s]=true;
		        queue.add(s);
		 
		        while (queue.size() != 0)
		        {
		            // Dequeue a vertex from queue and print it
		        	
		            s = queue.poll();
		            bfsst[k]=s;
		            bfsout.add(s);
		            k++;
		            
		            System.out.print(s+"--> ");
		           
		            
		            /*
		        	
					
		            */
		            
		            
		            
		            
		            
		            
		 
		            // Get all adjacent vertices of the dequeued vertex s
		            // If a adjacent has not been visited, then mark it
		            // visited and enqueue it
		            Iterator<Integer> i = adj[s].listIterator();
		            while (i.hasNext())
		            {
		                int n = i.next();
		                if (!visited[n])
		                {
		                    visited[n] = true;
		                    queue.add(n);
		                }
		            }
		        }
		        
		        
		        
		        
		        

				 for(int j=0;j<count+1;j++) 
			        {
			        	System.out.println(bfsst[j]);
			        
			        
			        
			        String b=Integer.toString(bfsst[j]);
			      //x and y positions first vertex
					int s1 =adjlist[bfsst[j]].get(0);
					int d1 =adjlist[bfsst[j]].get(1);
					
					//x and y positions of 2nds vertex
					
					int s2 =adjlist[bfsst[j+1]].get(0);
					int d2 =adjlist[bfsst[j+1]].get(1);
					
					//int midx =((s+42)+(s1+42))/2 ;
					//int midy=+((d+211)+(d1+211))/2;
					
					Graphics g = getGraphics();
					Graphics2D g2 =(Graphics2D)g;
					g2.setPaint(Color.BLUE);
					g.fillOval(s1+32, d1+200, 20, 20);
					g2.setPaint(Color.BLACK);
					g.drawString(b, s1+32, d1+200);
					g2.setPaint(Color.DARK_GRAY);
					g.drawOval(s1+32, d1+200, 20, 20);
					
					
					
					
					
					/*
					g2.setPaint(Color.BLUE);
					g2.setStroke(new BasicStroke(3));
					
					//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
					g2.draw(new Line2D.Float(s1+42, d1+211, s2+42, d2+211));
					*/
					
					long start=System.currentTimeMillis();
					while (System.currentTimeMillis() - start<2000) {}
					System.out.println("next");
					
					
			        }
		        
		       
		        
		        
		        
		        
		    }
		}

		 
		 
		 
		 Bfsdfs ch=new Bfsdfs(count+1);
			
			
		ch.BFS(0, bfs);
				
		jlmxwt.setText("BFS Traversal : "+bfsout);
			



	}
});




/********* fuction for DFS******////

dfsbut.addMouseListener  (new MouseAdapter() 
{
	@Override
	public void mouseClicked(MouseEvent e) 
	
	{	

		jlmxwt.setText("");
		
		 for(int j=0;j<count+1;j++) 
	        {
	        	
	        
	        	int s1 =adjlist[j].get(0);
				int d1 =adjlist[j].get(1);
	        
	        	String b = Integer.toString(j);
				
				 
				Graphics g = getGraphics();
				Graphics2D g2 =(Graphics2D) g;
				//g.drawString(b, x+35, y+205);
				g2.setPaint(Color.green);
				g.fillOval(s1+32, d1+200, 20, 20);
				g2.setPaint(Color.BLACK);
				g.drawString(b, s1+32, d1+200);
				g2.setPaint(Color.DARK_GRAY);
				g.drawOval(s1+32, d1+200, 20, 20);
				
		
	        }
		
		
		
		
		
		
		
		
LinkedList<Integer> dfsout=new LinkedList<>();
		 
		 
int bfsst[]=new int[12];
		
int k=0;
		
		
	class Graph {
			
			int k=0;
		    private int V; // No. of vertices
		  
		    // Array  of lists for
		    // Adjacency List Representation
		   // private LinkedList<Integer> adj[];
		  
		    // Constructor
		    @SuppressWarnings("unchecked") Graph(int v)
		    {
		        V = v;
		       // adj = new LinkedList[v];
		       // for (int i = 0; i < v; ++i)
		          //  adj[i] = new LinkedList();
		    }
		  
		    // Function to add an edge into the graph
		    void addEdge(int v, int w)
		    {
		      //  adj[v].add(w); // Add w to v's list.
		    }
		  
		    // A function used by DFS
		    void DFSUtil(int v, boolean visited[],LinkedList adj[])
		    {
		        // Mark the current node as visited and print it
		        visited[v] = true;
		        System.out.print(v + " ");
		        bfsst[k]=v;
		        String b =Integer.toString(v);
		        dfsout.add(v);
		        k++;
		  
		        // Recur for all the vertices adjacent to this
		        // vertex
		        Iterator<Integer> i = adj[v].listIterator();
		        while (i.hasNext()) {
		            int n = i.next();
		            if (!visited[n])
		                DFSUtil(n, visited,bfs);
		        }
		    }
		  
		    // The function to do DFS traversal. It uses recursive
		    // DFSUtil()
		    void DFS()
		    {
		        // Mark all the vertices as not visited(set as
		        // false by default in java)
		        boolean visited[] = new boolean[V];
		  
		        // Call the recursive helper function to print DFS
		        // traversal starting from all vertices one by one
		        for (int i = 0; i < V; ++i)
		            if (visited[i] == false)
		                DFSUtil(i, visited,bfs);
		    }
		  
		    // Driver Code
		    
		    /*
		    public static void main(String args[])
		    {
		        Graph g = new Graph(4);
		  
		        g.addEdge(0, 1);
		        g.addEdge(0, 2);
		        g.addEdge(1, 2);
		        g.addEdge(2, 0);
		        g.addEdge(2, 3);
		        g.addEdge(3, 3);
		  
		        System.out.println(
		            "Following is Depth First Traversal");
		  
		        g.DFS();
		    }
		    */
		    
		    
		    
		    
		    
	void colour() {	    

			 for(int j=0;j<count+1;j++) 
		        {
		        	System.out.println(bfsst[j]);
		      
		        
		        
		        String b=Integer.toString(bfsst[j]);
		      //x and y positions first vertex
				int s1 =adjlist[bfsst[j]].get(0);
				int d1 =adjlist[bfsst[j]].get(1);
				
				//x and y positions of 2nds vertex
				
				int s2 =adjlist[bfsst[j+1]].get(0);
				int d2 =adjlist[bfsst[j+1]].get(1);
				
				//int midx =((s+42)+(s1+42))/2 ;
				//int midy=+((d+211)+(d1+211))/2;
				
				Graphics g = getGraphics();
				Graphics2D g2 =(Graphics2D)g;
				g2.setPaint(Color.YELLOW);
				g.fillOval(s1+32, d1+200, 20, 20);
				g2.setPaint(Color.BLACK);
				g.drawString(b, s1+32, d1+200);
				g2.setPaint(Color.DARK_GRAY);
				g.drawOval(s1+32, d1+200, 20, 20);
				
				
				
				
				
				/*
				g2.setPaint(Color.BLUE);
				g2.setStroke(new BasicStroke(3));
				
				//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
				g2.draw(new Line2D.Float(s1+42, d1+211, s2+42, d2+211));
				*/
				
				long start=System.currentTimeMillis();
				while (System.currentTimeMillis() - start<2000) {}
				System.out.println("next");
				
				
		        }
		    
		    
		    
		    
	}
		    
		    
		    
		}
		
		 Graph g = new Graph(count+1);
		 g.DFS();
		 g.colour();
				
		
		jlmxwt.setText("DFS Traversal : "+dfsout);
		
		
		
		
		
		
		
		
		
		

	}
});









	
/********fuction for travelling salesman problem*****/
but5.addMouseListener  (new MouseAdapter() 
{
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		{
	    
	    
			   int n = count+1;
			   
			   
			    double[][] distanceMatrix = new double[n][n];
			    
			    for(int i=0;i<n;i++) 
			    {
			    	for(int j=0;j<n;j++) 
			    	{
			    		distanceMatrix[i][j]=distanceMatrix1[i][j];
			    	}
			    }
			    
			    /*
			    for (double[] row: distanceMatrix) java.util.Arrays.fill(row, 10000);
			    distanceMatrix[5][0] = 10;
			    distanceMatrix[1][5] = 12;
			    distanceMatrix[4][1] = 2;
			    distanceMatrix[2][4] = 4;
			    distanceMatrix[3][2] = 6;
			    distanceMatrix[0][3] = 8;
			 */
			    int startNode = 0;
			    TspDynamicProgrammingIterative solver = new TspDynamicProgrammingIterative(startNode, distanceMatrix);

			    // Prints: [0, 3, 2, 4, 1, 5, 0]
			    System.out.println("Tour: " + solver.getTour());
			    
			    int tourArr[]= new int [n+1];
			    for(int k=0;k<n+1;k++) 
			    {
			    	tourArr[k]=solver.getTour().get(k);
			    	System.out.println(solver.getTour().get(k));
			    }
			    
			    double cost=0;
			    
			    for(int m=0;m<tourArr.length;m++) 
			    {
			    	System.out.print(tourArr[m]);
			    	if(m>0) 
			    	{
			    		cost=cost+distanceMatrix[tourArr[m-1]][tourArr[m]];
			    		
			    		double wei=distanceMatrix[tourArr[m-1]][tourArr[m]];
			    		int wei1 = (int) Math.round(wei);
			    		String weit = String.valueOf(wei1);
			    		
			    		int s =adjlist[tourArr[m-1]].get(0);
		        		int d =adjlist[tourArr[m-1]].get(1);
		        		
		        		//x and y positions of 2nd vertex
		        		
		        		int s1 =adjlist[tourArr[m]].get(0);
		        		int d1 =adjlist[tourArr[m]].get(1);
		        		

		        		int midx =((s+42)+(s1+42))/2 ;
		        		int midy=+((d+211)+(d1+211))/2;
		        		
		            	
		            	Graphics g1 = getGraphics();
		        		Graphics2D g3 =(Graphics2D)g1;
		        		
		        		
		        		g3.setPaint(Color.BLUE);
		        		
		        		g3.setStroke(new BasicStroke(3));
		        		
		        		//g2.draw(new Line2D.Float(s+22, d+53, s1+15, d1+53));
		        		g3.draw(new Line2D.Float(s+42, d+211, s1+42, d1+211));
		        		
		        		g3.setPaint(Color.BLACK);
		        		
		        		g3.fillRect(midx-5, midy-8, 15, 15);
		        		g3.setPaint(Color.WHITE);
		        		g3.drawString(weit, midx-2, midy+4);
		        		

			    		j3.setText("Tour cost is "+cost);
			    		
			    		
			    	}
			    }

			    // Print: 42.0
			    System.out.println("Tour cost: " + solver.getTourCost());
			  System.out.println(+cost);
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
			int n =count+2;
			
	        // Creating an object of Scanner class to take user
	        // input
	        // 1. Number of cities
	        // 2. Cost matrix
	      
	        // Declaring variables
	        int i, j, cost;
	        int start=1;
	       
	 
	       
	        if (n == 1) {
	            // Display on the console
	            System.out.println("Path is not possible!");
	 
	            // terminate
	            System.exit(0);
	        }
	 
	        // Case 2
	        // Many cities
	 
	        // Again, reading the cost of the matrix
	 
	        // Display message
	        
	 
	        for (i = 1; i <= n; i++)
	        {
	            tour[i] = i;
	        }
	        // Calling the above Method 1 to
	        Tsp ch = new Tsp();
	        cost = ch.tspdp(c, tour, 0, n);
	 
	        // Now, coming to logic to print the optimal tour
	 
	        
	        
	        for (i=0;i<n;i++) 
	        {
	        	for(j=0;j<n;j++) 
	        	{
	        		System.out.print("  "+c[i][j]);
	        	}
	        	System.out.println();
	        }
	        // Display message for better readability
	        System.out.print("The Optimal Tour is: ");
	 
	        
	        for (i = 1; i <= n; i++)
	        {
	            // Printing across which cities should Salesman
	            // travel
	        	//int s = tour[i]-2; 
	        	                                  //tour[i]
	            System.out.print(tour[i] + "->");
	           // t=tour[i-1];
	            
	            
	            
	        }
	        //cost=cost+c[tour[n]][0];
	        // Starting off with the city 1->
	        System.out.println("0");
	 
	        // Print and display the (minimum)cost of the path
	        // traversed
	        System.out.println("Minimum Cost: " + cost);
	        
	        
	        */
	    }
		
	
	}
});

	// refresh button
	but4.addMouseListener  (new MouseAdapter() 
	{
		@Override
		public void mouseClicked(MouseEvent e) 
		{
		
			
			SwingUtilities.updateComponentTreeUI(pan1);
		    
			count = -1;
			
			for(int k=0;k<10;k++)
			{
				for(int l=0;l<10;l++) 
				{
					adj[k][l]=false;
					array[k][l]=0;
					cost1[k][l]=0;
					ar[k][l]=0;
					c[k][l]=0;
					c[l][k]=0;
					
					distanceMatrix1[k][l]=0;
					System.out.print(adj[k][l]+" ");
				}
				System.out.println("");
			}
			
			 
				System.out.println();
		
			jl.setText(" ");
	        j2.setText(" ");
			j3.setText("");
			jLprot.setText("");
			jlmxwtot.setText("");
			jlmxwt.setText("");
	       
		}
	});
}



public void mouseClicked(MouseEvent e) 
{
	//setSize(400,400);
/*	count++;
	String b = Integer.toString(count);
	{
	x=e.getX();
	y=e.getY();
	byte in[]= new byte[4];
	Graphics g = getGraphics();
	g.drawOval(x, y, 20, 20);
	g.drawString(b, x, y-1);
	
	}
	/*adj[count][count]=1;
	
	System.out.println(+count);
	
	
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++) {
			
			System.out.print(adj[i][j]+" ");
		}
		System.out.println("");
	}*/
	
	//g.fillOval(x, y, 6, 6);
}



@Override
public void mousePressed(MouseEvent e) 
{

	
	
}


@Override
public void mouseReleased(MouseEvent e) 
{
	// TODO Auto-generated method stub
	
}


@Override
public void mouseEntered(MouseEvent e)
{
	// TODO Auto-generated method stub
	
}


@Override
public void mouseExited(MouseEvent e) 
{
	// TODO Auto-generated method stub
	
}
}

/***setting titleto combobox***/


