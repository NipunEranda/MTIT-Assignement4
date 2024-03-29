// Copyright (C) 2011 - Will Glozer.  All rights reserved.

package com.sliit.abc.util.lambdaworks.jni;
/**
 * Exception thrown when the current platform cannot be detected.
 *
 * @author Will Glozer
 */
public class UnsupportedPlatformException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedPlatformException(String s) {
        super(s);
    }
}
