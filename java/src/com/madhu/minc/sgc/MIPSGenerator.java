/*  Generated Thu May 01 23:08:37 CDT 2008 by JBurg version 0.9.7 */


package com.madhu.minc.sgc;

public class MIPSGenerator
{

	java.util.Stack reducedValues = new java.util.Stack();


public static final int compare_NT = 1;

public static final int stmt_NT = 2;

public static final int reg_NT = 3;

public static final int constant_NT = 4;

public static final int nStates = 4;


public JBurgAnnotation label( SGCTree to_be_labelled)
{
	JBurgAnnotation result = null;
	if ( to_be_labelled != null )
	{
		result = new JBurgAnnotation(to_be_labelled,nStates + 1);
		
		result.left = label(((SGCTree)to_be_labelled.leftChild()));
		if ( ( ( (to_be_labelled.leftChild() != null)? ( (to_be_labelled.rightChild() != null)? 2: 1 ): 0 ) > 1 )  )
		{
			result.right = label(((SGCTree)to_be_labelled.rightChild()));
			
		}
		this.computeCostMatrix(result);
		
	}
	return( result);
	
}


private void computeCostMatrix( JBurgAnnotation node)
{
	int iCost;
	switch( node.getOperator() )
	{case SyntaxParser.INTEGER_LITERAL:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 0 && node.getOperator() == SyntaxParser.INTEGER_LITERAL )
			{
				
				iCost = 0;
				;
				if ( ( node.getCost(constant_NT) > iCost )  )
				{/* Matched SyntaxParser.INTEGER_LITERAL ==> constant */

					node.reset(constant_NT, iCost, 12);
					closure_constant(node, iCost);
					
				}
				
			}
			break;
		}
		case SyntaxParser.STAR:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 2 && node.getOperator() == SyntaxParser.STAR && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  && ( Integer.MAX_VALUE > node.rightChild().getCost(reg_NT) )  )
			{
				
				iCost =  ( (1 + node.leftChild().getCost(reg_NT))  + node.rightChild().getCost(reg_NT)) ;
				;
				if ( ( node.getCost(reg_NT) > iCost )  )
				{/* Matched SyntaxParser.STAR ==> reg */

					node.reset(reg_NT, iCost, 7);
					node.addSubgoal(reg_NT, node.leftChild(), reg_NT);
					node.addSubgoal(reg_NT, node.rightChild(), reg_NT);
					
				}
				
			}
			break;
		}
		case SyntaxParser.PLUS:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 2 && node.getOperator() == SyntaxParser.PLUS && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  && ( Integer.MAX_VALUE > node.rightChild().getCost(reg_NT) )  )
			{
				
				iCost =  ( (1 + node.leftChild().getCost(reg_NT))  + node.rightChild().getCost(reg_NT)) ;
				;
				if ( ( node.getCost(reg_NT) > iCost )  )
				{/* Matched SyntaxParser.PLUS ==> reg */

					node.reset(reg_NT, iCost, 5);
					node.addSubgoal(reg_NT, node.leftChild(), reg_NT);
					node.addSubgoal(reg_NT, node.rightChild(), reg_NT);
					
				}
				
			}
			break;
		}
		case SyntaxParser.JUMP_IF:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 0 && node.getOperator() == SyntaxParser.JUMP_IF )
			{
				
				iCost = 0;
				;
				if ( ( node.getCost(compare_NT) > iCost )  )
				{/* Matched SyntaxParser.JUMP_IF ==> compare */

					node.reset(compare_NT, iCost, 4);
					
				}
				
			}
			break;
		}
		case SyntaxParser.LESS_THAN:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 2 && node.getOperator() == SyntaxParser.LESS_THAN && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  && ( Integer.MAX_VALUE > node.rightChild().getCost(reg_NT) )  )
			{
				
				iCost =  ( (1 + node.leftChild().getCost(reg_NT))  + node.rightChild().getCost(reg_NT)) ;
				;
				if ( ( node.getCost(reg_NT) > iCost )  )
				{/* Matched SyntaxParser.LESS_THAN ==> reg */

					node.reset(reg_NT, iCost, 9);
					node.addSubgoal(reg_NT, node.leftChild(), reg_NT);
					node.addSubgoal(reg_NT, node.rightChild(), reg_NT);
					
				}
				
			}
			break;
		}
		case SyntaxParser.ASSIGN:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 2 && node.getOperator() == SyntaxParser.ASSIGN && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  && ( Integer.MAX_VALUE > node.rightChild().getCost(reg_NT) )  )
			{
				
				iCost =  ( (1 + node.leftChild().getCost(reg_NT))  + node.rightChild().getCost(reg_NT)) ;
				;
				if ( ( node.getCost(stmt_NT) > iCost )  )
				{/* Matched SyntaxParser.ASSIGN ==> stmt */

					node.reset(stmt_NT, iCost, 2);
					node.addSubgoal(stmt_NT, node.leftChild(), reg_NT);
					node.addSubgoal(stmt_NT, node.rightChild(), reg_NT);
					
				}
				
			}
			break;
		}
		case SyntaxParser.MINUS:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 2 && node.getOperator() == SyntaxParser.MINUS && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  && ( Integer.MAX_VALUE > node.rightChild().getCost(reg_NT) )  )
			{
				
				iCost =  ( (1 + node.leftChild().getCost(reg_NT))  + node.rightChild().getCost(reg_NT)) ;
				;
				if ( ( node.getCost(reg_NT) > iCost )  )
				{/* Matched SyntaxParser.MINUS ==> reg */

					node.reset(reg_NT, iCost, 6);
					node.addSubgoal(reg_NT, node.leftChild(), reg_NT);
					node.addSubgoal(reg_NT, node.rightChild(), reg_NT);
					
				}
				
			}
			break;
		}
		case SyntaxParser.IF:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 2 && node.getOperator() == SyntaxParser.IF && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  && ( Integer.MAX_VALUE > node.rightChild().getCost(compare_NT) )  )
			{
				
				iCost =  ( (1 + node.leftChild().getCost(reg_NT))  + node.rightChild().getCost(compare_NT)) ;
				;
				if ( ( node.getCost(stmt_NT) > iCost )  )
				{/* Matched SyntaxParser.IF ==> stmt */

					node.reset(stmt_NT, iCost, 3);
					node.addSubgoal(stmt_NT, node.leftChild(), reg_NT);
					node.addSubgoal(stmt_NT, node.rightChild(), compare_NT);
					
				}
				
			}
			break;
		}
		case SyntaxParser.DIV:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 2 && node.getOperator() == SyntaxParser.DIV && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  && ( Integer.MAX_VALUE > node.rightChild().getCost(reg_NT) )  )
			{
				
				iCost =  ( (1 + node.leftChild().getCost(reg_NT))  + node.rightChild().getCost(reg_NT)) ;
				;
				if ( ( node.getCost(reg_NT) > iCost )  )
				{/* Matched SyntaxParser.DIV ==> reg */

					node.reset(reg_NT, iCost, 8);
					node.addSubgoal(reg_NT, node.leftChild(), reg_NT);
					node.addSubgoal(reg_NT, node.rightChild(), reg_NT);
					
				}
				
			}
			break;
		}
		case SyntaxParser.IDENT:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 0 && node.getOperator() == SyntaxParser.IDENT )
			{
				
				iCost = 1;
				;
				if ( ( node.getCost(reg_NT) > iCost )  )
				{/* Matched SyntaxParser.IDENT ==> reg */

					node.reset(reg_NT, iCost, 13);
					
				}
				
			}
			break;
		}
		case SyntaxParser.GREATER_THAN:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 2 && node.getOperator() == SyntaxParser.GREATER_THAN && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  && ( Integer.MAX_VALUE > node.rightChild().getCost(reg_NT) )  )
			{
				
				iCost =  ( (1 + node.leftChild().getCost(reg_NT))  + node.rightChild().getCost(reg_NT)) ;
				;
				if ( ( node.getCost(reg_NT) > iCost )  )
				{/* Matched SyntaxParser.GREATER_THAN ==> reg */

					node.reset(reg_NT, iCost, 10);
					node.addSubgoal(reg_NT, node.leftChild(), reg_NT);
					node.addSubgoal(reg_NT, node.rightChild(), reg_NT);
					
				}
				
			}
			break;
		}
		case SyntaxParser.REF:
		{
			if ( ( (node == null||node.leftChild() == null)? 0: (node.rightChild() == null)? 1: 2) == 1 && node.getOperator() == SyntaxParser.REF && ( Integer.MAX_VALUE > node.leftChild().getCost(reg_NT) )  )
			{
				
				iCost =  (1 + node.leftChild().getCost(reg_NT)) ;
				;
				if ( ( node.getCost(reg_NT) > iCost )  )
				{/* Matched SyntaxParser.REF ==> reg */

					node.reset(reg_NT, iCost, 14);
					node.addSubgoal(reg_NT, node.leftChild(), reg_NT);
					
				}
				
			}
			break;
		}
		
	}
	
}


