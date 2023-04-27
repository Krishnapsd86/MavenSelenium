package TestMaven1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Maven3 {
	

	@Test
	public void testcase1() {
		System.out.println("Test case 1");
	}
	
	@BeforeMethod
	public void beforeMehtod() {
		System.out.println("Before Method called");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method called");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before test method is called");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("After test method is called");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("Before class method is called");
	}
	@AfterClass
	public void afterClass() {
		System.out.println("After class method is called");
	}
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before suite method is called");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("after suite method is called");
	}

}
