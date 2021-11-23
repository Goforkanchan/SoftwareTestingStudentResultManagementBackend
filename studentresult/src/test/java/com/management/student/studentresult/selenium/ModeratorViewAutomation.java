/**
 * 
 */
package com.management.student.studentresult.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.management.student.studentresult.vo.QueryVO;

/**
 * @author PRATAP
 *
 */
public class ModeratorViewAutomation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueryVO view1 = new QueryVO("MT2020093", "CS201", "Software Testing", 2, 2021);

		final String MOD_ID = "MOD2020093";
		final String MOD_PASS = "sujit123";
		List<QueryVO> l = new ArrayList<QueryVO>();
		l.add(view1);
//		l.add(marks2);
//		l.add(marks3);
//		l.add(marks4);
//		l.add(marks5);

		WebDriver driver = null;

		for (int i = 0; i < l.size(); i++) {
			try {

				System.setProperty("webdriver.chrome.driver", "driver\\chromedriver_win32_96.exe");

				// Instantiate a ChromeDriver class.
				driver = new ChromeDriver();

				// Launch Website
				driver.navigate().to("http://localhost:4200/");

				// Maximize the browser
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				WebElement element = driver.findElement(
						By.xpath("/html/body/app-root/custom-navbar/nav/div/ul/custom-nav-button[1]/button"));
				element.click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.id("id")).sendKeys(MOD_ID);
				driver.findElement(By.id("password")).sendKeys(MOD_PASS);
				driver.findElement(By.id("Log-in")).click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				WebElement viewButton = driver.findElement(
						By.xpath("/html/body/app-root/custom-navbar/nav/div/div/ul/li[3]/custom-nav-button/button"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", viewButton);
				driver.findElement(By.id("queryRoll")).sendKeys(l.get(i).getRollNumber());
				Select term = new Select(driver.findElement(By.id("queryTerm")));
				term.selectByVisibleText(String.valueOf(l.get(i).getTerm()));
				Select subjCode = new Select(driver.findElement(By.id("querySubject")));
				subjCode.selectByVisibleText(
						String.valueOf(l.get(i).getSubjectCode() + "," + l.get(i).getSubjectName()));
				Select year = new Select(driver.findElement(By.id("queryYear")));
				year.selectByVisibleText(String.valueOf(l.get(i).getYear()));
				WebElement submitBtn = driver.findElement(By.xpath(
						"/html/body/app-root/div/div/div[2]/div/div/app-view-update/div[1]/query-form/div/div/form/div[3]/button[2]"));
				js.executeScript("arguments[0].click();", submitBtn);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				// closing the window
//				if (driver != null)
//					driver.close();
			}
		}
	}

}
