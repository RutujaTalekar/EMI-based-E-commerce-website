package com.lti.fg.services;

import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class OtpGenerator
{
	public String generatorOTP(int len) 
	  { 
		int max=9;
		int min=0;
		String otp="";
		int range=max-min +1;
		 for (int i = 0; i < len; i++) { 
            int rand = (int)(Math.random() * range) + min; 
            otp+=rand;
            // Output is different everytime this code is executed 
             
		 }
		 return otp; 
	  } 

}
