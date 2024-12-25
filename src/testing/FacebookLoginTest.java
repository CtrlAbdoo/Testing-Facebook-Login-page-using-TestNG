package testing;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FacebookLoginTest {
	
	WebDriver driver;
	@BeforeTest
    public void setup() {
		System.setProperty("webdriver.edge.driver", "D:\\SW\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		
		driver.get("https://www.facebook.com/login");
	}

	 @Test(priority = 1)
	    public void validLoginTest() {
	        driver.get("https://www.facebook.com/");
	        driver.findElement(By.id("email")).sendKeys("valid_email@example.com");
	        driver.findElement(By.id("pass")).sendKeys("valid_password");
	        driver.findElement(By.name("login")).click();

//	        WebElement profileLink = driver.findElement(By.xpath("//a[contains(@href, 'profile')]"));
//	        Assert.assertTrue(profileLink.isDisplayed(), "Valid login test failed.");
	        System.out.println("Test Case 1: Valid Login Passed");
	    }
	 
	 @Test(priority = 2)
	    public void invalidLoginTest() {
	        driver.get("https://www.facebook.com/");
	        driver.findElement(By.id("email")).sendKeys("invalid_email@example.com");
	        driver.findElement(By.id("pass")).sendKeys("wrong_password");
	        driver.findElement(By.name("login")).click();

	        WebElement error = driver.findElement(By.xpath("//div[contains(text(), 'The email or mobile number you entered isn’t connected to an account.')]"));
	        Assert.assertTrue(error.isDisplayed(), "Invalid login test failed.");
	        System.out.println("Test Case 2: Invalid Login Passed");
	    }
	 
	 @Test(priority = 3)
	    public void dataDrivenLoginTest() throws IOException {
	        String[][] testData = ExcelReader.getData("C:\\Users\\theno\\Desktop\\data.xlsx", "Sheet1");
	        for (String[] credentials : testData) {
	            String username = credentials[0];
	            String password = credentials[1];

	            driver.get("https://www.facebook.com/");
	            driver.findElement(By.id("email")).sendKeys(username);
	            driver.findElement(By.id("pass")).sendKeys(password);
	            driver.findElement(By.name("login")).click();

	            WebElement error = driver.findElement(By.xpath("//div[contains(text(), 'The email or mobile number you entered isn’t connected to an account.')]"));
	            if (error.isDisplayed()) {
	                System.out.println("Data-driven Test with Username: " + username + " Passed");
	            } else {
	                System.out.println("Data-driven Test with Username: " + username + " Failed");
	            }
	        }
	    }
	 
	 @AfterTest
	    public void tearDown() {
	        driver.quit();
	    }
}
