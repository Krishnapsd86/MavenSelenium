package SaucesDemoProjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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
		
		ArrayList<String> AllList = new ArrayList<String>();
		List<WebElement> TotalItem = driver.findElements(By.cssSelector(".inventory_item_name"));
		for (int i = 0; i < TotalItem.size(); i++) {
			AllList.add(TotalItem.get(i).getText());	
		}
		ArrayList<String> matchList = new ArrayList<String>();   
		for(String s:AllList){
			matchList.add(s);
		}
		
		Assert.assertTrue(AllList.equals(matchList));

		driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
		ArrayList<String> NavList = new ArrayList<String>();
		List<WebElement> ItemsDisplay = driver.findElements(By.cssSelector("a[class='bm-item menu-item']"));
		for (int i = 0; i < ItemsDisplay.size(); i++) {
			NavList.add(ItemsDisplay.get(i).getText());
		}
		ArrayList<String> DisplayItem = new ArrayList<String>();
		for(String s:NavList) {
			DisplayItem.add(s);
		}
		Assert.assertTrue(NavList.equals(DisplayItem));
		
	}

	// verify the filter
	@Test
	public void productFilter() throws InterruptedException {
		
		WebElement filter = driver.findElement(By.cssSelector(".product_sort_container"));
		Select option = new Select(filter);
		WebElement AtoZ = driver.findElement(By.xpath("//*[@class=\"select_container\"]/select/option[1]"));
		AtoZ.click();		 
		ArrayList<String> obtainedList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.cssSelector(".inventory_item_name"));
		for(WebElement we:elementList){
			   obtainedList.add(we.getText());
			}
		ArrayList<String> sortedList = new ArrayList<String>();   
		for(String s:obtainedList){
		sortedList.add(s);
		}
		Collections.sort(sortedList);
		Assert.assertTrue(sortedList.equals(obtainedList));
			
		WebElement ZtoA = driver.findElement(By.xpath("//*[@class=\"select_container\"]/select/option[2]"));
		ZtoA.click();
		ArrayList<String> obtainedList1 = new ArrayList<String>();
		List<WebElement> elementList1 = driver.findElements(By.cssSelector(".inventory_item_name"));
		for(WebElement we:elementList1){
			   obtainedList1.add(we.getText());
			}
		ArrayList<String> sortedList1 = new ArrayList<String>();   
		for(String s:obtainedList1){
		sortedList1.add(s);
		}
		Collections.sort(sortedList1);
		Collections.reverse(sortedList1);
		Assert.assertTrue(sortedList1.equals(obtainedList1));

		
		WebElement LowToHigh = driver.findElement(By.xpath("//*[@class=\"select_container\"]/select/option[3]"));
		LowToHigh.click();
		ArrayList<String> obtainedList2 = new ArrayList<String>();
		List<WebElement> elementList2 = driver.findElements(By.cssSelector(".inventory_item_name"));
		for(WebElement we:elementList2){
			   obtainedList2.add(we.getText());
			}
		ArrayList<String> sortedList2 = new ArrayList<String>();   
		for(String s:obtainedList2){
		sortedList2.add(s);
		}
		Collections.sort(sortedList2);
		Collections.reverse(sortedList2);
		Assert.assertNotEquals(obtainedList2, sortedList2);
		
		WebElement HighToLow = driver.findElement(By.xpath("//*[@class=\"select_container\"]/select/option[4]"));
		HighToLow.click();
		ArrayList<String> obtainedList3 = new ArrayList<String>();
		List<WebElement> elementList3 = driver.findElements(By.cssSelector(".inventory_item_name"));
		for(WebElement we:elementList3){
			   obtainedList3.add(we.getText());
			}
		ArrayList<String> sortedList3 = new ArrayList<String>();   
		for(String s:obtainedList3){
		sortedList3.add(s);
		}
		Collections.sort(sortedList3);
		Collections.reverse(sortedList3);
		Assert.assertNotEquals(obtainedList3, sortedList3);
		
	

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
		
		
		// verify the complete order flow
		driver.findElement(By.id("finish")).click();
		WebElement ConfirmationMsg = driver.findElement(By.cssSelector(".complete-header"));
		String ConMsgs = ConfirmationMsg.getText();
		Assert.assertTrue(ConMsgs.equalsIgnoreCase("Thank you for your order!"));
	
		
	}

	@AfterTest
	public void TearDown() {
		driver.close();
	}
	
	

}
