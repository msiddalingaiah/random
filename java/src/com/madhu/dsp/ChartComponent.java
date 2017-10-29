
package com.madhu.dsp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class ChartComponent extends JComponent {
	private static final int TOP_MARGIN = 10;
	private static final int LEFT_MARGIN = 35;
	private static final int BOTTOM_MARGIN = 10;
	private static final int RIGHT_MARGIN = 10;
	private int npoints;
	private Source input;
	private double minimum;
	private double maximum;
	private double scale;
	private Dimension size;
	private double[] data;
	private boolean computed;

	public ChartComponent(int npoints, Source input, double minimum, double maximum, int height) {
		this.npoints = npoints;
		this.input = input;
		size = new Dimension(npoints+LEFT_MARGIN+RIGHT_MARGIN, height);
		data = new double[npoints];
		computed = false;
		setBackground(Color.BLACK);
		setForeground(Color.GREEN);
		this.minimum = minimum;
		this.maximum = maximum;
		this.scale = (height-(TOP_MARGIN+BOTTOM_MARGIN)) / (maximum - minimum);
	}

	public ChartComponent(int npoints, Source input, double minimum, double maximum) {
		this(npoints, input, minimum, maximum, 200);
	}

	@Override
	public Dimension getPreferredSize() {
		return size;
	}

	@Override
	public Dimension getMaximumSize() {
		return size;
	}

	@Override
	public Dimension getMinimumSize() {
		return size;
	}

	public void repaint() {
		input.reset();
		computed = false;
		super.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		try {
			if (computed == false) {
				for (int i = 0; i < npoints; i++) {
					data[i] = input.getValue();
				}
				computed = true;
			}
			g.setColor(getBackground());
			int graphWidth = getWidth() - (LEFT_MARGIN + RIGHT_MARGIN);
			int graphHeight = getHeight() - (TOP_MARGIN + BOTTOM_MARGIN);
			g.fillRect(0, 0, getWidth(), getHeight());
			double x1 = LEFT_MARGIN;
			double y1 = TOP_MARGIN + graphHeight;
			double dy = graphHeight / 10.0;
			g.setColor(Color.GRAY);
			double nScale = (maximum-minimum) / 10;
			for (int i=0; i<11; i+=1) {
				g.drawString(String.format("%4.1f", minimum + (nScale*i)), 5, (int) y1+5);
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

			double graphBase = getHeight() - BOTTOM_MARGIN;
			x1 = LEFT_MARGIN;
			y1 = (int) (graphBase - scale * (0 - minimum));
			g.setColor(Color.RED);
			g.drawLine((int) x1, (int) y1, getWidth()-RIGHT_MARGIN, (int) y1);
			y1 = graphBase - scale * (data[0] - minimum);
			g.setColor(getForeground());
			for (int i = 1; i < npoints; i++) {
				double x2 = i+LEFT_MARGIN;
				double y2 = graphBase - scale * (data[i] - minimum);
				g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
				x1 = x2;
				y1 = y2;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
