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

import com.management.student.studentresult.vo.MarksVO;

public class ModeratorAutomation {

	public static void main(String[] args) {

		// picked from session data, already exists
		MarksVO marks1 = new MarksVO("MT2020093", "CS201", "Software Testing", 2021, 2, 100, 50.5, "B");
		// used the id used during login operation
		MarksVO marks2 = new MarksVO("MOD2020093", "CS201", "Software Testing", 2021, 2, 100, 50.5, "B");
		// obtained marks>total marks
		MarksVO marks3 = new MarksVO("MT2020093", "CS201", "Software Testing", 2021, 2, 100, 500.0, "B");
		// changed id number; not existing in the database
		MarksVO marks4 = new MarksVO("MT2020010", "CS201", "Software Testing", 2021, 2, 100, 50.0, "B");
		// changed subject id; not existing in the database
		MarksVO marks5 = new MarksVO("MT2020010", "ST112", "Software Testing", 2021, 2, 100, 50.0, "B");

		final String MOD_ID = "MOD2020093";
		final String MOD_PASS = "sujit123";
		List<MarksVO> l = new ArrayList<MarksVO>();

		l.add(marks1);
		l.add(marks2);
		l.add(marks3);
		l.add(marks4);
		l.add(marks5);
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
				WebElement uploadButton = driver.findElement(
						By.xpath("/html/body/app-root/custom-navbar/nav/div/div/ul/li[2]/custom-nav-button/button"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", uploadButton);
				driver.findElement(By.id("rollNo")).sendKeys(l.get(i).getRollNo());
				Select term = new Select(driver.findElement(By.id("term")));
				term.selectByVisibleText(String.valueOf(l.get(i).getTerm()));
				Select subjCode = new Select(driver.findElement(By.id("subjCode")));
				subjCode.selectByVisibleText(
						String.valueOf(l.get(i).getSubjectCode() + "," + l.get(i).getSubjectName()));
				Select year = new Select(driver.findElement(By.id("year")));
				year.selectByVisibleText(String.valueOf(l.get(i).getYear()));
				driver.findElement(By.id("marksObtained")).sendKeys(String.valueOf(l.get(i).getMarksObtained()));
				driver.findElement(By.id("totMarks")).sendKeys(String.valueOf(l.get(i).getTotalMarks()));
				driver.findElement(By.id("grade")).sendKeys(l.get(i).getGrade());
				WebElement uploadbtn = driver.findElement(By
						.xpath("/html/body/app-root/div/div/div[2]/div/div/app-upload/div[1]/div/form/div[5]/button"));
				js.executeScript("arguments[0].click();", uploadbtn);
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