private void closure_constant( JBurgAnnotation p, int c)
{
	int iCost;
	
	iCost =  (c + 1) ;
	;
	if ( ( p.getCost(reg_NT) > iCost )  )
	{p.reset(reg_NT, iCost, 11);
		p.recordAntecedent(reg_NT, constant_NT);
		
	}
	
}


private Object action_2( SGCTree p)
{

	Object lvalue = (Object)reducedValues.pop();


	Object rvalue = (Object)reducedValues.pop();

		{
			((Register)rvalue).releaseTemp();
			asmWriter.printAddressInstruction("sw", lvalue, rvalue, 0);
			return null;
		}
}


private Object action_3( SGCTree p)
{

	Object expr = (Object)reducedValues.pop();


	Object label = (Object)reducedValues.pop();

		{
			asmWriter.printInstruction("jumpeq", label, null, null);
			return null;
		}
}


private Object action_4( SGCTree p)
{
		{
			return p.getText();
		}
}


private Object action_5( SGCTree p)
{

	Object arg1 = (Object)reducedValues.pop();


	Object arg2 = (Object)reducedValues.pop();

		{
			((Register)arg1).releaseTemp();
			((Register)arg2).releaseTemp();
			Object dst = registerAllocator.allocate(p);
			asmWriter.printInstruction("add", dst, arg1, arg2);
			return dst;
		}
}


