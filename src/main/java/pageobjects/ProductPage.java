package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageModels.Product;
import pageUtils.PriceUtil;

public class ProductPage extends Header {
	@FindBy(className = "add-to-cart-product")
	private WebElement addToBasket;
	@FindBy(css = ".price-box>.clearfix>.price")
	private WebElement price;
	@FindBy(css = ".product-info>h1[itemprop='name']")
	private WebElement productName;
	@FindBy(id = "productQuantityInput")
	private WebElement quantity;

	public Product addToBasket() {
		waitUntilVisible(addToBasket);
		addToBasket.click();
		return getProduct();
	}

	public Double getProductPrice() {
		return PriceUtil.getOnlyPriceValue(price.getText());
	}

	public String getProductName() {
		return productName.getText();
	}

	public int getQuantity() {
		return Integer.parseInt(quantity.getAttribute("value"));
	}

	public ProductPage setQuantity(int quantity) {
		this.quantity.clear();
		this.quantity.sendKeys(String.valueOf(quantity));
		return this;
	}

	public Product getProduct() {
		return new Product(getProductName(), getProductPrice(), getQuantity());
	}

}
