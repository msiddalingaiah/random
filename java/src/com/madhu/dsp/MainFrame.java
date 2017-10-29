
package com.madhu.dsp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame implements ActionListener {
	private ChartComponent chart;
	private JButton refresh;
	
	public MainFrame() {
		//useAudio();
		//sweepToneDecoder();
		//sweepCorrelator();
		sweepDemod();
	}

	private void sweepCorrelator() {
		setTitle("Correlator");
		int npoints = 500;
		SignalGenerator gen = new SignalGenerator(1.0, 8000, 2000, 0, 0);
		Correlator td = new Correlator(gen);
//		Source dec = new Comparator(new Integrator(td, 8), 50);
		Source dec = new Integrator(td, 8);
		TrackingGenerator swp = new TrackingGenerator(gen, td, 2000, 1000, npoints);
		JScrollPane sp = new JScrollPane(chart = new ChartComponent(npoints, swp, 0, 1));
		add(sp, BorderLayout.CENTER);
		JPanel bp = new JPanel();
		refresh = new JButton("Refresh");
		bp.add(refresh);
		add(bp, BorderLayout.SOUTH);
		pack();
		refresh.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void useAudio() {
		setTitle("Tone Decoder");
		int npoints = 8820 >> 6;
		AudioLine gen = new AudioLine();
		Source dec = new Integrator(new ToneDecoder(gen), 8);
		JScrollPane sp = new JScrollPane(chart = new ChartComponent(npoints, dec, 0, 120));
		add(sp, BorderLayout.CENTER);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try {
			gen.setJComponent(chart);
			gen.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sweepToneDecoder() {
		setTitle("Tone Decoder");
		int npoints = 500;
		SignalGenerator gen = new SignalGenerator(1.0, 8000, 2000, 0, 0.25);
		ToneDecoder td = new ToneDecoder(gen);
//		Source dec = new Comparator(new Integrator(td, 8), 50);
		Source dec = new Integrator(td, 8);
		TrackingGenerator swp = new TrackingGenerator(gen, dec, 2000, 1000, npoints);
		JScrollPane sp = new JScrollPane(chart = new ChartComponent(npoints, swp, 0, 30));
		add(sp, BorderLayout.CENTER);
		JPanel bp = new JPanel();
		refresh = new JButton("Refresh");
		bp.add(refresh);
		add(bp, BorderLayout.SOUTH);
		pack();
		refresh.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void sweepDemod() {
		setTitle("FM Demodulator");
		int npoints = 500;
		SignalGenerator gen = new SignalGenerator(1.0, 8000, 2000, 0, 0.25);
		Source fm = new Integrator(new FM(gen), 10);
		TrackingGenerator swp = new TrackingGenerator(gen, fm, 2000, 1000, npoints);
		JScrollPane sp = new JScrollPane(chart = new ChartComponent(npoints, swp, -200, 200));
		add(sp, BorderLayout.CENTER);
		JPanel bp = new JPanel();
		refresh = new JButton("Refresh");
		bp.add(refresh);
		add(bp, BorderLayout.SOUTH);
		pack();
		refresh.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		chart.repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MainFrame();
	}
}
