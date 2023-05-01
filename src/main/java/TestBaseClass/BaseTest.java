package TestBaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	

	public WebDriver initilizeDriver() throws IOException {

		Properties prop = new Properties();
		//FileInputStream fi = new FileInputStream(System.getProperty("user-dir")+"\\src\\main\\java\\KbbAcademy\\resources\\GlobalData.properties");
		FileInputStream fis = new FileInputStream("C:\\Users\\krish\\eclipse-workspace\\SeleniumMavenProject\\src\\main\\resources\\Data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\krish\\Downloads\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
			
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();;
			driver = new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			//initilize edgedriver;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}
public void goTo() {
		
		driver.get("https://www.saucedemo.com/");
	}

	

}
