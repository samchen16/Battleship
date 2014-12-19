package com.mygdx.battleship;

public class Point {
	public int x;
	public int y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(int xx, int yy) {
		x = xx;
		y = yy;
	}
	
	public boolean equals(Object other) {
		Point p = (Point) other;
		return (x == p.x && y == p.y);
	}
}