
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 23, 2006
 */

package com.madhu.minc.s3;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeFrame extends JFrame {
	private static final long serialVersionUID = -8164752983669053434L;

	public TreeFrame(String title, Tree root) {
		super(title);
		JTree jtree = new JTree(createTree(root));
		getContentPane().add(new JScrollPane(jtree));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private DefaultMutableTreeNode createTree(Tree tree) {
		DefaultMutableTreeNode dm = new DefaultMutableTreeNode(tree.toString());
		int n = tree.getChildCount();
		for (int i=0; i<n; i+=1) {
			dm.add(createTree(tree.getChild(i)));
		}
		return dm;
	}
}
