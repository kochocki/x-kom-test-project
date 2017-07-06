package pageUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PriceUtil {
	public static Double getOnlyPriceValue(String priceText) {

		String patternString = "(.*) z³";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(priceText);
		matcher.find();
		String valueWithoutCurrency = matcher.group(1);
		Double priceDouble = Double.valueOf(valueWithoutCurrency.replace(" ", "").replace(",", "."));
		return priceDouble;
	}
}