private Object action_6( SGCTree p)
{

	Object arg1 = (Object)reducedValues.pop();


	Object arg2 = (Object)reducedValues.pop();

		{
			((Register)arg1).releaseTemp();
			((Register)arg2).releaseTemp();
			Object dst = registerAllocator.allocate(p);
			asmWriter.printInstruction("sub", dst, arg1, arg2);
			return dst;
		}
}


private Object action_7( SGCTree p)
{

	Object arg1 = (Object)reducedValues.pop();


	Object arg2 = (Object)reducedValues.pop();

		{
			((Register)arg1).releaseTemp();
			((Register)arg2).releaseTemp();
			Object dst = registerAllocator.allocate(p);
			asmWriter.printInstruction("mul", dst, arg1, arg2);
			return dst;
		}
}


private Object action_8( SGCTree p)
{

	Object arg1 = (Object)reducedValues.pop();


	Object arg2 = (Object)reducedValues.pop();

		{
			((Register)arg1).releaseTemp();
			((Register)arg2).releaseTemp();
			Object dst = registerAllocator.allocate(p);
			asmWriter.printInstruction("div", dst, arg1, arg2);
			return dst;
		}
}


private Object action_9( SGCTree p)
{

	Object arg1 = (Object)reducedValues.pop();


	Object arg2 = (Object)reducedValues.pop();

		{
			((Register)arg1).releaseTemp();
			((Register)arg2).releaseTemp();
			Object dst = registerAllocator.allocate(p);
			asmWriter.printInstruction("cmplt", dst, arg1, arg2);
			return dst;
		}
}


private Object action_10( SGCTree p)
{

	Object arg1 = (Object)reducedValues.pop();


	Object arg2 = (Object)reducedValues.pop();

		{
			((Register)arg1).releaseTemp();
			((Register)arg2).releaseTemp();
			Object dst = registerAllocator.allocate(p);
			asmWriter.printInstruction("cmpgt", dst, arg1, arg2);
			return dst;
		}
}


private Object action_11( SGCTree p)
{

	Object constant = (Object)reducedValues.pop();

		{
		    Object dst = registerAllocator.allocate(p);
			asmWriter.printInstruction("la", dst, p, null);
			return dst;
		}
}


private Object action_12( SGCTree p)
{
		{
		    return p.getText();
		}
}


private Object action_13( SGCTree p)
{
		{
		    Object dst = registerAllocator.allocate(p);
		    asmWriter.printInstruction ( "la", dst, p, null);
		    return dst;
		}
}


private Object action_14( SGCTree p)
{

	Object address = (Object)reducedValues.pop();

		{
			asmWriter.printAddressInstruction("lw", address, address, 1);
		    return address;
		}
}


