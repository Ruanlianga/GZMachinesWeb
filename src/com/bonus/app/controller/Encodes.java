package com.bonus.app.controller;

import org.apache.commons.codec.binary.Base64;

public class Encodes {

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
	   return Base64.decodeBase64(input.getBytes());
	}

}
