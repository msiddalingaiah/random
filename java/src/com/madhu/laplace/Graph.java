
package com.madhu.laplace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Graph extends JComponent {
	private static final int INSET = 5;
	private double data[];
	private double maxY, minY;
	private double xOffset;
	private double xScale;
	private double yOffset;
	private double yScale;
	private double tmax;

	public Graph() {
		data = new double[5];
		for (int i = 0; i < 5; i += 1) {
			data[i] = i * i;
		}
		setBackground(Color.BLACK);
		setForeground(Color.GREEN);
		xOffset = 6.0;
	}

	public void graph(double d[], double tmax) {
		this.tmax = tmax;
		data = d;
		minY = Double.MAX_VALUE;
		maxY = Double.MIN_VALUE;
		for (int i = 0; i < data.length; i += 1) {
			if (d[i] > maxY) {
				maxY = d[i];
			}
			if (d[i] < minY) {
				minY = d[i];
			}
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Color.GRAY);
		g.drawRect(INSET, INSET, d.width - 2*INSET, d.height - 2*INSET);
		xOffset = INSET+1;
		xScale = (d.getWidth() - (INSET+2)) / (tmax - 0.0);
		yOffset = d.getHeight() - (INSET+1);
		yScale = (d.getHeight() - (2*INSET+2)) / (maxY - minY);
		if (minY < 0.0 && maxY > 0.0) {
			int iy0 = yToPixels(0.0);
			g.setColor(Color.RED);
			g.drawLine(INSET+1, iy0, d.width - (INSET+1), iy0);
		}
		g.setColor(getForeground());
		double x = 0.0;
		int x1 = xToPixels(x);
		int y1 = yToPixels(data[0]);
		double dx = tmax / data.length;
		x += dx;
		for (int i=1; i<data.length; i+=1) {
			int x2 = xToPixels(x);
			int y2 = yToPixels(data[i]);
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
			x += dx;
		}
	}
	
	private int xToPixels(double x) {
		return (int) (xScale * (x - 0.0) + xOffset);
	}

	private int yToPixels(double y) {
		return (int) (yOffset - yScale * (y - minY));
	}
	
	public double pixelsToX(int x) {
		return 0.0 + (x - xOffset) / xScale;
	}

	public double pixelsToY(int y) {
		return minY + (yOffset - y) / yScale;
	}
}
