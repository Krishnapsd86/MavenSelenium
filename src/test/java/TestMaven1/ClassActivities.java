package TestMaven1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClassActivities {

	@Test
	public void SauceDemo() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver","C:\\Users\\krish\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		//WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(co);

		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		List<WebElement> TotalItem = driver.findElements(By.cssSelector(".inventory_item_name"));
		System.out.println(TotalItem.size());
		Assert.assertEquals(TotalItem.size(), 6);
//		for (int i = 0; i < TotalItem.size(); i++) {
//			String ListName = TotalItem.get(i).getText();
//			System.out.println(ListName);
//
//		}
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("logout_sidebar_link")).click();
		driver.close();
	}
}
