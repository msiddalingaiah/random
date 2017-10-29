
/*
 * Created on Dec 7, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.lir;

import java.util.List;

/**
 * @author Madhu Siddalingaiah
 *
 */
public abstract class RegisterAllocator {
	/**
	 * 
	 */
	public RegisterAllocator() {
	}

	public abstract void allocate(List variables);
}
