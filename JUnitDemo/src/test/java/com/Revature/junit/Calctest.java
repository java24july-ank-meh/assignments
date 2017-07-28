package com.Revature.junit;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Calctest {
	@Test
	public void multiplicationofzeroshouldgivezero(){
		System.out.println("Currently Testing x * 0");
		Calculator tester = new CalculatorImp1();
		assertEquals("10*0 should = 0", 0, tester.multiply(10, 0));
	}
	
	@Test
	public void additionshouldgivesum(){
		System.out.println("ADD should give sum");
		Calculator tester = new CalculatorImp1();
		assertEquals("10+2 should give 12", 12, tester.add(10, 2));
	}
	
	@Before
	public void beforeMethod(){
		System.out.println("*******BEFORE EACH METHOD*******");
	}
	
	@After
	public void BeforeMethod(){
		
	}
	
	@org.junit.BeforeClass
	public void BeforeClass(){
		
	}
	
	@org.junit.AfterClass
	public void AfterClass(){
		
	}
}
