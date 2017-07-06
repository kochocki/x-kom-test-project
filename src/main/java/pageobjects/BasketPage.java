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

	public BasketPage() {
		super();
		isLoaded(basket);
	}

	public String getEmptyBasketText() {
		isLoaded(emptyBasket);
		return emptyBasket.getText();
	}

	public Double getFirstProductOverallPrice() {
		isLoaded(firstProductOverallPrice);
		return PriceUtil.getOnlyPriceValue(firstProductOverallPrice.getText());
	}

	public Double getSummaryPrice() {
		isLoaded(summaryPrice);
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

}
