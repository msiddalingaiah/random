
package com.madhu.minc.s4;

import java.util.HashMap;
import java.util.Iterator;

/*
 * Created on Jul 1, 2007 at 10:00 AM
 */
public class MatchList {
	private HashMap<Symbol,Match> matches;
	
	public MatchList() {
		matches = new HashMap<Symbol,Match>();
	}

	public boolean derives(Symbol lhs) {
		return matches.get(lhs) != null;
	}
	
	public Match getMatch(Symbol lhs) {
		Match m = matches.get(lhs);
		if (m == null) {
			m = new Match();
			matches.put(lhs, m);
		}
		return m;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<Symbol> it = matches.keySet().iterator();
		while (it.hasNext()) {
			sb.append(matches.get(it.next()));
			sb.append('\n');
		}
		return sb.toString();
	}
}
