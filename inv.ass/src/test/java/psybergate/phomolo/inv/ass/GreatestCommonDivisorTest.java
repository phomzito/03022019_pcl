package psybergate.phomolo.inv.ass;

import org.junit.Test;

import junit.framework.Assert;

public class GreatestCommonDivisorTest {

	@Test
	public void TwelveAndFourDivisorTest() {
		int[] numbers = new int[] { 12, 8 };
		GreatestCommonDivisor greatestCommonDivisor = new GreatestCommonDivisor(numbers);
		Assert.assertEquals(greatestCommonDivisor.getDivisor(), 4);
		Assert.assertEquals(greatestCommonDivisor.gcd(numbers), 4);
		numbers = new int[] { 8, 12 };
		greatestCommonDivisor = new GreatestCommonDivisor(numbers);
		Assert.assertEquals(greatestCommonDivisor.getDivisor(), 4);
		Assert.assertEquals(greatestCommonDivisor.gcd(numbers), 4);
	}

	@Test
	public void inorderValues() {
		int[] numbers = new int[] { 54, 18, 24, 30 };
		GreatestCommonDivisor greatestCommonDivisor = new GreatestCommonDivisor(numbers);
		Assert.assertEquals(greatestCommonDivisor.getDivisor(), 6);
		Assert.assertEquals(greatestCommonDivisor.gcd(numbers), 6);
		numbers = new int[] { 30, 18, 24, 54 };
		greatestCommonDivisor = new GreatestCommonDivisor(numbers);
		Assert.assertEquals(greatestCommonDivisor.getDivisor(), 6);
		Assert.assertEquals(greatestCommonDivisor.gcd(numbers), 6);

	}

	@Test
	public void singleValueTest() {
		GreatestCommonDivisor greatestCommonDivisor = new GreatestCommonDivisor(5);
		Assert.assertEquals(5, greatestCommonDivisor.getDivisor());
		int[] numbers = new int[] { 5, 5 , 5 , 5};
		Assert.assertEquals(5, greatestCommonDivisor.gcd(numbers));
		greatestCommonDivisor = new GreatestCommonDivisor(numbers);
		Assert.assertEquals(greatestCommonDivisor.getDivisor(), 5);
	}
}
