package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lib.page.BasePage;

public abstract class Header extends BasePage {
	@FindBy(className = "img-logo")
	private WebElement logo;
	@FindBy(css = ".search-text>input[type='text']")
	private WebElement searchTextField;
	@FindBy(className = "icon-search")
	private WebElement searchButton;

	public void clickLogo() {
		logo.click();
	}

	public void setSearchText(String text) {
		searchTextField.sendKeys(text);
	}

	public void clickSearchButton() {
		searchButton.click();
	}
}
