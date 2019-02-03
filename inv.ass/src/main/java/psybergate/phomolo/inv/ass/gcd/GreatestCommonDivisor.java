package psybergate.phomolo.inv.ass.gcd;

import java.math.BigInteger;

class GreatestCommonDivisor {
	private long divisor;
	private int[] numbers;

	GreatestCommonDivisor(int... numbers) {
		this.numbers = numbers;
		divisor = gcd(numbers);
	}

	int gcd(int[] numbers) {
		int gcd = 1;
		int index = 2;

		if (numbers.length == 1) {
			gcd = numbers[0];
		}
		if (numbers.length > 1) {
			gcd = euclidGCD(numbers[0], numbers[1]);
		}
		while (index < numbers.length) {
			gcd = euclidGCD(gcd, numbers[index]);
			index++;
		}
		return gcd;
	}

	// Euclid Algorithm
	int euclidGCD(int a, int b) {
		if (b == 0) {
			return a;
		}
		return euclidGCD(b, a % b);
	}

	int javaGCD(int first, int second) {
		BigInteger firstValue = new BigInteger(String.valueOf(first));
		BigInteger secondValue = new BigInteger(String.valueOf(second));
		return firstValue.gcd(secondValue).intValue();
	}

	public long getDivisor() {
		return divisor;
	}

	public int[] getNumbers() {
		return numbers;
	}

}
