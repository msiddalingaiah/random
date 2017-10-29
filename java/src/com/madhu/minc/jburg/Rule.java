
package com.madhu.minc.jburg;

/*
 * Created on Jul 19, 2007 at 7:01:59 PM
 */
public class Rule {
	Symbol lhs;		/* lefthand side non-terminal */
	RTree pattern;		/* rule pattern */
	int ern;		/* external rule number */
	int packedErn;		/* packed external rule number */
	int cost;		/* associated cost */
	Rule link;		/* next rule in ern order */
	Rule next;		/* next rule with same pattern root */
	Rule chain;		/* next chain rule with same rhs */
	Rule decode;		/* next rule with same lhs */
	Rule kids;		/* next rule with same burm_kids pattern */
	String template;  /* output template */
	
	public String toString() {
		return lhs + ": " + pattern;
	}
}
