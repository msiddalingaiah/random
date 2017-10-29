
/*
 * Created on Nov 20, 2004 3:00:10 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;

import com.madhu.minc.hir.Block;
import com.madhu.minc.hir.Quad;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class GraphicsBlock {
	private boolean selected;
	private int left, top;
	private Block block;
	private Font blockFont;
	private Font quadFont;
	private int width;
	private int height;
	private int xOffset;
	private int yOffset;

	public GraphicsBlock(Block block) {
		this.block = block;
		this.blockFont = new Font("Monospaced", Font.BOLD, 11);
		this.quadFont = new Font("Monospaced", Font.PLAIN, 11);
		int lineHeight = quadFont.getSize() + 2;
		height = lineHeight * (block.getQuads().size() + 2);
		width = 50;
		int charWidth = 8;
		Iterator it = block.getQuads().iterator();
		while (it.hasNext()) {
			Quad q = (Quad) it.next();
			int len = q.toString().length() * charWidth;
			if (len > width) {
				width = len;
			}
		}
	}
	
	public void paint(Graphics g) {
		if (selected) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(left, top, width, height);
		g.setColor(Color.RED);
		g.drawRect(left, top, width, height);
		g.setColor(Color.BLACK);
		int leftMargin = left+5;
		int lineY = top+12;
		g.setFont(blockFont);
		g.drawString(block.getName(), leftMargin + ((width>>1) - 8), lineY);

		g.setFont(quadFont);
		lineY += 20;
		Iterator it = block.getQuads().iterator();
		while (it.hasNext()) {
			Quad q = (Quad) it.next();
			g.drawString(q.toString(), leftMargin, lineY);
			lineY += 12;
		}
	}

	public boolean isAbove(GraphicsBlock gb) {
		return getBottom() < gb.getTop();
	}
	
	public boolean isBelow(GraphicsBlock gb) {
		return getTop() > gb.getBottom();
	}
	
	public boolean isLeftOf(GraphicsBlock gb) {
		return getRight() < gb.getLeft();
	}
	
	public boolean isRightOf(GraphicsBlock gb) {
		return getLeft() > gb.getRight();
	}
	
	/**
	 * @param i
	 * @param j
	 */
	public void setPosition(int left, int top) {
		this.left = left;
		this.top = top;
	}

	/**
	 * @return
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * @return
	 */
	public int getTop() {
		return top;
	}

	public int getRight() {
		return left + width;
	}

	public int getBottom() {
		return top + height;
	}

	/**
	 * @param i
	 */
	public void setLeft(int x) {
		this.left = x;
	}

	/**
	 * @param i
	 */
	public void setTop(int y) {
		this.top = y;
	}

	/**
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInside(int x, int y) {
		boolean inside = x > this.left && (x < this.left + width) &&
			y > this.top && y < (this.top + height);
		if (inside) {
			xOffset = this.left - x;
			yOffset = this.top - y;
		}
		return inside;
	}

	/**
	 * @param b
	 */
	public void setSelected(boolean flag) {
		this.selected = flag;
	}

	/**
	 * @param i
	 * @param j
	 */
	public void drag(int x, int y) {
		this.left = x + xOffset;
		this.top = y + yOffset;
	}
}
