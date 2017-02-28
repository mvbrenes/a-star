package model;

import java.awt.Color;
import java.util.Comparator;

import view_objects.Square;

public class Node implements Comparable<Node> {

	private Square square;
	private boolean startNode = false, endNode = false, path;
	boolean inOpenList = false;

	int F, G, H;
	Node parent = null;

	public Node(Square square) {
		this.square = square;
	}

	public boolean isUnpathable() {
		return square.isUnpathable();
	}

	public void setUnpathable(boolean b) {
		square.setUnpathable(b);
	}

	public Square getSquare() {
		return square;
	}

	public boolean isStartNode() {
		return startNode;
	}

	public boolean setStartNode(boolean startNode) {
		if (!isUnpathable()) {
			this.startNode = startNode;
			if (startNode)
				square.setColor(Color.green);
			return true;
		} else
			return false;
	}

	public boolean isEndNode() {
		return endNode;
	}

	public boolean setEndNode(boolean endNode) {
		if (!isUnpathable()) {
			this.endNode = endNode;
			if (endNode)
				square.setColor(Color.red);
			return true;
		} else
			return false;
	}

	public boolean isPath() {
		return path;
	}

	public void setPath(boolean path) {
		this.path = path;
		if (path && !isEndNode() && !isStartNode()) {
			square.setColor(Color.yellow);
		}
	}

	public int compareTo(Node o) {
		if (getF() < o.getF())
			return -1;
		if (getF() == o.getF())
			return 0;
		if (getF() > o.getF())
			return 1;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((square == null) ? 0 : square.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (square == null) {
			if (other.square != null)
				return false;
		} else if (!square.equals(other.square))
			return false;
		return true;
	}

	public int getF() {
		return F;
	}

	public void setF() {
		F = getG() + getH();
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
		setF();
	}

	public int getH() {
		return H;
	}

	public int getId() {
		return square.getId();
	}

	public void setH(Node node) {
		int x = node.getSquare().getColoum();
		int y = node.getSquare().getRow();

		x -= this.getSquare().getColoum();
		y -= this.getSquare().getRow();

		H = x + y;
	}

	@Override
	public String toString() {
		return "Node" + square.getId() + " F=" + F + ", G=" + G + ", H=" + H;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean isInOpenList() {
		return inOpenList;
	}

	public void setInOpenList(boolean inOpenList) {
		this.inOpenList = inOpenList;
	}
}
