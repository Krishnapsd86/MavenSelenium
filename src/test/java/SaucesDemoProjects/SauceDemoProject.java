package SaucesDemoProjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoProject {
	WebDriver driver;

	@Test
	// verify the login for user
	public void Login() throws IOException {
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\krish\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
	

		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		// verify dashboard logo
		String pageHeader = driver.findElement(By.cssSelector(".header_label")).getText();
		Assert.assertTrue(true, pageHeader);
		System.out.println("DashBoard Logo : "+pageHeader);

	}

	@Test

	// verify number all items in navigation bar
	public void ProductPage() {
		List<WebElement> TotalItem = driver.findElements(By.cssSelector(".inventory_item_name"));
		System.out.println(TotalItem.size());
//		for (int i = 0; i < TotalItem.size(); i++) {
//			
//			
//		}
		Assert.assertEquals(TotalItem.size(), 6);

		driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
		List<WebElement> ItemsDisplay = driver.findElements(By.cssSelector("a[class='bm-item menu-item']"));
		for (int i = 0; i < ItemsDisplay.size(); i++) {
			ItemsDisplay.get(i).getText();
		}
		Assert.assertEquals(ItemsDisplay.size(), 4);
	}

	// verify the filter
	@Test
	public void productFilter() throws InterruptedException {
		
		WebElement filter = driver.findElement(By.cssSelector(".product_sort_container"));
		Select option = new Select(filter);
		WebElement AtoZ = driver.findElement(By.xpath("//*[@class=\"select_container\"]/select/option[1]"));
		AtoZ.click();
		List<WebElement> defaultList = driver.findElements(By.cssSelector(".inventory_item_name"));
		for (int i = 0; i < defaultList.size(); i++) {
			String List = defaultList.get(i).getText();
		}		
		WebElement ZtoA = driver.findElement(By.xpath("//*[@class=\"select_container\"]/select/option[2]"));
		ZtoA.click();
		List<WebElement> secondList = driver.findElements(By.cssSelector(".inventory_item_name"));
		for (int i = 0; i < secondList.size(); i++) {
			String second = secondList.get(i).getText();
		}
		
		Assert.assertNotSame(defaultList, secondList);
		
		WebElement LowToHigh = driver.findElement(By.xpath("//*[@class=\"select_container\"]/select/option[3]"));
		LowToHigh.click();
		// option.selectByIndex(2);
		List<WebElement> thirdList = driver.findElements(By.cssSelector(".inventory_item_name"));
		for (int i = 0; i < thirdList.size(); i++) {
			String third = thirdList.get(i).getText();
		}
		
		WebElement HighToLow = driver.findElement(By.xpath("//*[@class=\"select_container\"]/select/option[4]"));
		HighToLow.click();
		// option.selectByIndex(3);
		List<WebElement> forthList = driver.findElements(By.cssSelector(".inventory_item_name"));
		for (int i = 0; i < forthList.size(); i++) {
			String four = forthList.get(i).getText();
		}
		
		Assert.assertNotSame(thirdList, forthList);

	}

	// verify the add to card functionality
	
	@Test(dependsOnMethods = { "productFilter" })
	public void addToCart() throws InterruptedException {
		List<WebElement> addButton = driver.findElements(By.cssSelector("button[class*='btn btn_primary']"));
		String beforeClick = addButton.get(0).getText();
		System.out.println("Before Adding : "+beforeClick);
		for (int i = 0; i < addButton.size(); i++) {
			addButton.get(i).click();
		}

		Assert.assertNotEquals("Remove", beforeClick);
		
		driver.findElement(By.cssSelector(".shopping_cart_link")).click();
		List<WebElement> CartProduct = driver.findElements(By.xpath("//div[@class='cart_list']"));
		for (int i = 0; i < CartProduct.size(); i++) {
			String AddedInCart = CartProduct.get(i).getText();
			//System.out.println(AddedInCart);
		}
		Assert.assertEquals(CartProduct.size(),CartProduct.size());
		
		driver.findElement(By.id("checkout")).click();

	}
	
	@Test(dependsOnMethods = { "addToCart" })
	public void CheckOutPage() {
		
		driver.findElement(By.id("first-name")).sendKeys("First");
		driver.findElement(By.id("last-name")).sendKeys("Second");
		driver.findElement(By.id("postal-code")).sendKeys("1234");
		driver.findElement(By.id("continue")).click();
		
		List<WebElement> CheckoutItems =driver.findElements(By.xpath("//div[@class='cart_item']"));
		int totalItems =CheckoutItems.size();		
		Assert.assertEquals(totalItems, CheckoutItems.size());
		
	
		// verify the calculation of price on checkout page
		List<WebElement> TotalPrices =driver.findElements(By.cssSelector("div[class=\"inventory_item_price\"]"));
	double sum =0;
		for (int i = 0; i < TotalPrices.size(); i++) { 
			String prices = TotalPrices.get(i).getText();
			String priceValue = prices.replace("$", "");
			double price = Double.parseDouble(priceValue);
			sum = sum + price;
			
		}
		System.out.println("Item Total $"+sum);
		//.summary_tax_label
		String Tax = driver.findElement(By.cssSelector(".summary_tax_label")).getText();		
		String taxLabel = Tax.replace("Tax: $", "");
		double taxLabels = Double.parseDouble(taxLabel);
		sum = taxLabels + sum;
		System.out.println("Total $:"+sum);
		
		String TotalSum = driver.findElement(By.cssSelector(".summary_info_label.summary_total_label")).getText();
		String Totsum = TotalSum.replace("Total: $", "");
		double totalSummary = Double.parseDouble(Totsum);
		System.out.println("Total Summary $:"+totalSummary);
		Assert.assertEquals(sum, totalSummary);
		
		
		
		driver.findElement(By.id("finish")).click();
		WebElement ConfirmationMsg = driver.findElement(By.cssSelector(".complete-header"));
		String ConMsgs = ConfirmationMsg.getText();
		Assert.assertTrue(ConMsgs.equalsIgnoreCase("Thank you for your order!"));
	
		
	}

	
	// verify the complete order flow
	

}
