package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

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
	@FindBys({ @FindBy(css = ".basket-item-group>.basket-item>.description-wrapper>.name") })
	private List<WebElement> productNames;

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

	public List<String> getAllProductNames() {
		List<String> names = new ArrayList<String>();
		for (WebElement we : productNames) {
			names.add(we.getText());
		}
		return names;
	}

}
