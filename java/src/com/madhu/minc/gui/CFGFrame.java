
/*
 * Created on Nov 20, 2004 4:42:42 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.madhu.minc.hir.ControlFlowGraph;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class CFGFrame extends JFrame {
	public CFGFrame(String title, ControlFlowGraph cfg) {
		setTitle(title);
		setSize(700, 500);
		CFGPanel cp = new CFGPanel(cfg);
		JScrollPane scrollPane = new JScrollPane(cp,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
