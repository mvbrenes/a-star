package view_objects;

import javax.swing.JPanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


import model.Node;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import controller.ViewController;



@SuppressWarnings("serial")
public class Panel extends JPanel{

	JLabel title, dialogLabel;
	Node[][] nodes;
	Square[][] squares;
	
	boolean startNodeSelected = false;
	boolean endNodeSelected = false;
	
	Node startNode, endNode;
	
	public Panel() {
		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.gray);
		setLayout(null);
		
		JLabel lblAGui = new JLabel("A* GUI");
		lblAGui.setHorizontalAlignment(SwingConstants.CENTER);
		lblAGui.setBounds(10, 5, 580, 29);
		lblAGui.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(lblAGui);
		
		JLabel lblNewLabel = new JLabel("Click Starting White Node");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 45, 580, 14);
		add(lblNewLabel);
		
	
	}
	
	public Panel(Node[][] nodes) {
		
		this.nodes = nodes;
		//squares = new ArrayList<Square>();
		squares = new Square[15][15];
		
		for(int x = 0; x < 15; x++)
			for (int y = 0; y < 15; y++)
				squares[x][y] = nodes[x][y].getSquare();
		
		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.gray);
		setLayout(null);
		
		title = new JLabel("A* GUI");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 5, 580, 29);
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(title);
		
		dialogLabel = new JLabel("Click Start Node");
		dialogLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dialogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dialogLabel.setBounds(10, 45, 580, 14);
		add(dialogLabel);
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				x -= 60;
				y -= 105;
				
				if (!startNodeSelected) {
					
					// Checks to see if the click was within the container
					if (x >= 0 && x <= 480 && y >= 0 && y < 480) {
						int row = y / 32;
						int column = x /32;
						
						if (setStartNode(column, row)) {
							repaint();
							startNodeSelected = true;
							dialogLabel.setText("Click End Node");
						}
					}
				} else if (!endNodeSelected) {
					// Checks to see if the click was within the container
					if (x >= 0 && x <= 480 && y >= 0 && y < 480) {
						int row = y / 32;
						int column = x /32;
						
						if (!isStartNode(column, row)) {
							if(setEndNode(column, row)) {

							repaint();
							endNodeSelected = true;
							startAStar();
							repaint();
							}
						}
					}
				}

			}
		});
		
	}
	
	public boolean setStartNode(int x, int y) {
		startNode = nodes[x][y];
		return ViewController.setStartNode(nodes[x][y]);	
	}
	
	public boolean setEndNode(int x , int y) {
		endNode = nodes[x][y];
		return ViewController.setEndNode(nodes[x][y]);	
	}
	
	public void startAStar() {
		if (ViewController.startAStar(nodes, startNode, endNode))
			dialogLabel.setText("FOUND PATH!");
		else
			dialogLabel.setText("NO POSSIBLE PATH FOUND");
	}
	
	public boolean isStartNode(int x , int y) {
		boolean b = nodes[x][y].isStartNode();
		return b;
	}
	public void changeDisplay(int x, int y) {
		dialogLabel.setText("X: " + x + " Y: " + y);
	}
	public void changeMore(int r, int c) {
		dialogLabel.setText(dialogLabel.getText() + 
				" Row: " + r + " Col: " + c);
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		for(int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				squares[i][j].draw(graphics);
		
		graphics.setColor(Color.black);
		graphics.drawRect(60, 105, 480, 480);
		
		
		int x = 92;
		int y = 105;
		for (int i = 0; i < 15; i++) {
			graphics.drawLine(x, 105 , x, 105 + 480);
			graphics.drawLine(60, y , 60 + 480, y);
			x += 32;
			y += 32;
		}
		
		
		
		
	}


}

