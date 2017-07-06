package basket;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import lib.test.BaseTest;
import pageModels.Product;
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
		List<Product> addedProducts = new ArrayList<Product>();
		List<Product> productsFromBasket;
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		addedProducts.add(productPage.addToBasket());
		basketPage = new BasketPage();
		productsFromBasket = basketPage.getAllProducts();
		assertTrue(compareListOfProducts(addedProducts, productsFromBasket));
	}

	@Test
	public void addTwoDifferentProducts() {
		List<Product> addedProducts = new ArrayList<Product>();
		List<Product> productsFromBasket;
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		addedProducts.add(productPage.addToBasket());
		basketPage = new BasketPage();
		basketPage.setSearchText("ssd");
		basketPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		addedProducts.add(productPage.addToBasket());
		basketPage = new BasketPage();
		productsFromBasket = basketPage.getAllProducts();
		assertTrue(compareListOfProducts(addedProducts, productsFromBasket));
	}

	@Test
	public void addTwoTheSameProductsManually() {
		List<Product> addedProducts = new ArrayList<Product>();
		List<Product> productsFromBasket;
		String currentUrl;
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		currentUrl = productPage.getCurrentUrl();
		addedProducts.add(productPage.addToBasket());
		basketPage = new BasketPage();
		goTo(currentUrl);
		productPage = new ProductPage();
		addedProducts.add(productPage.addToBasket());
		mergeLastProductWithExisting(addedProducts);
		basketPage = new BasketPage();
		productsFromBasket = basketPage.getAllProducts();
		assertTrue(compareListOfProducts(addedProducts, productsFromBasket));
	}

	@Test
	public void addTwoTheSameProductsAutomatically() {
		List<Product> addedProducts = new ArrayList<Product>();
		List<Product> productsFromBasket;
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		productPage.setQuantity(2);
		addedProducts.add(productPage.addToBasket());
		basketPage = new BasketPage();
		productsFromBasket = basketPage.getAllProducts();
		assertTrue(compareListOfProducts(addedProducts, productsFromBasket));
	}

	@Test
	public void addOneProductManuallyAndTwoTheSameAutomatically() {
		String currentUrl;
		List<Product> addedProducts = new ArrayList<Product>();
		List<Product> productsFromBasket;
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		currentUrl = productPage.getCurrentUrl();
		addedProducts.add(productPage.addToBasket());
		basketPage = new BasketPage();
		goTo(currentUrl);
		productPage = new ProductPage();
		productPage.setQuantity(2);
		addedProducts.add(productPage.addToBasket());
		mergeLastProductWithExisting(addedProducts);
		basketPage = new BasketPage();
		productsFromBasket = basketPage.getAllProducts();
		assertTrue(compareListOfProducts(addedProducts, productsFromBasket));
	}

	@Test
	public void addOneProductAndDeleteIt() {
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		productPage.addToBasket();
		basketPage = new BasketPage();
		basketPage.removeProduct(0);
		assertEquals(
				"Twój koszyk jest pusty.\nPrzejrzyj nasze produkty polecane na stronie g³ównej i wybierz coœ dla siebie.\nPrzywróæ koszyk",
				basketPage.getEmptyBasketText());
	}

	@Test
	public void incrementQuantityInBasketByButton() {
		List<Product> productsInBasketBeforeIncrement;
		List<Product> productsInBasketAfterIncrement;
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		productPage.addToBasket();
		basketPage = new BasketPage();
		productsInBasketBeforeIncrement = basketPage.getAllProducts();
		basketPage.incrementProductQuantity(0);
		productsInBasketAfterIncrement = basketPage.getAllProducts();
		// increment quantity of first product to make it comparable with
		// product after increment in basket
		productsInBasketBeforeIncrement.get(0).setQuantity(productsInBasketBeforeIncrement.get(0).getQuantity() + 1);
		assertTrue(compareListOfProducts(productsInBasketBeforeIncrement, productsInBasketAfterIncrement));
	}

	@Test
	public void decrementQuantityInBasketByButton() {
		List<Product> productsInBasketBeforeDecrement;
		List<Product> productsInBasketAfterDecrement;
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
		resultPage = new ResultPage();
		resultPage.clickFirstProduct();
		productPage = new ProductPage();
		productPage.setQuantity(2).addToBasket();
		basketPage = new BasketPage();
		productsInBasketBeforeDecrement = basketPage.getAllProducts();
		basketPage.decrementProductQuantity(0);
		productsInBasketAfterDecrement = basketPage.getAllProducts();
		// decrement quantity of first product to make it comparable with
		// product after decrement in basket
		productsInBasketBeforeDecrement.get(0).setQuantity(productsInBasketBeforeDecrement.get(0).getQuantity() - 1);
		assertTrue(compareListOfProducts(productsInBasketBeforeDecrement, productsInBasketAfterDecrement));
	}

	// @AfterMethod
	public void afterTest() {
		super.afterTest();
	}

	private boolean compareListOfProducts(List<Product> list1, List<Product> list2) {
		list1.sort(Comparator.comparing(Product::getName));
		list2.sort(Comparator.comparing(Product::getName));
		System.out.println("\n\nComparing:");
		System.out.println(list1.toString());
		System.out.println("With:");
		System.out.println(list2.toString());
		return list1.equals(list2);
	}

	private List<Product> mergeLastProductWithExisting(List<Product> products) {
		Product lastProduct = products.get(products.size() - 1);
		for (int i = 0; i <= products.size() - 2; i++) {
			if (products.get(i).equals(lastProduct)) {
				products.get(i).setQuantity(products.get(i).getQuantity() + lastProduct.getQuantity());
				products.remove(products.size() - 1);
			}
		}
		return products;
	}

}
