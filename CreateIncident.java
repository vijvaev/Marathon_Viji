package week8_Marathon;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;


public class CreateIncident  extends BaseClass{
		
	@BeforeTest
	public void setFile() {
		fileName="TestData_CreateIncident";
	}
	
	
	@Test(dataProvider="fetchData")
	public void runCreateIncident(String short_description) throws InterruptedException {
			
			//Shadow shadow = new Shadow(driver);
			shadow.setImplicitWait(30);
			//4.Click  Incidents in Filter navigator
			shadow.findElementByXPath("//span[text()='Incidents']").click();
			Thread.sleep(3000);
			//5. Click on Create new option and fill the mandatory fields
			Thread.sleep(3000);
			WebElement eleFrame= shadow.findElementByXPath(("//iframe[@title='Main Content']"));
			driver.switchTo().frame(eleFrame);
			driver.findElement(By.xpath("//button[text()='New']")).click();
			String text2 = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");

			System.out.println("Inident NUmber"+text2);
			driver.findElement(By.id("incident.short_description")).sendKeys(short_description);
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			driver.switchTo().defaultContent();

			//6. Verify the newly created incident by getting the incident number and put it in search field and 
			//   enter then verify the instance number created or not
			Thread.sleep(1000);
			WebElement eleFrame2 = shadow.findElementByXPath("//iframe[@id='gsft_main']");
			driver.switchTo().frame(eleFrame2);
			WebElement numberDropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
			Select numberDD=new Select(numberDropDown);
			numberDD.selectByVisibleText("Number");
			driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(text2,Keys.ENTER);
			driver.switchTo().defaultContent();
			
			//// Confirm incident exists !
			Thread.sleep(3000);
			WebElement eleframe3 = shadow.findElementByXPath("//iframe[@id='gsft_main']");
			driver.switchTo().frame(eleframe3);
			String text = driver.findElement(By.xpath("//a[text()='Number']/following::a[@class='linked formlink']")).getText();
			System.out.println(text); 

		}
	}

