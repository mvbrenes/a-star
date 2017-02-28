package model;

import java.util.HashMap;

public class AStar {

	public static boolean findPath(Node[][] nodes, Node startNode, Node endNode) {
		MinHeap openList = new MinHeap();
		HashMap<Integer, Node> closedList = new HashMap<Integer, Node>();
		
		// Set Heuristics for all nodes
		for (int i = 0; i < nodes.length; i++)
			for (int j = 0; j <nodes.length; j++)
				nodes[i][j].setH(endNode);
		
		
		//add starting node to openlist
		openList.add(startNode);
		
		boolean keepSearching = true;
		
		while(keepSearching) {
			
			Node current;
			
			// pop best move off of open list
			if (openList.size() == 0) {
				keepSearching = false;
				// No path exists
				current = null;
				return false;
			} else
				current = (Node) openList.remove();
			
			// check for goal
			if (current.equals(endNode)) {
				// Fount the path
				Node n = current;
				while(n.getParent() != null) {
					n.getParent().setPath(true);
					n = n.getParent();
				}
				keepSearching = false;
			} else {
				// Search neighbors
				closedList.put(current.getId(), current);
				
				int curRow = current.getSquare().getRow();
				int curCol = current.getSquare().getColoum();
				
				for(int r = curRow - 1; r <= curRow + 1; r++) {
					for(int c = curCol - 1; c <= curCol + 1; c++) {
						
						if(!(r < 0 || c < 0 
							|| r > nodes.length - 1 || c > nodes[0].length - 1)) {
							// In bounds
							if (!nodes[c][r].isUnpathable()) {
								// pathable
								Node n = nodes[c][r];
								if (!closedList.containsKey(n.getId())) {
									// not in closed list
									int g = 14;
									if (r == curRow || c == curCol)
										g = 10;
									g += current.getG();
									
									if (!n.inOpenList) {
										// not in openlist
										n.setG(g);
										n.setParent(current);
										n.setInOpenList(true);
										openList.add(n);
									} else {
										if (g < n.getG()) {
											n.setG(g);
											n.setParent(current);
										}
									}
								}
							}
							
						}
					}
				}
			}
		}
		
		return true;
	}
}
