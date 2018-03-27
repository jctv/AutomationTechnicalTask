package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleAccountPage {
	WebDriver driver;

	@FindBy(xpath = "//span[text()='Create account']")
	WebElement createAccountSpan;

	@FindBy(id = "FirstName")
	WebElement firstNameTxt;

	@FindBy(id = "LastName")
	WebElement lastNameTxt;

	@FindBy(id = "GmailAddress")
	WebElement usernameTxt;

	@FindBy(id = "Passwd")
	WebElement passTxt;

	@FindBy(id = "PasswdAgain")
	WebElement confirmPassTxt;

	@FindBy(xpath = "//*[@id=\"BirthMonth\"]/div")
	WebElement birthMonthTxt;

	@FindBy(id = "BirthDay")
	WebElement birthDayTxt;

	@FindBy(id = "BirthYear")
	WebElement birthYearTxt;

	@FindBy(xpath = "//*[@id=\"Gender\"]/div[1]")
	WebElement genderFormElementTxt;

	@FindBy(id = "RecoveryPhoneNumber")
	WebElement recoveryPhoneNumberTxt;

	@FindBy(id = "RecoveryEmailAddress")
	WebElement recoveryEmailAddressTxt;

	@FindBy(xpath = "//*[@id='CountryCode']/div[1]")
	WebElement locationTxt;

	@FindBy(id = "submitbutton")
	WebElement submitBtn;

	@FindBy(xpath = "//*[@id=':27']/div")
	WebElement confirmCountryOpt;

	@FindBy(id = "header-text-div")
	WebElement privacyAndTermsTtl;

	@FindBy(id = "tos-scroll-button")
	WebElement scrollBtn;

	@FindBy(id = "iagreebutton")
	WebElement iagreebuttonBtn;

	@FindBy(xpath = "//*[contains(text(), 'Your new email')]")
	WebElement newAccountAdressLbl;

	//

	public GoogleAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void completeCreateNewAccount(String username) {
		// Open the page to start the flow
		driver.get("https://accounts.google.com/");
		this.waitUntilPageLoads();

		// Redirects the user to the create account form
		this.waitForElementToClick(createAccountSpan);

		// Completeing the create new account form
		firstNameTxt.sendKeys("firstName");
		lastNameTxt.sendKeys("LastName");
		usernameTxt.sendKeys(username);
		passTxt.sendKeys("ABC900bmx");
		confirmPassTxt.sendKeys("ABC900bmx");
		birthMonthTxt.sendKeys("May");
		birthDayTxt.sendKeys("30");
		birthYearTxt.sendKeys("1990");
		genderFormElementTxt.sendKeys("Male");
		recoveryPhoneNumberTxt.sendKeys("88574012");
		recoveryEmailAddressTxt.sendKeys("any@gmail.com");
		locationTxt.sendKeys("Costa Rica");
		waitForElementToClick(confirmCountryOpt);

	}

	public void submitAccountCreationForm() {
		waitForElementToClick(submitBtn);
	}

	public boolean acceptPrivacyAndPolicy() {
		if (privacyAndTermsTtl.isDisplayed()) {
			while (scrollBtn.isDisplayed()) {
				waitForElementToClick(scrollBtn);
			}
			waitForElementToClick(iagreebuttonBtn);
			return true;
		}
		return false;
	}

	public String getCreatedAccount() {
		return newAccountAdressLbl.getText();
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

	public void scrollPage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, -400);");
	}
}