private void dispatchAction( SGCTree p, int iRule) throws java.lang.Exception
{switch( iRule )
	{case 1:
		{/* Don't reduce or touch the stack. */

			break;
		}
		case 2:
		{reducedValues.push(this.action_2(p));
			break;
		}
		case 3:
		{reducedValues.push(this.action_3(p));
			break;
		}
		case 4:
		{reducedValues.push(this.action_4(p));
			break;
		}
		case 5:
		{reducedValues.push(this.action_5(p));
			break;
		}
		case 6:
		{reducedValues.push(this.action_6(p));
			break;
		}
		case 7:
		{reducedValues.push(this.action_7(p));
			break;
		}
		case 8:
		{reducedValues.push(this.action_8(p));
			break;
		}
		case 9:
		{reducedValues.push(this.action_9(p));
			break;
		}
		case 10:
		{reducedValues.push(this.action_10(p));
			break;
		}
		case 11:
		{reducedValues.push(this.action_11(p));
			break;
		}
		case 12:
		{reducedValues.push(this.action_12(p));
			break;
		}
		case 13:
		{reducedValues.push(this.action_13(p));
			break;
		}
		case 14:
		{reducedValues.push(this.action_14(p));
			break;
		}
		default:throw new IllegalStateException("Unmatched reduce action " + iRule);
		
	}
	
}


public void reduce( JBurgAnnotation p, int goalState) throws java.lang.Exception
{
	int iRule = -1;
	if ( ( goalState > 0 )  )
	{
		iRule = p.getRule(goalState);
		;
		
	}
	else
	{/* Find the minimum-cost path. */

		int minCost = Integer.MAX_VALUE;
		
		int i;
		for( 
		i = 0;
		i <= nStates;i++ )
		{
			if ( ( minCost > p.getCost(i) )  )
			{
				iRule = p.getRule(i);
				
				minCost = p.getCost(i);
				
				goalState = i;
				
			}
			
		}
		
	}
	if ( ( iRule > 0 )  )
	{
		reduceAntecedentStates(p, goalState);
		reduceSubgoals(p, goalState);
		dispatchAction ( (SGCTree)p.getNode(), iRule );
	}
	
	else
	{
		throw new IllegalStateException ( "Unable to find a rule to process \"" + p.toString() + "\", operator="+ String.valueOf(p.getOperator()) + ", goal=" + String.valueOf(goalState) );
	}
	
}


private void reduceSubgoals( JBurgAnnotation p, int goalState) throws java.lang.Exception
{/* Reduce subgoals in reverse order so they get pushed onto the stack */
/* in the order expected by the action routines. */

	for ( int i = i = p.getSubgoalsSize(goalState) - 1; i >= 0; i-- )
	{
		JBurgSubgoal sg = (JBurgSubgoal) p.getSubgoals(goalState).elementAt(i);
		reduce ( sg.getNode(), sg.getGoalState());
	}
	
}


private void reduceAntecedentStates( JBurgAnnotation p, int goalState) throws java.lang.Exception
{
	int[] antecedentRules = new int[nStates];
	
	int[] antecedentStates = new int[nStates];
	
	int currentState = goalState;
	
	int antecedentIndex = 0;
	
	while ( p.hasAntecedent(currentState) )
	{
		currentState = p.getAntecedent(currentState);
		antecedentStates[antecedentIndex] = currentState;
		antecedentRules[antecedentIndex] = p.getRule(currentState);
		antecedentIndex++;
	}
	
	for ( --antecedentIndex; antecedentIndex >= 0; antecedentIndex-- )
	{
		reduceSubgoals( p, antecedentStates[antecedentIndex]);
		dispatchAction( p.getNode(), antecedentRules[antecedentIndex] );
	}
	
}


public void burm ( SGCTree root ) throws Exception
{

	JBurgAnnotation annotatedTree = label(root);
	reduce ( annotatedTree, 0);
}
/* BURM property, from the specification */


private RegisterAllocator registerAllocator;


public void setRegisterallocator( RegisterAllocator setting)
{
	this.registerAllocator = setting;
	
}


public RegisterAllocator getRegisterallocator( )
{return( this.registerAllocator);
	
}

/* BURM property, from the specification */


private ASMWriter asmWriter;


public void setAsmwriter( ASMWriter setting)
{
	this.asmWriter = setting;
	
}


public ASMWriter getAsmwriter( )
{return( this.asmWriter);
	
}


public Object getResult( )
{
	return reducedValues.pop();
	
}


/**
 *  JBurgAnnotation is a data structure internal to the
 *  JBurg-generated BURM that annotates a JBurgNode with
 *  information used for dynamic programming and reduction.
  */
class JBurgAnnotation
{
	/**
	 *  The cost/rule matrices are used during dynamic programming
	 *  to compute the most economical rules that can reduce
	 *  the input node.
	*/
	private int cost[];
	private int rule[];

	/**  Transformation rules may have antecedents: other states whose
	 *  output the transformation rule is intended to transform.
	 *  All such antecedent states must be executed in sequence when the rule is reduced.
	 */
	private int[] antecedentState = null;

	 /**
	 *  A node may have a specific goal state, set by its ancestor's
	 *  requirements for a certain type of input; or it may be at 
	 *  liberty to use the most locally-economical reduction.
	 */
	public java.util.Vector[] m_subgoals;

