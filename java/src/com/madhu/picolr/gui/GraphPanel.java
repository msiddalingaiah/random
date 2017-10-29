
package com.madhu.picolr.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import com.madhu.picolr.Tree;

public class GraphPanel extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener {
	private Node2D root;
	private AffineTransform at;
	private boolean firstTime = true;
	private int startX;
	private int startY;

	public GraphPanel(Tree t) {
		root = new Node2D(t, 0, 0, 750);
		at = new AffineTransform();
		at.setToIdentity();
		addMouseWheelListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (Transform.no2D) {
			return;
		}

		if (firstTime) {
			Dimension d = getSize();
			int w = d.width;
			int h = d.height;
			at.translate(w / 2, 50);
			firstTime = false;
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform saveXform = g2.getTransform();
		g2.transform(at);
		root.draw(g2);
		g2.setTransform(saveXform);
	}

	public void mouseWheelMoved(MouseWheelEvent mwe) {
		double factor = 1.0 - (mwe.getWheelRotation() / 10.0);
		at.scale(factor, factor);
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		this.startX = e.getX();
		this.startY = e.getY();
	}

	public void mouseDragged(MouseEvent e) {
		at.translate(e.getX() - startX, e.getY() - startY);
		this.startX = e.getX();
		this.startY = e.getY();
		repaint();
	}

	public void mouseMoved(MouseEvent me) { }
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
}
