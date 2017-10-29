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
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class TimeFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -6710594923613008449L;

	public static final int FFT_SIZE = 1024;
	public static final float RAW_SAMPLE_RATE = 44100.0f;

	protected boolean startPressed;
	protected boolean stopPressed;
	protected JButton startBtn;
	protected JButton stopBtn;
	protected JComboBox averageCombo;
	private JButton quitBtn;
	protected TimePanel timePanel;
	protected FFTFrame fftFrame;
	protected byte[] audioBytes;
	protected double[] data;
	private double[] averageData;
	private int averageCount;

	protected AudioInputStream audioInputStream;
	private AudioFormat format;
	private DataLine.Info info;

	public TimeFrame() throws LineUnavailableException {
		setTitle("Time Domain");
		Container cp = getContentPane();
		timePanel = new TimePanel();
		cp.add(timePanel, BorderLayout.CENTER);
		JPanel southPanel = new JPanel();
		southPanel.add(startBtn = new JButton("Start"));
		startBtn.addActionListener(this);
		southPanel.add(stopBtn = new JButton("Stop"));
		stopBtn.addActionListener(this);
		southPanel.add(new JLabel("Average"));
		southPanel.add(averageCombo = new JComboBox());
		averageCombo.addItem(new Integer(1));
		averageCombo.addItem(new Integer(2));
		averageCombo.addItem(new Integer(4));
		averageCombo.addItem(new Integer(8));
		averageCombo.addActionListener(this);
		southPanel.add(quitBtn = new JButton("Quit"));
		quitBtn.addActionListener(this);
		cp.add(southPanel, BorderLayout.SOUTH);
		pack();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);

		audioBytes = new byte[FFT_SIZE * 3];	// a little extra to be safe...
		data = new double[FFT_SIZE];
		averageData = new double[FFT_SIZE];

		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float rate = RAW_SAMPLE_RATE;
		int sampleSize = 16;
		boolean bigEndian = true;
		int channels = 1;

		format = new AudioFormat(encoding, rate, sampleSize,
					channels, (sampleSize / 8) * channels, rate, bigEndian);
		info = new DataLine.Info(TargetDataLine.class, format);

		if (!AudioSystem.isLineSupported(info)) {
			JOptionPane.showMessageDialog(this,
				"Line matching " + info + " not supported",
				"Line Error", JOptionPane.ERROR_MESSAGE);
		} else {
			fftFrame = new FFTFrame();
			Point p = getLocation();
			fftFrame.setLocation(p.x, p.y + getHeight() + 10);
			fftFrame.setSampleRate(getSampleRate());
		}
	}

	public double getSampleRate() {
		Integer item = (Integer) averageCombo.getSelectedItem();
		int avgValue = item.intValue();
		return RAW_SAMPLE_RATE / avgValue;
	}

	public void transform(double[] realData) {
		Integer item = (Integer) averageCombo.getSelectedItem();
		int avgValue = item.intValue();
		int npoints = realData.length / avgValue;
		int index = npoints * averageCount;
		int offset = 0;
		for (int i = 0; i < npoints; i++) {
			double sum = 0;
			for (int j=0; j<avgValue; j += 1) {
				sum += realData[j + offset];
			}
			offset += avgValue;
			averageData[index++] = sum / avgValue;
		}

		averageCount += 1;
		if (averageCount >= avgValue) {
			timePanel.setData(averageData);
			fftFrame.transform(averageData);
			averageCount = 0;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		TargetDataLine line = null;
		int bufferLengthInBytes = 0;

		while (true) {
			if (startPressed) {
				startPressed = false;
				if (line == null) {
					try {
						line = (TargetDataLine) AudioSystem.getLine(info);
						line.open(format, line.getBufferSize());
						line.start();
						int frameSizeInBytes = format.getFrameSize();
						int bufferLengthInFrames = line.getBufferSize() / 8;
						bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
						if (bufferLengthInBytes > audioBytes.length) {
							bufferLengthInBytes = audioBytes.length;
						}
					} catch (LineUnavailableException ex) {
						JOptionPane.showMessageDialog(this,
							"Unable to open the line: " + ex.getMessage(),
							"Line Error", JOptionPane.ERROR_MESSAGE);
					} catch (SecurityException ex) {
						JOptionPane.showMessageDialog(this, ex.getMessage(),
							"SecurityException", JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, ex.toString(),
							"Exception", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			if (line != null) {
				int numBytesRead;
				int readIndex = 0;
				while (readIndex < 2*FFT_SIZE) {
					if ((numBytesRead = line.read(audioBytes, readIndex, bufferLengthInBytes)) == -1) {
						break;
					}
					readIndex += numBytesRead;
				}

				// Big endian
				for (int i = 0; i < FFT_SIZE; i++) {
					int MSB = (int) audioBytes[2 * i];
					int LSB = (int) audioBytes[2 * i + 1];
					double value = (MSB << 8) | (0xff & LSB);
					data[i] = value / 32768.0;
				}
				transform(data);
			}
			if (stopPressed) {
				stopPressed = false;
				if (line != null) {
					line.stop();
					line.close();
					line = null;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
//	encoding = PCM_SIGNED
//	rate = 44100.0
//	sampleSize = 16
//	channels = 2
//	bigEndian = true

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == startBtn) {
			startPressed = true;
			averageCount = 0;
		} else if (source == stopBtn) {
			stopPressed = true;
		} else if (source == averageCombo) {
			averageCount = 0;
			fftFrame.setSampleRate(getSampleRate());
		} else if (source == quitBtn) {
			stopPressed = true;
			System.exit(0);
		}
	}
}
