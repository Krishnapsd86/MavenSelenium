package TestMaven1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

@Test
public class CaptureScreen {
	WebDriver driver;

	public void captureScreenShot() throws IOException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\krish\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.get("https://www.saucedemo.com/");
		
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File des = new File(".//Screenshot/page.png");
		FileUtils.copyFile(src, des);

	}
}
