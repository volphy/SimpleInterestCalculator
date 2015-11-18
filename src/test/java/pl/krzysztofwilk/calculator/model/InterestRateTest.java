package pl.krzysztofwilk.calculator.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import pl.krzysztofwilk.calculator.model.InterestRate;
import pl.krzysztofwilk.calculator.model.Money;
import pl.krzysztofwilk.calculator.model.MoneyException;

@RunWith(Parameterized.class)
public class InterestRateTest {

	@Parameters
	public static Collection<Object[]> data() throws MoneyException {
		return Arrays.asList(new Object[][] {
			{Money.valueOf(0D), BigDecimal.valueOf(0.01D)},
			{Money.valueOf(500D), BigDecimal.valueOf(0.01D)},
			{Money.valueOf(999.99D), BigDecimal.valueOf(0.01D)},
			{Money.valueOf(1_000D), BigDecimal.valueOf(0.015D)},
			{Money.valueOf(3_000D), BigDecimal.valueOf(0.015D)},
			{Money.valueOf(9_999.99D), BigDecimal.valueOf(0.015D)},
			{Money.valueOf(10_000D), BigDecimal.valueOf(0.025D)},
			{Money.valueOf(15_000D), BigDecimal.valueOf(0.025D)},
			{Money.valueOf(99_999.99D), BigDecimal.valueOf(0.025D)},
			{Money.valueOf(100_000D), BigDecimal.valueOf(0.05D)},
			{Money.valueOf(300_000D), BigDecimal.valueOf(0.05D)}
		});
	}

	@Parameter
	public Money inputValue;
	
	@Parameter(value=1)
	public BigDecimal expectedValue;
	
	@Test
	public void testInterestRate() throws MoneyException {
		Money investedMoney = inputValue;
		
		BigDecimal expectedInterestRate = expectedValue;
		
		BigDecimal receivedInterestRate = InterestRate.getInterestRate(investedMoney);	
		
		assertNotNull(receivedInterestRate);

		assertEquals(expectedInterestRate, receivedInterestRate);		
	}
}
