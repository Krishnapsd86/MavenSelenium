package TestMaven1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Maven2 {
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This will run before every method");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("This will run after every method");
	}
	
	@Test(priority =1)
	public void loginTest() {
		System.out.println("Login successful one ");
	}
	
	@Test(priority =1)
	public void loginTest2() {
		System.out.println("Login successful two ");
	}

}
