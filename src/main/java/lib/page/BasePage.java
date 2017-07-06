package lib.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lib.test.Drivers;

public abstract class BasePage {

	public BasePage() {
		PageFactory.initElements(Drivers.driver, this);
		System.out.println("Current URL: " + Drivers.driver.getCurrentUrl());
	}

	public void waitUntilVisible(WebElement element) {
		new WebDriverWait(Drivers.driver, 30).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilInvisible(WebElement element) {
		new WebDriverWait(Drivers.driver, 30).until(ExpectedConditions.invisibilityOf(element));
	}

	public String getCurrentUrl() {
		return Drivers.driver.getCurrentUrl();
	}

}
