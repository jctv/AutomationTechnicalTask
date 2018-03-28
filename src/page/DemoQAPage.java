package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoQAPage {
	WebDriver driver;

	@FindBy(id = "name_3_firstname")
	WebElement firstNameTxt;

	@FindBy(id = "name_3_lastname")
	WebElement lastNameTxt;

	@FindBy(xpath = "//input[@value='single']")
	WebElement singleMaritalStatusRdo;

	@FindBy(xpath = "//input[@value='dance']")
	WebElement danceHobbyRdo;

	@FindBy(id = "dropdown_7")
	WebElement countrySlc;

	@FindBy(id = "mm_date_8")
	WebElement birthMonthtSlc;

	@FindBy(id = "dd_date_8")
	WebElement birthDaySlc;

	@FindBy(id = "yy_date_8")
	WebElement birthYearSlc;

	@FindBy(id = "phone_9")
	WebElement phoneNumberTxt;

	@FindBy(id = "username")
	WebElement usernameTxt;

	@FindBy(id = "email_1")
	WebElement emailAddressTxt;

	@FindBy(id = "description")
	WebElement aboutMeTxt;

	@FindBy(id = "password_2")
	WebElement passwordTxt;

	@FindBy(id = "confirm_password_password_2")
	WebElement confirmPassTxt;

	@FindBy(name = "pie_submit")
	WebElement submitBtn;
	
	@FindBy(xpath = "//p[contains(text(),'Thank you for your registration')]")
	WebElement confirmationLbl;
	
	

	public DemoQAPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void completeCreateNewAccount(String username, String email) {

		this.waitUntilPageLoads();
		// Completeing the create new account form
		firstNameTxt.sendKeys("firstName");
		lastNameTxt.sendKeys("LastName");
		waitForElementToClick(singleMaritalStatusRdo);
		waitForElementToClick(danceHobbyRdo);
		this.selectOption(countrySlc, "Costa Rica");
		this.selectOption(birthMonthtSlc, "5");
		this.selectOption(birthDaySlc, "30");
		this.selectOption(birthYearSlc, "1990");
		usernameTxt.sendKeys(username);
		aboutMeTxt.sendKeys("I'm an automation QA");
		emailAddressTxt.sendKeys(email);
		phoneNumberTxt.sendKeys("1209889012334344");
		passwordTxt.sendKeys("AB900bmx!@1234QOL");
		confirmPassTxt.sendKeys("AB900bmx!@1234QOL");

	}

	public void submitAccountCreationForm() {
		waitForElementToClick(submitBtn);
	}
	public boolean verifyUserRegistration() {
		return confirmationLbl.isDisplayed();
	}
	// Customized method to wait before try a click
	public void waitForElementToClick(final WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			this.waitUntilPageLoads();
		} catch (Exception e) {
			System.out.println("Unable to perform the click on element " + e.getMessage());
		}

	}

	// Customized Method to wait the page to be loaded
	public void waitUntilPageLoads() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (!js.executeScript("return document.readyState").toString().equals("complete")) {
			try {
				Thread.sleep(1000);
				System.out.print(" Loading Page");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	// select a value from a select option element
	public void selectOption(WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
	}

}
