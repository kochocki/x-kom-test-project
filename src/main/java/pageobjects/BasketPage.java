package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import pageModels.Product;
import pageUtils.PriceUtil;

public class BasketPage extends Header {
	@FindBy(className = "basket-wrapper")
	private WebElement basket;
	@FindBy(id = "basketEmpty")
	private WebElement emptyBasket;
	@FindBy(className = "overallprice")
	private WebElement firstProductOverallPrice;
	@FindBy(className = "price-value-label")
	private WebElement summaryPrice;
	@FindBys({ @FindBy(css = ".basket-item") })
	private List<WebElement> productsWE;
	@FindBys({ @FindBy(css = ".basket-item>.totalprice-wrapper>.remove-product") })
	private List<WebElement> deleteProductButtons;
	@FindBys({ @FindBy(css = ".basket-item>.count-wrapper>span>.js-quantity-increment") })
	private List<WebElement> incrementQuantityButtons;
	@FindBys({ @FindBy(css = ".basket-item>.count-wrapper>span>.js-quantity-decrement") })
	private List<WebElement> decrementQuantityButtons;
	@FindBy(className = "spinner")
	private WebElement loader;

	public BasketPage() {
		super();
		waitUntilVisible(basket);
	}

	public String getEmptyBasketText() {
		waitUntilVisible(emptyBasket);
		return emptyBasket.getText();
	}

	public Double getFirstProductOverallPrice() {
		waitUntilVisible(firstProductOverallPrice);
		return PriceUtil.getOnlyPriceValue(firstProductOverallPrice.getText());
	}

	public Double getSummaryPrice() {
		waitUntilVisible(summaryPrice);
		return PriceUtil.getOnlyPriceValue(summaryPrice.getText());
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		for (WebElement productWE : productsWE) {
			String name = productWE.findElement(By.className("name")).getText();
			Double price = PriceUtil.getOnlyPriceValue(productWE.findElement(By.className("price")).getText());
			int quantity = Integer
					.parseInt(productWE.findElement(By.className("js-quantity-input")).getAttribute("value"));
			products.add(new Product(name, price, quantity));
		}
		return products;
	}

	public BasketPage removeProduct(int index) {
		deleteProductButtons.get(index).click();
		return this;
	}

	public BasketPage incrementProductQuantity(int productIndex) {
		incrementQuantityButtons.get(productIndex).click();
		waitForLoader();
		return this;
	}

	public BasketPage decrementProductQuantity(int productIndex) {
		decrementQuantityButtons.get(productIndex).click();
		waitForLoader();
		return this;
	}

	public void waitForLoader() {
		waitUntilVisible(loader);
		waitUntilInvisible(loader);
	}

}
