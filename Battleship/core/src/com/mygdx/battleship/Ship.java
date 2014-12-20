package com.mygdx.battleship;

import com.badlogic.gdx.math.Vector2;

public class Ship {
	private int width;
	private int height;
	private int bottomLeftX;
	private int bottomLeftY;
	private int topRightX;
	private int topRightY;
	public int health;
	private boolean placed;
	public Ship(int w, int h){
		width = w;
		height = h;
		setLocation(-1, -1);
		health = w * h;
		placed = false;
	}
	public void setLocation(int x, int y){
		if(x == -1 || y == -1){
			bottomLeftX = -1;
			bottomLeftY = -1;
			topRightX = -1;
			topRightY = -1;
			placed = false;
		}
		else{
			bottomLeftX = x;
			bottomLeftY = y;
			topRightX = x+width;
			topRightY = y+height;
			placed = true;
		}
	}
	public void changeOrientation(){
		int temp = width;
		width = height;
		height = temp;
	}
	public boolean isPlaced(){
		return placed;
	}
	public int getX(){
		return bottomLeftX;
	}
	public int getY(){
		return bottomLeftY;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public String toString(){
		return "Ship:{" +
		"\n		bottomLeft: ("+bottomLeftX+","+bottomLeftY+")"+
		"\n		bottomLeft: ("+topRightX+","+topRightY+")"+
		"\n		dimensions: ("+width+","+height+")"+
		"\n		placed: "+placed+
		"\n		health: "+health;
	}
}
