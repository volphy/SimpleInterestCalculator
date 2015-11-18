package pl.krzysztofwilk.calculator.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import org.junit.Test;
import pl.krzysztofwilk.calculator.model.Money;
import pl.krzysztofwilk.calculator.model.MoneyException;

public class MoneyTest {
	
	private static final Double DELTA = 0.0D;
	
	@Test(expected = MoneyException.class)
	public void testNegativeAmountSpecifiedAsBigDecimal() throws MoneyException {
		Money.valueOf(BigDecimal.valueOf(-1D));
	}	
	
	@Test(expected = MoneyException.class)
	public void testNegativeAmountSpecifiedAsLong() throws MoneyException {
		Money.valueOf(-1L);
	}
	
	@Test(expected = MoneyException.class)
	public void testNegativeAmountSpecifiedAsDouble() throws MoneyException {
		Money.valueOf(-1.00D);
	}
	
	@Test(expected = MoneyException.class)
	public void testNaNDoubleValue() throws MoneyException {
		Money.valueOf(Double.NaN);
	}
	
	@Test(expected = MoneyException.class)
	public void testNegativeInfinityDoubleValue() throws MoneyException {
		Money.valueOf(Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = MoneyException.class)
	public void testPositiveInfinityDoubleValue() throws MoneyException {
		Money.valueOf(Double.POSITIVE_INFINITY);
	}
	
	@Test
	public void testZeroValue() throws MoneyException {
		Double expectedValue = Double.valueOf(0.0D);
		
		Money retrievedValue = Money.valueOf(expectedValue);
		
		assertNotNull(retrievedValue);
		assertEquals(expectedValue, retrievedValue.getAmount().doubleValue(), DELTA);
	}
	
	
	@Test
	public void testPositiveValueWithoutDecimalDigits() throws MoneyException {
		Long expectedValue = Long.valueOf(125L);
		
		Money retrievedValue = Money.valueOf(expectedValue);
		
		assertNotNull(retrievedValue);
		assertNotNull(retrievedValue.getAmount());
		assertEquals(expectedValue, retrievedValue.getAmount().longValue(), DELTA);
	}
	
	@Test
	public void testPositiveValueWithDecimalDigits() throws MoneyException {
		Double expectedValue = Double.valueOf(1000.99D);
		
		Money retrievedValue = Money.valueOf(expectedValue);
		
		assertNotNull(retrievedValue);
		assertNotNull(retrievedValue.getAmount());
		assertEquals(expectedValue, retrievedValue.getAmount().doubleValue(), DELTA);
	}
	
	@Test
	public void testEquality() throws MoneyException {
		Money firstValue = Money.valueOf(1001.99D);
		Money secondValue = Money.valueOf(1001.99D);
		
		assertTrue(firstValue.equals(secondValue));
	}
	
	@Test
	public void testInequality() throws MoneyException {
		Money firstValue = Money.valueOf(1001.99D);
		Money secondValue = Money.valueOf(11001.99D);
		
		assertFalse(firstValue.equals(secondValue));
	}
	
	@Test
	public void testSmallInequality() throws MoneyException {
		Money firstValue = Money.valueOf(1001.00D);
		Money secondValue = Money.valueOf(1000.99999999999D);
		
		assertFalse(firstValue.equals(secondValue));
	}
	

	
	@Test
	public void testSuccessfulAddOperaion() throws MoneyException {
		Money firstValue = Money.valueOf(1000.99D);
		Money secondValue = Money.valueOf(1000.01D);		
		Money expectedValue = Money.valueOf(2001.00D);
		
		Money retrievedValue = firstValue.add(secondValue);		
		
		assertNotNull(retrievedValue);		
		assertTrue(retrievedValue.equals(expectedValue));
	}
	
	
	@Test(expected = MoneyException.class)
	public void testFailedAddOperaionNullSecondValue() throws MoneyException {
		Money firstValue = Money.valueOf(1000.99D);
		Money secondValue = null;		
		Money expectedValue = Money.valueOf(2001.00D);
		
		Money retrievedValue = firstValue.add(secondValue);		
		
		assertNotNull(retrievedValue);		
		assertTrue(retrievedValue.equals(expectedValue));
	}
	
	
	@Test
	public void testSuccessfulMultiplyOperation() throws MoneyException {
		Money originalValue = Money.valueOf(1000L);
		BigDecimal multiplier = BigDecimal.valueOf(1.5D);
		Money expectedValue = Money.valueOf(1500L);
		
		Money retrievedValue = originalValue.multiply(multiplier);
		
		assertNotNull(retrievedValue);
		assertTrue(expectedValue.equals(retrievedValue));		
	}
	
	@Test(expected = MoneyException.class)
	public void testFailedMultiplyOperationNullOperand() throws MoneyException {
		Money originalValue = Money.valueOf(1000L);
		BigDecimal multiplier = null;
		
		@SuppressWarnings("unused")
		Money retrievedValue = originalValue.multiply(multiplier);				
	}
}
