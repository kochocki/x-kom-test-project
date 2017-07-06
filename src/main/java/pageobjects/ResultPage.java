package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage extends Header {
	@FindBy(css = "#productList>.product-item:nth-child(1)>.image")
	private WebElement firstProduct;

	public void clickFirstProduct() {
		firstProduct.click();
	}
}
