package me.barthelemy.com.examples.tag;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle {
	
	private int xPos, xVel, yPos, yVel, width, height, upKey, leftKey, rightKey, downKey;
	private int initxPos, inityPos;
	private KeyboardManager keyManager;
	private Color color;
	
	private int windowHeight, windowWidth;
	private boolean up = false, down = false, right = false, left = false;
	private boolean hasTNT = false;
    private boolean dead = false;
	
	

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Rectangle(int xPos, int yPos, Color color, int width, int height, 
			int upKey, int leftKey, int downKey, int rightKey, KeyboardManager keyManager,
			int windowWidth, int windowHeight) {
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.color = color;
		this.xPos = xPos;
		this.yPos = yPos;
		this.initxPos = xPos;
		this.inityPos = yPos;
		this.height = height;
		this.width = width;
		this.upKey = upKey;
		this.downKey = downKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.keyManager = keyManager;
		xVel = 0;
		yVel = 0;
		
	}
	
	public void render(Graphics g) {
		if(dead) return;
		g.setColor(color);
		if(hasTNT) g.setColor(Color.RED);
		g.fillRect(xPos, yPos, width, height);
	}
	
	public void tick() {
		updateBooleans();
		updatePosition();
	}

	private void updateBooleans() {
		up = keyManager.getKeyDown(upKey);
		down = keyManager.getKeyDown(downKey);
		left = keyManager.getKeyDown(leftKey);
		right = keyManager.getKeyDown(rightKey);	
	}

	private void updatePosition() {
		if(up & !(Math.abs(yVel) > 15)) yVel -= 3;
		if(down & !(Math.abs(yVel) > 15)) yVel += 3;
		if(left & !(Math.abs(xVel) > 15)) xVel -= 3;
		if(right & !(Math.abs(xVel) > 15)) xVel += 3;
		
		if(yVel > 0) yVel -= 1;
		if(yVel < 0) yVel += 1;
		if(xVel > 0) xVel -= 1;
		if(xVel < 0) xVel += 1;
		
		xPos += xVel;
		yPos += yVel;
		
		boundaryCorrections();
	}
	
	

	public boolean checkColide(Rectangle other) {
		int xMin = xPos;
		int xMax = xPos + width;
		int yMin = yPos;
		int yMax = yPos + height;
		
		int xMin2 = other.xPos;
		int xMax2 = other.xPos + other.width;
		int yMin2 = other.yPos;
		int yMax2 = other.yPos + other.height;
		boolean colide = false;
		
		if(xMin < xMax2 && xMin > xMin2) {
			if(yMin < yMax2 && yMin > yMin2) {
				return true;
			}
			if(yMin2 < yMax && yMin2 > yMin) {
				return true;
			}
		}
	
		
		if(xMin2 < xMax && xMin2 > xMin) {
			if(yMin < yMax2 && yMin > yMin2) {
				return true;
			}
			if(yMin2 < yMax && yMin2 > yMin) {
				return true;
			}
		}
	
		
		return colide;
	}

	private void boundaryCorrections() {
		if(xPos < 0) {
			xPos = 3;
			xVel = 3;
		}
		if(xPos > windowWidth - width) {
			xPos = windowWidth - width;
			xVel = -3;
		}
		if(yPos < 0) {
			yPos = 3;
			yVel = 3;
		}
		if(yPos > windowHeight - height) {
			yPos = windowHeight - height;
			yVel = -3;
		}
	}

	public boolean hasTNT() {
		return hasTNT;
	}

	public void setHasTNT(boolean hasTNT) {
		this.hasTNT = hasTNT;
	}

	public void resetPosition() {
		xPos = initxPos;
		yPos = inityPos;
	}

	

}

