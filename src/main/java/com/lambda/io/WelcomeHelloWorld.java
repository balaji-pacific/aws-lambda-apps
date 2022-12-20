package com.lambda.io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Hello world!
 *
 */
public class WelcomeHelloWorld implements RequestHandler<InputObject, Object>
{

	@Override
	public Object handleRequest(InputObject input, Context context) {
		System.out.println("Welcome to lambda function Hellow World");
		if(input.getAge() > 18) {
			System.out.println(input.getName() + " is eligible for casting vote");
		}else {
			System.out.println(input.getName() + " is not eligible for casting vote");
		}
		return "Hello World".toString();
	}
	
}
