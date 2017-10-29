
/*
 * Created on Nov 20, 2004 2:56:48 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import com.madhu.minc.hir.Block;
import com.madhu.minc.hir.ControlFlowGraph;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class CFGPanel extends JPanel implements MouseListener, MouseMotionListener {
	private ControlFlowGraph cfg;
	private HashMap gBlocks;
	private GraphicsBlock selectedBlock;
	
	public CFGPanel(ControlFlowGraph cfg) {
		this.cfg = cfg;
		gBlocks = new HashMap();
		Block b = cfg.getStartBlock();
		while (b != null) {
			gBlocks.put(b.getName(), new GraphicsBlock(b));
			b = b.getNextBlock();
		}
		preOrder(cfg.getStartBlock());
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/**
	 * @param block
	 */
	private void preOrder(Block start) {
		GraphicsBlock gb0 = (GraphicsBlock) gBlocks.get(start.getName());
		gb0.setPosition(20, 20);
		int width = gb0.getWidth();
		int height = gb0.getTop() + gb0.getHeight();
		Block b = start.getNextBlock();
		while (b != null) {
			GraphicsBlock gb1 = (GraphicsBlock) gBlocks.get(b.getName());
			gb1.setPosition(gb0.getLeft(), gb0.getTop() + gb0.getHeight() + 20);
			gb0 = gb1;
			b = b.getNextBlock();
			int w = gb0.getWidth();
			if (w > width) {
				width = w;
			}
			height = gb0.getTop() + gb0.getHeight();
		}
		setPreferredSize(new Dimension(width + 40, height + 40));
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
	public void paintChildren(Graphics g) {
		Block b = cfg.getStartBlock();
		while (b != null) {
			GraphicsBlock gb = (GraphicsBlock) gBlocks.get(b.getName());
			g.setColor(Color.BLUE);
			List list = b.getSuccessors();
			int n = list.size();
			for (int i = 0; i < n; i++) {
				Block succ = (Block) list.get(i);
				drawArrow(g, gb, succ);
			}
			b = b.getNextBlock();
		}
		b = cfg.getStartBlock();
		while (b != null) {
			GraphicsBlock gb = (GraphicsBlock) gBlocks.get(b.getName());
			gb.paint(g);
			b = b.getNextBlock();
		}
	}

	/**
	 * @param g
	 * @param gb
	 * @param block
	 */
	private void drawArrow(Graphics g, GraphicsBlock gb1, Block succ) {
		if (succ == null) {
			return;
		}
		GraphicsBlock gb2 = (GraphicsBlock) gBlocks.get(succ.getName());

		int tail = 5;
		int arrowSize = 6;

		int x1 = (gb1.getLeft() + gb1.getRight()) / 2;
		int xb = (gb2.getLeft() + gb2.getRight()) / 2;
		x1 = (x1 + xb) / 2;

		if (x1 < gb1.getLeft()+5) {
			x1 = gb1.getLeft() + 5;
		}
		if (x1 > gb1.getRight()-5) {
			x1 = gb1.getRight() - 5;
		}
		if (gb1.isAbove(gb2)) {			
			int y1 = gb1.getBottom();

			int x2 = x1;
			if (x2 < gb2.getLeft()+5) {
				x2 = gb2.getLeft()+5;
			}
			if (x2 > gb2.getRight()-5) {
				x2 = gb2.getRight()-5;
			}
			int y2 = gb2.getTop() - (arrowSize + tail);
			g.drawLine(x1, y1, x2, y2);

			g.drawLine(x2, y2+(arrowSize+tail), x2, y2);
			g.drawLine(x2, y2+(arrowSize+tail), x2-(arrowSize >> 1), y2+tail);
			g.drawLine(x2, y2+(arrowSize+tail), x2+(arrowSize >> 1), y2+tail);
		}
		if (gb1.isBelow(gb2)) {
			int y1 = gb1.getTop();

			int x2 = x1;
			if (x2 < gb2.getLeft()+5) {
				x2 = gb2.getLeft()+5;
			} else if (x2 > gb2.getRight()-5) {
				x2 = gb2.getRight()-5;
			}

			int y2 = gb2.getBottom() + (arrowSize + tail);
			g.drawLine(x1, y1, x2, y2);

			g.drawLine(x2, y2-(arrowSize+tail), x2, y2);
			g.drawLine(x2, y2-(arrowSize+tail), x2-(arrowSize >> 1), y2-tail);
			g.drawLine(x2, y2-(arrowSize+tail), x2+(arrowSize >> 1), y2-tail);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		if (selectedBlock != null) {
			selectedBlock.setSelected(false);
			selectedBlock = null;
		}
		int x = e.getX();
		int y = e.getY();
		Iterator it = gBlocks.values().iterator();
		while (it.hasNext()) {
			GraphicsBlock gb = (GraphicsBlock) it.next();
			if (gb.isInside(x, y)) {
				selectedBlock = gb;
				gb.setSelected(true);
				break;
			}
		}
		repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent e) {
		if (selectedBlock != null) {
			selectedBlock.drag(e.getX(), e.getY());
			repaint();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {
	}
}
