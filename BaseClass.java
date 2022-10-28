package week8_Marathon;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class BaseClass {
	
	public ChromeDriver driver;
	public static Shadow shadow;
	public String fileName;
	
	@Parameters({"url", "user_name", "user_password"})
	@BeforeMethod
	public void precondition(String url, String user_name, String user_password) throws InterruptedException {
		
		//1. Launch ServiceNow application
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//2. Login with valid credentials username and password 
		//   Create your own credentials
		driver.findElement(By.id("user_name")).sendKeys(user_name);
		driver.findElement(By.id("user_password")).sendKeys(user_password);
		driver.findElement(By.id("sysverb_login")).click();

		//3. Click All
		shadow = new Shadow(driver);
		shadow.setImplicitWait(30);
		shadow.findElementByXPath("//div[@id='all']").click();
		
		

}
	@AfterMethod
	public void postCondition() {
//		driver.close();
}
	
	@DataProvider(name = "fetchData")
	public String[][] testdata() throws IOException {
		return ReadExcelData.data(fileName);
	}
}