/*
 * Created on Jun 14, 2003
 *
 * Copyright (c) 2003 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.fft;

import java.util.Date;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.Line.Info;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Main {
	public static void main(String args[]) throws LineUnavailableException {
		TimeFrame tf = new TimeFrame();
		tf.run();
	}

	public static void testFFT(String[] args) {
		int size = 1024;
		FFT3 fft3 = new FFT3(size);
		double[] real = new double[size];
		for (int i = 0; i < size; i++) {
			real[i] = Math.sin(4 * 2 * Math.PI * i / size) > 0 ? 1 : -1;
		}
		long start = System.currentTimeMillis();
		for (int i=0; i<1000; i+=1) {
			fft3.transform(real);
		}
		long time = System.currentTimeMillis() - start;
		System.out.printf("Time = %dms\n", time);

		FFT fft = new FFT(size);
		start = System.currentTimeMillis();
		for (int i=0; i<1000; i+=1) {
			fft.transform(real);
		}
		time = System.currentTimeMillis() - start;
		System.out.printf("Time = %dms\n", time);

		for (int i = 0; i < size>>1; i++) {
			System.out.printf("%5.4f\n", fft3.getMagnitude()[i]-fft.getMagnitude()[i]);
		}
	}

	public static void testAudio() throws LineUnavailableException {
		System.out.println(Main.class + " " + new Date());
		Mixer.Info[] info = AudioSystem.getMixerInfo();
		int n = info.length;
		System.out.println("Found " + n + " mixers");
		for (int i=0; i<n; i+=1) {
			Mixer mixer = AudioSystem.getMixer(info[i]);
			Line[] lines = mixer.getSourceLines();
			System.out.println(info[i] + " has " + lines.length + " lines");
		}
		Info[] lineInfo = AudioSystem.getSourceLineInfo(Port.Info.LINE_IN);
		System.out.println("lineInfo.length = " + lineInfo.length);
		for (int i = 0; i < lineInfo.length; i++) {
			System.out.println(lineInfo[i]);
		}
		System.exit(0);
	}
}
