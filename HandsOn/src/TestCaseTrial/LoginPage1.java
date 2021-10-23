package TestCaseTrial;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginPage1 {
	WebDriver driver;

	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("file:///C:/Users/palla/Downloads/Pallavi%20JBK%20Data/Softwares/javabykiran-Selenium-Softwares/Offline%20Website/index.html");
	}

	@Test(priority = 1)
	public void checkTitle() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "JavaByKiran | Log in");
	}

	@Test(priority = 2)
	public void checkLabel() {
		String label = driver.findElement(By.xpath("//b")).getText();
		System.out.println(label);
		Assert.assertEquals(label, "Java By Kiran");
	}

	@Test(priority = 3)
	public void checkHeading() {
		String heading = driver.findElement(By.xpath("//h4")).getText();
		System.out.println(heading);
		Assert.assertEquals(heading, "JAVA | SELENIUM | PYTHON");
	}
	@Test(priority = 4)
	public void checkLoginMessage() {
		String message = driver.findElement(By.xpath("//p")).getText();
		System.out.println(message);
		Assert.assertEquals(message, "Sign in to start your session");
	}

	@Test(priority = 5)
	public void checkEmailPlaceholder() {
		String actEmailPlaceHolder = driver.findElement(By.id("email")).getAttribute("placeholder");
		Assert.assertEquals(actEmailPlaceHolder, "Email");
	}
	@Test(priority = 6)
	public void checkPasswordPlaceholder() {
		String actEmailPlaceHolder = driver.findElement(By.id("password")).getAttribute("placeholder");
		Assert.assertEquals(actEmailPlaceHolder, "Password");
	}

	@Test(priority = 7)
	public void checkCrediantialsEmailId() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("pallavi@gmail.com");
		driver.findElement(By.xpath("//button")).click();
		String actCredtentials = driver.findElement(By.id("email_error")).getText();
		Assert.assertEquals(actCredtentials, "Please enter email as kiran@gmail.com");
	}

	@Test(priority = 8)
	public void checkCrediantialsPassword() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("pallavi");
		driver.findElement(By.xpath("//button")).click();
		String actCredtentials = driver.findElement(By.id("password_error")).getText();
		System.out.println(actCredtentials);
		Assert.assertEquals(actCredtentials, "Please enter password 123456");
	}

	@Test(priority = 11)
	public void verifyLogin() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("/html/body/div/div[2]/form/div[3]/div/button")).click();
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "JavaByKiran | Dashboard");
	}
	/*
	 * @Test(priority = 9) public void checkRegistration() { String actReg =
	 * driver.findElement(By.xpath("/html/body/div/div[2]/a")).getText();
	 * Assert.assertEquals(actReg, "Register a new membership"); }
	 * 
	 * @Test(priority = 10) public void checkRegistrationDetails() {
	 * driver.findElement(By.xpath("/html/body/div/div[2]/a")).click(); String
	 * actReg = driver.getTitle(); Assert.assertEquals(actReg,
	 * "JavaByKiran | Registration Page"); }
	 */
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
