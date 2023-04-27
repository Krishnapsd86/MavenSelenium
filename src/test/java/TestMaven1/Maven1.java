package TestMaven1;

import org.testng.annotations.Test;

public class Maven1 {
	
	@Test(priority=1)
	public void LoginTest() {
		System.out.println("Login Successful");
	}
	@Test(priority=2)
	public void LogoutTest() {
		System.out.println("LogOut Successful");
	}

}
