
/*
 * Created on Jun 16, 2003
 *
 * Copyright (c) 2003 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.fft;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class FFTFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -1469690751146201212L;

	private static DecimalFormat hzFormat = new DecimalFormat("0Hz");
	private static DecimalFormat khzFormat = new DecimalFormat("0.00kHz");
	private static DecimalFormat milliFormat = new DecimalFormat("0.0mV");
	private static DecimalFormat microFormat = new DecimalFormat("0.0µV");
	private static Object[] averages = {
		new Integer(1), new Integer(2), new Integer(4), new Integer(8),
		new Integer(16), new Integer(32), new Integer(64)
	};

	protected AbstractFFT fft;
	protected FFTPanel fftPanel;
	protected JComboBox windowCombo;
	protected JComboBox averageCombo;
	protected JTextField peakLevelTF;
	protected JTextField peakFreqTF;

	public FFTFrame() {
		setTitle("Frequency Domain");
		Container cp = getContentPane();
		fftPanel = new FFTPanel();
		cp.add(fftPanel, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		windowCombo = new JComboBox(AbstractFFT.WINDOW_NAMES);
		windowCombo.setSelectedIndex(AbstractFFT.HANNING);
		averageCombo = new JComboBox(averages);
		averageCombo.setSelectedIndex(4);
		southPanel.add(new JLabel("Win"));
		southPanel.add(windowCombo);
		southPanel.add(new JLabel("Avg"));
		southPanel.add(averageCombo);
		southPanel.add(new JLabel("Pk Lev"));
		southPanel.add(peakLevelTF = new JTextField(5));
		peakLevelTF.setEditable(false);
		southPanel.add(new JLabel("Pk Frq"));
		southPanel.add(peakFreqTF = new JTextField(5));
		peakFreqTF.setEditable(false);
		cp.add(southPanel, BorderLayout.SOUTH);

		pack();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		windowCombo.addActionListener(this);
		averageCombo.addActionListener(this);
		int winF = windowCombo.getSelectedIndex();
		int ave = ((Integer) averageCombo.getSelectedItem()).intValue();
		fft = new FFT3(TimeFrame.FFT_SIZE, ave, winF);
		setVisible(true);
	}

	public void transform(double[] realData) {
		fft.transform(realData);
		if (fft.isAverageDone()) {
			fftPanel.setData(fft.getMagnitude());
			double level = fftPanel.getPeakLevel();
			if (level >= 0.001 && level < 1.0) {
				level *= 1000.0;
				peakLevelTF.setText(milliFormat.format(level));
			} else if (level >= 1.0e-6 && level < 0.001) {
				level *= 1.0e+6;
				peakLevelTF.setText(microFormat.format(level));
			}
			double freq = fftPanel.getPeakFrequency();
			if (freq < 1000.0) {
				peakFreqTF.setText(hzFormat.format(freq));
			} else {
				freq /= 1000.0;
				peakFreqTF.setText(khzFormat.format(freq));
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == windowCombo) {
			int winF = windowCombo.getSelectedIndex();
			fft.setWindowFunction(winF);
		} else if (source == averageCombo) {
			int ave = ((Integer) averageCombo.getSelectedItem()).intValue();
			fft.setAverageCount(ave);
		}
	}

	public void setSampleRate(double sampleRate) {
		fftPanel.setSampleRate(sampleRate);
	}
}
