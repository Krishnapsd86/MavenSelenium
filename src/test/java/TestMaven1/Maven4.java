package TestMaven1;

import org.testng.annotations.Test;

public class Maven4 {
	
	@Test(priority =2)
	public void alpha() {
		System.out.println("Alpha executed ");
	}
	
	@Test(priority =1)
	public void beta() {
		System.out.println("Beta Executed ");
	}
	
	
	@Test(priority = 0)
	public void ca() {
		System.out.println("ca Executed ");
	}
	
	@Test(priority = -1)
	public void da() {
		System.out.println("da Executed ");
	}

}
