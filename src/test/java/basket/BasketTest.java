package basket;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import lib.test.BaseTest;
import pageModels.ProductModel;
import pageobjects.BasketPage;
import pageobjects.MainPage;
import pageobjects.ProductPage;
import pageobjects.ResultPage;

public class BasketTest extends BaseTest {

	MainPage mainPage;
	BasketPage basketPage;
	ProductPage productPage;
	ResultPage resultPage;

	@BeforeMethod
	public void beforeTest() {
		super.beforeTest();
	}

	@Test
	public void emptyBasketTest() {
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.clickBasket();
		basketPage = new BasketPage();
		assertEquals(
				"Twój koszyk jest pusty.\nPrzejrzyj nasze produkty polecane na stronie g³ównej i wybierz coœ dla siebie.",
				basketPage.getEmptyBasketText());
	}

	@Test
	public void addOneProductTest() {
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		Double productPrice = productPage.getProductPrice();
		productPage.addToBasket();
		basketPage = new BasketPage();
		assertEquals(productPrice, basketPage.getFirstProductOverallPrice());
	}

	@Test
	public void addTwoDifferentProducts() {

		List<ProductModel> addedProducts = new ArrayList<ProductModel>();
		List<String> productNamesFromBasket;
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		addedProducts.add(productPage.getProduct());
		productPage.addToBasket();
		basketPage = new BasketPage();
		basketPage.setSearchText("ssd");
		basketPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		addedProducts.add(productPage.getProduct());
		productPage.addToBasket();
		basketPage = new BasketPage();
		productNamesFromBasket = basketPage.getAllProductNames();
		List<String> addedProductNames = addedProducts.stream().map(ProductModel::getName).collect(Collectors.toList());
		assertTrue(addedProductNames.containsAll(productNamesFromBasket)
				&& productNamesFromBasket.containsAll(addedProductNames));
	}

	@AfterMethod
	public void afterTest() {
		super.afterTest();
	}

}
