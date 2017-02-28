package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AStar;
import model.MinHeap;
import model.Node;

import view_objects.Panel;
import view_objects.Square;

public class ViewController {
	
	Node[][] nodesArray;
	JFrame panel;
	
	public ViewController(Node[][] nodesArray, JFrame panel) {
		this.nodesArray = nodesArray;
		this.panel = panel;
	}
	
	public void generateObstacles() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Node> nodes = new ArrayList<Node>();

		for(int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				nodes.add(nodesArray[j][i]);
		
		for (int i = 0; i < 225; i++) {
			list.add(new Integer(i));
		}
		
		Collections.shuffle(list);
		
		for (int i = 0; i < 23; i++) {
			nodes.get(list.get(i)).setUnpathable(true);
		}
	}
	
	public static boolean startAStar(Node[][] nodes, Node s, Node e) {
		return AStar.findPath(nodes, s, e);
	}
	
	
	public static boolean setStartNode(Node node) {
		return node.setStartNode(true);
	}
	
	public static boolean setEndNode(Node node) {
		return node.setEndNode(true);
	}
}