	/** *  This node's children (may be null).  */
	private JBurgAnnotation left;
	private JBurgAnnotation right;
	/**  The INode we're annotating.  */
	SGCTree m_node; 
	JBurgAnnotation ( SGCTree newNode, int nRules) 
	{
		m_node = newNode;
		rule   = new int[nRules];
		cost   = new int[nRules];
		//  Initial cost of all rules is "infinite"
		java.util.Arrays.fill ( cost, Integer.MAX_VALUE);
		//  Initial rule for every goal is zero -- the JVM has zero-filled the rules array.
	}

	 /** @return this node's operator. */
	public int getOperator() { return m_node.getOperator(); }

	 /** @return this node's wrappedSGCTree. */ 
	public SGCTree getNode()  { return m_node; }

	/** @return the left child of this node.  */

	public JBurgAnnotation leftChild() { return left; }

	/** @return the right child of this node.  */

	public JBurgAnnotation rightChild() { return right; }

	/** @return the wrapped node's toString().  */
	public String toString() { return m_node.toString(); } 
	/** @return the current best cost to reach a goal state.  */
	public int getCost( int goalState ) { return cost[goalState]; }

	 /** Set the cost/rule configuration of a goal state.
	 * @throws IllegalArgumentException if this node has a fixed cost/rule.
	*/
	 public void reset ( int goalState, int cost, int rule )
	{
		this.cost[goalState] = cost;
		this.rule[goalState] = rule;
		//  We have a brand new rule, therefore it has no antecedents.
		if ( this.antecedentState != null )
		this.antecedentState[goalState] = 0;
		if ( m_subgoals != null && m_subgoals[goalState] != null )
		{
			m_subgoals[goalState].clear();
		}
	}

	/** * @return the rule to fire for a specific goal state. */
	public int getRule ( int goalState ) { return rule[goalState]; }

	 /**
	 *  A closure's transformation rule succeeded.
	 *  If this path is selected for reduction, then all the actions  must be run in sequence, beginning with the original;
	 *  so the order of the rules matters.  We disallow transformation rules with  cycles (a node should never 
	 *  transition back to a goal state that has already been reduced).
	*/
	public void recordAntecedent ( int iGoalState, int newAntecedentState )
	{
		int antecedentRule = rule[newAntecedentState];
		//  Sanity-check: we shouldn't be asked to record an antecedent state that hasn't been labelled.
		if ( antecedentRule == 0 )
			throw new IllegalStateException ( "Attempting to record an unlabelled antecedent state." );
		if ( antecedentRule == 1 )
		{
			//  Rule 1 is the simple transformation rule; it doesn't run,  but if it has antecedents, then they must run.
			if ( antecedentState != null )
				antecedentState[iGoalState] = antecedentState[newAntecedentState];
			}
		else
		
			{
				if ( antecedentState == null )
					antecedentState = new int[rule.length];
			}
		antecedentState[iGoalState] = newAntecedentState;
	}

	 /** @return the antecedent to the given goal state. */
	public int getAntecedent(int iGoalState)
	{
		if ( antecedentState != null )
			return antecedentState[iGoalState];
		else
			return 0;
	}
	 /** @return true if the given goal state has an antecdent. */
	public boolean hasAntecedent ( int iGoalState )
		{ return ( antecedentState != null && getAntecedent(iGoalState) != 0 ); }
		public void addSubgoal ( int goalState, JBurgAnnotation node, int subGoal )
		{
			if ( m_subgoals == null )
			{
				m_subgoals = new java.util.Vector[rule.length];
			}
			if ( m_subgoals[goalState] == null )
			{
				m_subgoals[goalState] = new java.util.Vector();
			}
			m_subgoals[goalState].add(new JBurgSubgoal(node, subGoal));
		}
		public int getSubgoalsSize(int goalState)
		{
			if ( m_subgoals != null && m_subgoals[goalState] != null )
			{
				return m_subgoals[goalState].size();
			}
			else
			{
				return 0;
			}
		}
		public java.util.Vector getSubgoals(int goalState)
		{
			if ( m_subgoals == null )
			{
				throw new IllegalStateException("No subgoal records.");
			}
			return m_subgoals[goalState];
		}
	}

	public class JBurgSubgoal
	{
		JBurgAnnotation m_node;
		int m_goal_state;
		public JBurgSubgoal(JBurgAnnotation node, int goalState)
		{
			m_node = node;
			m_goal_state = goalState;
		}
		public JBurgAnnotation getNode() { return m_node; }
		public int getGoalState() { return m_goal_state; }
	}
}
