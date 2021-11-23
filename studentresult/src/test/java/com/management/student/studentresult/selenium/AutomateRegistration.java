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

class UserDetails {

	private String firstname;
	private String lastname;
	private String gender;
	private String role;
	private String contactno;
	private String dob;
	private String email;
	private String password;
	private String confirm;
	private String id;
	private String address;

	public UserDetails(String firstname, String lastname, String gender, String contactno, String role, String dob,
			String email, String password, String confirm, String id, String address) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.role = role;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.id = id;
		this.address = address;
		this.contactno = contactno;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getGender() {
		return gender;
	}

	public String getRole() {
		return role;
	}

	public String getContactno() {
		return contactno;
	}

	public String getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirm() {
		return confirm;
	}

	public String getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

}

public class AutomateRegistration {

	public static void main(String[] args) {

		// testcases for registration input fields
		// record and replay, from user session data
		UserDetails user1 = new UserDetails("Bishwajeet", "Sharma", "Male", "7588661639", "student", "1999-04-15",
				"bishwajeet@iiitb.org", "Bishu@1234", "Bishu@1234", "MT2020188", "Sahibganj");
		// changing the key value pairs

		// change the phone number length from 10 to 11
		UserDetails user2 = new UserDetails("Pratap", "Chandra", "Male", "70000016391", "student", "1997-07-25",
				"pratap@iiitb.org", "Pratap@1234", "Pratap@1234", "MT2020001", "Kalighat");

		// different password and confirm password field
		UserDetails user3 = new UserDetails("Kanchan", "Mahajan", "Female", "7588661000", "student", "1999-04-15",
				"kanchan@iiitb.org", "Kanchu@1234", "GoForK@1234", "MT2020003", "Nanded");

		// provide a corrupted date
		UserDetails user4 = new UserDetails("Aditya", "Saha", "Male", "7500661000", "student", "2022-01-01",
				"aditya@iiitb.org", "Aditya@1234", "Aditya@1234", "MT2020011", "Dankuni");

		// provide an Alphanumeric username
		UserDetails user5 = new UserDetails("@12345", "&^%", "Male", "7500561000", "moderator", "1992-01-01",
				"moderator@iiitb.org", "Mod@1234", "Mod@1234", "MOD2020011", "Bangalore");

		List<UserDetails> l = new ArrayList<UserDetails>();

		l.add(user1);
		l.add(user2);
		l.add(user3);
		l.add(user4);
		l.add(user5);
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
						By.xpath("/html/body/app-root/custom-navbar/nav/div/ul/custom-nav-button[2]/button"));
				element.click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.id("firstname")).sendKeys(l.get(i).getFirstname());
				driver.findElement(By.id("lastname")).sendKeys(l.get(i).getLastname());
				Select gender = new Select(driver.findElement(By.id("gender")));
				gender.selectByVisibleText(l.get(i).getGender());
				driver.findElement(By.id("contactno")).sendKeys(l.get(i).getContactno());
				Select role = new Select(driver.findElement(By.id("role")));
				role.selectByVisibleText(l.get(i).getRole());
				driver.findElement(By.id("id")).sendKeys(l.get(i).getId());
				driver.findElement(By.id("address")).sendKeys(l.get(i).getAddress());
				driver.findElement(By.id("dateofbirth")).sendKeys(l.get(i).getDob());
				driver.findElement(By.id("email")).sendKeys(l.get(i).getEmail());
				driver.findElement(By.id("password")).sendKeys(l.get(i).getPassword());
				driver.findElement(By.id("confirm")).sendKeys(l.get(i).getConfirm());
				// Waiting for the page to load
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("scrollBy(0, 5000)");
				WebElement registerelement = driver.findElement(
						By.xpath("/html/body/app-root/div/div/div[2]/div/div/app-registerform/div/div/form/button[1]"));
//			registerelement.click();
				js.executeScript("arguments[0].click();", registerelement);
//			driver.findElement(By.id("register")).click();

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
