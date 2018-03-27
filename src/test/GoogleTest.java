package test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.GoogleAccountPage;

public class GoogleTest {
	public WebDriver driver;
	public GoogleAccountPage accountPage;

	public GoogleTest() {
		// setup of the driver 
		System.setProperty("webdriver.chrome.driver", "/Users/jtribin/Documents/Drivers/chromedriver");
		driver = new ChromeDriver();
	}

	@Before
	public void setupTest() {
		accountPage = new GoogleAccountPage(driver);
		// Open the page to start the flow
		driver.get("https://accounts.google.com/");
	}

	@After
	public void teardown() {
		// driver.quit();
	}

	@Test
	public void createNewGoogleAccount() {
		accountPage.completeCreateNewAccount("carlMen12389026");
		accountPage.submitAccountCreationForm();
		accountPage.acceptPrivacyAndPolicy();
		assertTrue("Account created success", accountPage.getCreatedAccount().contains("carlMen12389026"));


	}

}
