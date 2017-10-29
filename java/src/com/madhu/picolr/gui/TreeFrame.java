
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 6, 2006
 */

package com.madhu.picolr.gui;

import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;

import com.madhu.picolr.Tree;

public class TreeFrame extends JFrame {
	private static final long serialVersionUID = -8164752983669053434L;

	public TreeFrame(String title, Tree root) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new GraphPanel(root));

//		JTree jtree = new JTree(createTree(root));
//		getContentPane().add(new JScrollPane(jtree));
	}

	private DefaultMutableTreeNode createTree(Tree tree) {
		DefaultMutableTreeNode dm = new DefaultMutableTreeNode(tree.getValue());
		int n = tree.size();
		for (int i = 0; i < n; i += 1) {
			dm.add(createTree(tree.get(i)));
		}
		return dm;
	}
}
