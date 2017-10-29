
package com.madhu.laplace;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphPanel extends JPanel implements MouseMotionListener {
	private Graph graph;
	private JLabel statusLabel;

	public GraphPanel() {
		setLayout(new BorderLayout());
		graph = new Graph();
		add(graph, BorderLayout.CENTER);
		statusLabel = new JLabel("0, 0");
		add(statusLabel, BorderLayout.SOUTH);
		graph.addMouseMotionListener(this);
	}

	public void graph(double[] d, double tmax) {
		graph.graph(d, tmax);
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		statusLabel.setText(String.format("(%d) %.2f, (%d) %.2f",
				e.getX(), graph.pixelsToX(e.getX()), e.getY(), graph.pixelsToY(e.getY())));
	}
}
