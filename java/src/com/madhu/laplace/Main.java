
package com.madhu.laplace;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {
	private JTextField numOrder, denOrder, time;
	private JButton numEdit, denEdit, doIt;
	private CoefficientDialog numerator, denominator;
	private GraphPanel graph;

	public Main(String title) {
		setTitle(title);
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(2, 1));
		JPanel northTop = new JPanel();
		double[] b = new double[] { 2, 19, 60, 82, 64, 30, 8, 1 };
		double[] c = new double[] { 8, 28, 44, 40, 22, 7, 1 };
		double[] n1 = new double[] { 500 };
		double[] d1 = new double[] { 100, 2, 1 };
		double[] ncoeff = b;
		double[] dcoeff = c;
		northTop.add(new JLabel("Numerator order"));
		northTop.add(numOrder = new JTextField(String.format("%d", ncoeff.length-1), 3));
		northTop.add(new JLabel("Denominator order"));
		northTop.add(denOrder = new JTextField(String.format("%d", dcoeff.length-1), 3));
		northTop.add(doIt = new JButton("Invert"));
		doIt.addActionListener(this);
		north.add(northTop);
		JPanel northBot = new JPanel();
		northBot.add(numEdit = new JButton("Edit numerator"));
		numEdit.addActionListener(this);
		northBot.add(denEdit = new JButton("Edit denominator"));
		denEdit.addActionListener(this);
		northBot.add(new JLabel("Max. time"));
		northBot.add(time = new JTextField("2.5", 5));
		north.add(northBot);
		add("North", north);
		graph = new GraphPanel();
		add("Center", graph);
		numerator = new CoefficientDialog(this, "Numerator", ncoeff);
		denominator = new CoefficientDialog(this, "Denominator", dcoeff);
		invert();
		setSize(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == numEdit) {
			numerator.show(Integer.parseInt(numOrder.getText()));
		}
		if (e.getSource() == denEdit) {
			denominator.show(Integer.parseInt(denOrder.getText()));
		}
		if (e.getSource() == doIt) {
			invert();
		}
	}

	private void invert() {
		try {
			double[] numData = numerator.getData();
			double[] denData = denominator.getData();
			LInverter li = new LInverter(numData, denData);
			double tmax = Double.valueOf(time.getText()).doubleValue();
			li.invert(tmax, 100);
			graph.graph(li.getResults(), tmax);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String args[]) {
		new Main("Laplace");
	}
}

class CoefficientDialog extends JDialog implements ActionListener {
	private double data[];
	private JTextField textFields[];
	private JButton ok, cancel;
	private JPanel center;

	public CoefficientDialog(JFrame parent, String title, double[] data) {
		super(parent, title, true);
		this.data = data;
		setLayout(new BorderLayout());
		center = new JPanel();
		center.setLayout(new GridLayout(0, 2));
		add("Center", center);
		JPanel south = new JPanel();
		south.setLayout(new FlowLayout());
		south.add(ok = new JButton("Ok"));
		ok.addActionListener(this);
		south.add(cancel = new JButton("Cancel"));
		cancel.addActionListener(this);
		add("South", south);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void show(int order) {
		order += 1;
		if (order != data.length) {
			double[] d = data;
			data = new double[order];
			int n = d.length < data.length ? d.length : data.length;
			for (int i = 0; i < n; i++) {
				data[i] = d[i];
			}
		}
		center.removeAll();
		textFields = new JTextField[order];
		for (int i = order-1; i >= 0; i -= 1) {
			textFields[i] = new JTextField(String.format("%.2f", data[i]), 10);
			center.add(new JLabel(String.format("a%d", i)));
			center.add(textFields[i]);
		}
		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			for (int i = 0; i < data.length; i += 1) {
				try {
					data[i] = (Double.valueOf(textFields[i].getText()))
							.doubleValue();
				} catch (NumberFormatException exc) {
					// TODO JOptionPane
				}
			}
			if (data[data.length-1] == 0) {
				JOptionPane.showMessageDialog(null,
						String.format("a%d cannot be zero", data.length-1), "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				dispose();
			}
		}
		if (e.getSource() == cancel) {
			dispose();
		}
	}

	public double[] getData() {
		return data;
	}
}
