package TestCaseTrial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UsersPage1 {
	WebDriver driver;

	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(
				"file:///C:/Users/palla/Downloads/Pallavi%20JBK%20Data/Softwares/javabykiran-Selenium-Softwares/Offline%20Website/pages/examples/users.html");
	}

	@Test(priority = 1)
	public void checkTitle() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "JavaByKiran | User");
	}

	@Test(priority = 2)
	public void checkCurrentURL() {
		String title = driver.getCurrentUrl();
		System.out.println(title);
		String exp = "file:///C:/Users/palla/Downloads/Pallavi%20JBK%20Data/Softwares/javabykiran-Selenium-Softwares/Offline%20Website/pages/examples/users.html";
		Assert.assertEquals(title, exp);
	}

	@Test(priority = 3)
	public void checkLabel() {
		String label = driver.findElement(By.xpath("//b[text()='Java By Kiran']")).getText();
		System.out.println(label);
		String exp = "Java By Kiran";
		Assert.assertEquals(label, exp);
	}

	@Test(priority = 4)
	public void checkLabel1() {
		String label = driver.findElement(By.xpath("//h1")).getText();
		System.out.println(label);
		String exp = "Users";
		Assert.assertEquals(label, exp);
	}

	@Test(priority = 5)
	public void checkFooterLink() throws Exception {
		driver.findElement(By.xpath("//a[text()='JavaByKiran']")).click();
		Thread.sleep(5000);
		String url = driver.getTitle();
		System.out.println(url);
		String expUrl = "JavaByKiran | User";
		Assert.assertEquals(url, expUrl);
	}

	@Test(priority = 6)
	public void addUserButton() {
		driver.findElement(By.xpath("//button[text()='Add User']")).click();
		String url = driver.getTitle();
		System.out.println(url);
		String expUrl = "JavaByKiran | Add User";
		Assert.assertEquals(url, expUrl);
		driver.navigate().back();
		String url1 = driver.getTitle();
		System.out.println("Back to User Page with title...." + url1);
	}

	@Test(priority = 7)
	public void checkHeading1() {
		String label = driver.findElement(By.xpath("//h3")).getText();
		System.out.println(label);
		String exp = "User List";
		Assert.assertEquals(label, exp);
	}

	@Test(priority = 8)
	public void checkColumnNames() throws Exception {
		FileInputStream fis = new FileInputStream("d.xls");
		Workbook w = Workbook.getWorkbook(fis);
		Sheet s = w.getSheet(0);
		int rows = s.getRows();
		int column = s.getColumns();
		System.out.println("rows>> " + rows);
		System.out.println("column>> " + column);
		ArrayList<String> expMenusList = new ArrayList<>();
		ArrayList<String> actualmenusList = new ArrayList<>();
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < 1; j++) {
				Cell c = s.getCell(i, 0);// col,rows
				// System.out.println("Current data....."+c.getContents());
				String val = c.getContents();
				expMenusList.add(val);
			}
		}
		List<WebElement> menus = driver.findElements(By.xpath("//tr//th"));
		for (WebElement menu : menus) {
			String courseName = menu.getText();
			actualmenusList.add(courseName);
		}
		System.out.println("1" + expMenusList);
		System.out.println("2" + actualmenusList);
		Assert.assertEquals(actualmenusList, expMenusList);
	}

	@Test(priority = 9)
	public void checkColumn() throws Exception {
		FileInputStream fis = new FileInputStream("d.xls");
		Workbook w = Workbook.getWorkbook(fis);
		Sheet s = w.getSheet(0);
		int rows = s.getRows();
		int column = s.getColumns();
		System.out.println("rows>> " + rows);
		System.out.println("column>> " + column);
		ArrayList<String> expMenusList = new ArrayList<>();
		for (int i = 1; i < rows; i++) {
			Cell c = s.getCell(2, i);// col,rows
			System.out.println("Current data....." + c.getContents());
			String val = c.getContents();
			expMenusList.add(val);

		}
	}
	@Test(priority = 10)
	public void emailEndsWith() throws Exception {
		FileInputStream fis = new FileInputStream("d.xls");
		Workbook w = Workbook.getWorkbook(fis);
		Sheet s = w.getSheet(0);
		int rows = s.getRows();
		int column = s.getColumns();
		ArrayList<String> expMenusList = new ArrayList<>();
		for (int i = 1; i < rows; i++) {
			Cell c = s.getCell(2, i);// col,rows
			System.out.println("Current data....." + c.getContents());
			String val = c.getContents();
			if(val.contains(".com"))
			expMenusList.add(val);
		}
		ArrayList<String> actualmenusList = new ArrayList<>();
		
		List<WebElement> menus = driver.findElements(By.xpath("//tr//td[3]"));
		for (WebElement menu : menus) {
			String courseName = menu.getText();
			actualmenusList.add(courseName);
			System.out.println("actualmenusList  "+actualmenusList);
		}
		Assert.assertEquals(actualmenusList, expMenusList);
	}
	@Test(priority = 11)
	public void addUser() {
		driver.findElement(By.xpath("//button[text()='Add User']")).click();
		String url = driver.getTitle();
		String expUrl = "JavaByKiran | Add User";
		Assert.assertEquals(url, expUrl);

	}

	@Test(priority = 12)
	public void deleteUser1and3() throws Exception {
		driver.findElement(By.xpath("//tr[2]//td[8]")).click();
		Thread.sleep(2000);
		String act = driver.switchTo().alert().getText();
		Assert.assertEquals(act, "You can not delete Default User");
	}
	@Test(priority = 13)
	public void deleteUser2and4() throws Exception {
		driver.findElement(By.xpath("//tr[3]//td[8]")).click();
		Thread.sleep(2000);
		String act = driver.switchTo().alert().getText();
		driver.switchTo().alert().dismiss();
		Assert.assertEquals(act, "Are you sure you want to delete this user");
	}
	@Test(priority = 14)
	public void deleteUser2and4Accept() throws Exception {
		driver.findElement(By.xpath("//tr[5]//td[8]")).click();
		Thread.sleep(2000);
		String s=driver.switchTo().alert().getText();
		System.out.println("First alert..."+s);
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		String act = driver.switchTo().alert().getText();
		System.out.println("Second alert..."+act);
		Assert.assertEquals(act, "User deleted successfully.");
	}
	@Test(priority = 15)
	public void checkLogoutLink() {
		driver.findElement(By.xpath("//a[text()='LOGOUT']")).click();
		String url = driver.getTitle();
		String expUrl = "JavaByKiran | Log in";
		Assert.assertEquals(url, expUrl);
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
