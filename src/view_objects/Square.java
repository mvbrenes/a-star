package view_objects;

import java.awt.Color;
import java.awt.Graphics;

public class Square {
	
	private Color color;
	
	private int row, coloum;
	private int id;
	
	boolean unpathable = false;
	
	
	public Square(int id) {
		this.id = id;
		setRowColumn();
		setColor(Color.white);
	}
	
	public Square(int x, int y) {
		row = y;
		coloum = x;
		id = (row * 15) + coloum;
		setColor(Color.white);
	}
	
	public void setRowColumn() {
		row = id / 15;
		coloum = id % 15;
	}
	
	public boolean isUnpathable() {
		return unpathable;
	}
	public void setUnpathable(boolean unpathable) {
		this.unpathable = unpathable;
		if (unpathable)
			setColor(Color.blue);
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(getX(), getY(), 32, 32);
	}
	
	private int getX() {
		return (coloum * 32) + 60;
	}
	
	private int getY() {
		return (row * 32) + 105;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getRow() {
		return row;
	}
	public int getColoum() {
		return coloum;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coloum;
		result = prime * result + row;
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
		Square other = (Square) obj;
		if (coloum != other.coloum)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}
