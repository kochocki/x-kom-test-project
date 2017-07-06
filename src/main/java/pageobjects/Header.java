package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lib.page.BasePage;

public abstract class Header extends BasePage {
	@FindBy(className = "img-logo")
	private WebElement logo;
	@FindBy(css = ".search-text>.js-validate-without-mark")
	private WebElement searchTextField;
	@FindBy(css = "button[type='submit']")
	private WebElement searchButton;
	@FindBy(css = ".basket-status")
	private WebElement basket;
	@FindBy(css = ".hints-list>li:nth-child(1)>ul>li>a")
	private WebElement firstSuggestion;

	public void clickLogo() {
		logo.click();
	}

	public void setSearchText(String text) {
		searchTextField.sendKeys(text);

	}

	public void clickSearchButton() {
		searchButton.click();
	}

	public void clickBasket() {
		basket.click();
	}

	public ProductPage clickFirstSuggestion() {
		isLoaded(firstSuggestion);
		firstSuggestion.click();
		return new ProductPage();
	}
}
