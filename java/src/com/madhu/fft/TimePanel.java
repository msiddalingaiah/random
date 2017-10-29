/*
 * Created on Jun 16, 2003
 *
 * Copyright (c) 2003 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.fft;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class TimePanel extends JPanel {
	private static final long serialVersionUID = -7995144389556658704L;

	private static final int TOP_MARGIN = 10;
	private static final int LEFT_MARGIN = 35;
	private static final int BOTTOM_MARGIN = 10;
	private static final int RIGHT_MARGIN = 10;

	protected double[] data;
	protected double refLevel;

	public TimePanel() {
		setPreferredSize(new Dimension(400, 175));
		setBackground(Color.BLACK);
		setForeground(Color.GREEN);
		refLevel = 0.589;	// Volts
	}

	public void setData(double[] samples) {
		this.data = samples;
		repaint();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		g.setColor(getBackground());
		int graphWidth = getWidth() - (LEFT_MARGIN + RIGHT_MARGIN);
		int graphHeight = getHeight() - (TOP_MARGIN + BOTTOM_MARGIN);
		g.fillRect(0, 0, getWidth(), getHeight());
		double x1 = LEFT_MARGIN;
		double y1 = TOP_MARGIN + graphHeight;
		double dy = graphHeight / 10.0;
		g.setColor(Color.GRAY);
		for (int i=0; i<11; i+=1) {
			g.drawString(Integer.toString(-120 + (10*i)), 5, (int) y1+5);
			g.drawLine((int) x1, (int) y1, (int) (x1+graphWidth), (int) y1);
			y1 -= dy;
		}
		x1 = LEFT_MARGIN;
		y1 = TOP_MARGIN + graphHeight;
		double dx = graphWidth / 22.0;
		for (int i=0; i<23; i+=1) {
			g.drawLine((int) x1, (int) y1, (int) x1, (int) (y1-graphHeight));
			x1 += dx;
		}

		if (data != null) {
			g.setColor(getForeground());
			x1 = LEFT_MARGIN;
			y1 = TOP_MARGIN + graphHeight;
			int n = data.length / 2;
			dx = ((double) graphWidth) / (double) n;
			dy = graphHeight / 2.0;
			//double ln10 = Math.log(10.0);
			//double logFactor = 1.0/6.0;
			for (int i=0; i<n; i+=1) {
				double x2 = LEFT_MARGIN + (i * dx);
				double d = data[i];
				double yval = d;
				double y2 = TOP_MARGIN + dy - (dy * yval);
				if (i == 0) {
					y1 = y2;
				}
				g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
				x1 = x2;
				y1 = y2;
			}
			g.setColor(Color.RED);
		}
	}
}
