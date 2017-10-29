
package com.madhu.dsp;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.DataLine.Info;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/*
 * Created on Mar 19, 2009 at 1:48:21 PM
 */
public class AudioLine implements Source {
	public static final float RAW_SAMPLE_RATE = 44100.0f;
	public static final int BLOCK_SIZE = 8820;
	public static final int AVERAGE_SAMPLES = 5;
	private byte[] audioBytes;
	private double[] data;
	private double[] averageData;
	private int averageDataIndex;
	private AudioFormat format;
	private Info info;
	private TargetDataLine line;
	private int bufferLengthInBytes;
	private JComponent chart;

	public AudioLine() {
		audioBytes = new byte[BLOCK_SIZE * 3];	// a little extra to be safe...
		data = new double[BLOCK_SIZE];
		averageData = new double[BLOCK_SIZE / AVERAGE_SAMPLES];

		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float rate = RAW_SAMPLE_RATE;
		int sampleSize = 16;
		boolean bigEndian = true;
		int channels = 1;

		format = new AudioFormat(encoding, rate, sampleSize,
					channels, (sampleSize / 8) * channels, rate, bigEndian);
		info = new DataLine.Info(TargetDataLine.class, format);

		if (!AudioSystem.isLineSupported(info)) {
			JOptionPane.showMessageDialog(null,
				"Line matching " + info + " not supported",
				"Line Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setJComponent(JComponent chart) throws Exception {
		this.chart = chart;
	}

	public void run() throws Exception {
		while (true) {
			readLine();
		}
	}

	public double getValue() throws Exception {
		if (averageDataIndex >= averageData.length) {
			averageDataIndex = 0;
		}
		return averageData[averageDataIndex++];
	}

	private void readLine() throws Exception {
		if (line == null) {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format, line.getBufferSize());
			line.start();
			int frameSizeInBytes = format.getFrameSize();
			int bufferLengthInFrames = line.getBufferSize() / 8;
			bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
			if (bufferLengthInBytes > audioBytes.length) {
				bufferLengthInBytes = audioBytes.length;
			}
		}
		if (line != null) {
			int numBytesRead;
			int readIndex = 0;
			while (readIndex < 2*BLOCK_SIZE) {
				if ((numBytesRead = line.read(audioBytes, readIndex, bufferLengthInBytes)) == -1) {
					break;
				}
				readIndex += numBytesRead;
			}
			// Big endian
			for (int i = 0; i < BLOCK_SIZE; i++) {
				int MSB = (int) audioBytes[2 * i];
				int LSB = (int) audioBytes[2 * i + 1];
				double value = (MSB << 8) | (0xff & LSB);
				data[i] = value / 32768.0;
			}
			int ai = 0;
			for (int i = 0; i < BLOCK_SIZE; i += AVERAGE_SAMPLES) {
				double sum = 0.0;
				for (int j = 0; j < AVERAGE_SAMPLES; j += 1) {
					sum += data[i+j];
				}
				averageData[ai++] = sum / AVERAGE_SAMPLES;
			}
			averageDataIndex = 0;
			chart.repaint();
		}
	}

	public void reset() {
	}
}
