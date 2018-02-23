/**
 * 
 */
package com.test4;

import java.awt.datatransfer.StringSelection;
import java.util.Vector;

/**
 * @author yesemili
 *
 */
public class test4 {

	
	Vector<Vector<String>> strings=new Vector<Vector<String>>();
	Vector<String> str1=new Vector<String>();
	Vector<String> str2=new Vector<String>();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test4 stest4=new test4();
		stest4.strings.remove(1);
		System.out.println("vector<string> = "+stest4.strings.get(0).size());
	}
	
	public test4()
	{
		str1.add("1");
		str2.add("2");
		strings.add(str1);
		strings.add(str2);
	}
}
