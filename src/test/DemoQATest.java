package test;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.DemoQAPage;

public class DemoQATest {
	public WebDriver driver;
	public DemoQAPage regsitrationPage;
	public Random randomGenerator;

	public DemoQATest() {
		// setup of the driver
		System.setProperty("webdriver.chrome.driver", "/Users/jtribin/Documents/Drivers/chromedriver");
		driver = new ChromeDriver();
		randomGenerator = new Random();
	}

	@Before
	public void setupTest() {
		regsitrationPage = new DemoQAPage(driver);
		// Open the page to start the test
		driver.get("http://demoqa.com/registration/");
	}

	@After
	public void teardown() {
		// driver.quit();
	}

	@Test
	public void registerNewUserAccount() {
		// random value generation to generate fake data
		int randomValue = randomGenerator.nextInt(100);
		regsitrationPage.completeCreateNewAccount("carlMen999999" + randomValue, "any" + randomValue + "@gmail.com");
		regsitrationPage.submitAccountCreationForm();
		assertTrue("User registration success", regsitrationPage.verifyUserRegistration());

	}

}
