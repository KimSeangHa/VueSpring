package com.spring.webservice.utill;

import java.security.SecureRandom;

public class MakeToken {
	
	public static String NewToken(int i) {
		
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[i];
		random.nextBytes(bytes);
		String token = bytes.toString();
		
		return token;
	}
}
