package pl.krzysztofwilk.calculator.application;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import pl.krzysztofwilk.calculator.application.Calculator;
import pl.krzysztofwilk.calculator.model.Money;
import pl.krzysztofwilk.calculator.model.MoneyException;

@RunWith(Parameterized.class)
public class CalculatorTest {

	private static Calculator calculator;
	
	@BeforeClass
	public static void setUp() {
		calculator = new Calculator();
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{900.00D, 909.00D}, 
			{1_001.00D, 1016.015D}, 
			{20_000.99D, 20501.01475D}, 
			{1_000_000.00D, 1050000.00D}
		});
	}
	
	@Parameter
	public Double inputValue;
	
	@Parameter(value=1)
	public Double expectedValue;	
	
	@Test
	public void testCalculator() throws MoneyException {
		Money investedMoney = Money.valueOf(inputValue);
		
		Money expectedTotalReturn = Money.valueOf(expectedValue);
		
		Money receivedTotalReturn = calculator.calculateReturn(investedMoney);
		
		assertNotNull(receivedTotalReturn);		
		assertTrue(receivedTotalReturn.greaterThan(investedMoney));
		
		assertEquals(0, expectedTotalReturn.getAmount().compareTo(receivedTotalReturn.getAmount()));
	}	
}
