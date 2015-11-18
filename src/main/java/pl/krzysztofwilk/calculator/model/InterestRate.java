package pl.krzysztofwilk.calculator.model;

import java.math.BigDecimal;

public final class InterestRate {
	
	private InterestRate() {}
	
	private static final Long[] LIMITS = {
			Long.valueOf(1000L),
			Long.valueOf(10000L),
			Long.valueOf(100000L)
	};
	
	private static final BigDecimal[] INTEREST_RATES = {
			BigDecimal.valueOf(0.01D),  // 1.0%
			BigDecimal.valueOf(0.015D), // 1.5%
			BigDecimal.valueOf(0.025D), // 2.5%
			BigDecimal.valueOf(0.05D)   // 5%
	};
	
	
	public static BigDecimal getInterestRate(Money value) throws MoneyException {
		BigDecimal interestRate;
		
		if (value.lessThan(LIMITS[0])) {
			interestRate = INTEREST_RATES[0];
		} else if (value.lessThan(LIMITS[1])) {
			interestRate = INTEREST_RATES[1];
		} else if (value.lessThan(LIMITS[2])) {
			interestRate = INTEREST_RATES[2];
		} else  {
			interestRate = INTEREST_RATES[3];
		}
		
		return interestRate;
	}
}
