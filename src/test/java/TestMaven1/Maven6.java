package TestMaven1;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Maven6 {
	
	@Test()
	@Parameters({"username","password"})
	public void testcaseOne(String un , String pwd) {
		System.out.println(un); //UserName
		System.out.println(pwd); //PassworD
		System.out.println("Test case one");
		
	}
	
	@Test()
	public void testcaseTwo() {
		System.out.println("Test case two");
	}

}
