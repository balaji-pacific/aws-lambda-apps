/**
 * 
 */
package com.lambda.io;

import java.io.Serializable;

/**
 * @author balajisoundarrajan
 *
 */
public class InputObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6494378816281758082L;
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
