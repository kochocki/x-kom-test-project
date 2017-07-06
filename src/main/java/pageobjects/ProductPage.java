package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageModels.ProductModel;
import pageUtils.PriceUtil;

public class ProductPage extends Header {
	@FindBy(className = "add-to-cart-product")
	private WebElement addToBasket;
	@FindBy(css = ".price-box>.clearfix>.price")
	private WebElement price;
	@FindBy(css = ".product-info>h1[itemprop='name']")
	private WebElement productName;

	public BasketPage addToBasket() {
		isLoaded(addToBasket);
		addToBasket.click();
		return new BasketPage();
	}

	public Double getProductPrice() {
		return PriceUtil.getOnlyPriceValue(price.getText());
	}

	public String getProductName() {
		return productName.getText();
	}

	public ProductModel getProduct() {
		return new ProductModel(getProductName(), getProductPrice());
	}

}
