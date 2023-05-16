package SaucesDemoProjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

public class JqureyUi {
	WebDriver driver;
	@Test
	public void ValidationJqurey(){
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\krish\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
	

		driver.get("https://jqueryui.com/droppable/");
		String Title =driver.getTitle();
		System.out.println(Title);
		 
		System.out.println(driver.getCurrentUrl());
	}
	@Test(dependsOnMethods = {"ValidationJqurey"})
	public void ValidateDragDrop() throws InterruptedException {
		
		WebElement frame = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		driver.switchTo().frame(frame);
		WebElement draggable =driver.findElement(By.xpath("//*[@id=\"draggable\"]"));
		WebElement dropable =driver.findElement(By.xpath("//*[@id=\"droppable\"]"));
		Actions act=new Actions(driver);
		Thread.sleep(3000);
		act.dragAndDrop(draggable, dropable).build().perform();
		String afterDrop = dropable.getText();
		
		Assert.assertEquals(afterDrop,"Dropped!");
		
	}
	@AfterTest
	public void TearDown() {
		driver.close();
	}

}
