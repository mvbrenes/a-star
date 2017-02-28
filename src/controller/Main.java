package controller;

import javax.swing.JFrame;

import model.Node;

import view_objects.Panel;
import view_objects.Square;


public class Main {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("AStar");
		//ArrayList<Node> nodes = new ArrayList<Node>();
		Node[][] nodes = new Node[15][15];
		
		for (int x = 0; x < 15; x++)
			for (int y = 0; y < 15; y++)
			//nodes.add(new Node(new Square(i)));
				nodes[x][y] = new Node(new Square(x, y));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Panel(nodes));
		frame.pack();
		
		ViewController viewController = new ViewController(nodes, frame);
		
		viewController.generateObstacles();
		
		frame.repaint();
		frame.setVisible(true);
		
	}
}	
