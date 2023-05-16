package TestMaven1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Maven13 {
	
	
	@Test(groups = {"login"})
	public void TestCaseOne() {
		System.out.println("testcase one executed");
		
	}
	
	@Test(groups = {"login"})
	public void TestCaseTwo() {
		System.out.println("testcase two executed");
		//Assert.assertEquals(true, false);
	}
	
	@Test(dependsOnGroups = {"login"},groups = {"logout"})
	public void TestCaseThree() {
		System.out.println("testxase three executed ");
	}

}
