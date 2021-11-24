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
public class StudentViewAndRaiseObjectionAutomation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Picked from session data, record and replay
		QueryVO view1 = new QueryVO("MT2020093", "CS201", "Software Testing", 2, 2021);

		// After removing subject code and subject name, same marks are being fetched
		// which means
		// subject code and subject name becomes redundant
		QueryVO view2 = new QueryVO("MT2020093", null, null, 2, 2021);

		final String STUDENT_ID = "MT2020093";
		final String STUDENT_PASS = "aditya123";
		List<QueryVO> l = new ArrayList<QueryVO>();
		l.add(view1);
		l.add(view2);

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
				driver.findElement(By.id("id")).sendKeys(STUDENT_ID);
				driver.findElement(By.id("password")).sendKeys(STUDENT_PASS);
				driver.findElement(By.id("Log-in")).click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				WebElement viewButton = driver.findElement(
						By.xpath("/html/body/app-root/custom-navbar/nav/div/div/ul/li[3]/custom-nav-button/button"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", viewButton);
				Select term = new Select(driver.findElement(By.id("queryTerm")));
				term.selectByVisibleText(String.valueOf(l.get(i).getTerm()));
				Select subjCode = new Select(driver.findElement(By.id("querySubject")));
				if (l.get(i).getSubjectCode() != null || l.get(i).getSubjectName() != null)
					subjCode.selectByVisibleText(
							String.valueOf(l.get(i).getSubjectCode() + "," + l.get(i).getSubjectName()));
				Select year = new Select(driver.findElement(By.id("queryYear")));
				year.selectByVisibleText(String.valueOf(l.get(i).getYear()));
				WebElement submitBtn = driver.findElement(By.xpath(
						"/html/body/app-root/div/div/div[2]/div/div/app-view-update/div/query-form/div/div/form/div[3]/button[2]"));
				js.executeScript("arguments[0].click();", submitBtn);

				// Raising Objection
				WebElement objButton = driver.findElement(By.xpath(
						"/html/body/app-root/div/div/div[2]/div/div/results/div/div/custom-table/table/tbody/tr/td[8]/button"));
				js.executeScript("arguments[0].click();", objButton);

				WebElement submit1Btn = driver.findElement(By.xpath(
						"/html/body/app-root/div/div/div[2]/div/div/results/div/div/custom-submit-button/button"));
				js.executeScript("arguments[0].click();", submit1Btn);

				WebElement submit2Btn = driver.findElement(
						By.xpath("/html/body/app-root/div/div/div[2]/div/div/confirmation/div/div/button[2]"));
				js.executeScript("arguments[0].click();", submit2Btn);

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
