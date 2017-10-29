
package com.madhu.picolr.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.madhu.picolr.Tree;

public class Node2D {
	private static final int FONT_SIZE = 14;
	private double x, y;
	private ArrayList<Node2D> nodes;
	private TextLayout textTl;
	final static BasicStroke stroke = new BasicStroke(4.0f, 
            BasicStroke.CAP_ROUND, 
            BasicStroke.JOIN_MITER, 
            10.0f);

	public Node2D(Tree tree, double x, double y, double width) {
		String str = tree.getValue().toString();
		if (str.length() == 0) {
			str = "(null)";
		}
		textTl = new TextLayout(str, new Font("Helvetica", 1, FONT_SIZE),
			new FontRenderContext(null, false, false));
		this.x = x;
		this.y = y;
		int n = tree.size();
		nodes = new ArrayList<Node2D>(n);
		double dx = width / n;
		double x0 = x - dx * n/2.0;
		for (int i = 0; i < n; i++) {
			nodes.add(new Node2D(tree.get(i), x0 + (i+0.5)*dx, y + 50.0, dx));
		}
	}
	
	public void draw(Graphics2D g2) {
		Rectangle2D textBounds = textTl.getBounds();
		double width = textBounds.getWidth();
		double height = textBounds.getHeight();
		for (Node2D node : nodes) {
			Line2D.Double line = new Line2D.Double(node.getX(), node.getY()-FONT_SIZE+2, x, y+FONT_SIZE-2);
			g2.setStroke(stroke);
			g2.setColor(Color.RED);
			g2.draw(line);
			node.draw(g2);
		}
		g2.setColor(Color.BLUE);
		textTl.draw(g2, (float) (x-width/2), (float) (y+height/2));
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
