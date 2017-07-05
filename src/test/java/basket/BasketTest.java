package basket;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lib.test.BaseTest;
import pageobjects.MainPage;

public class BasketTest extends BaseTest {

	MainPage mainPage;

	@BeforeTest
	public void beforeTest() {
		super.beforeTest();
	}

	@Test
	public void test() {
		goTo("http://x-kom.pl");
		mainPage = new MainPage();
		mainPage.setSearchText("iphone 7");
		mainPage.clickSearchButton();
	}

}
