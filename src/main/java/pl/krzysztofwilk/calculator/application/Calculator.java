package pl.krzysztofwilk.calculator.application;

import java.math.BigDecimal;

import pl.krzysztofwilk.calculator.model.InterestRate;
import pl.krzysztofwilk.calculator.model.Money;
import pl.krzysztofwilk.calculator.model.MoneyException;

public class Calculator {

	public Money calculateReturn(Money investedAmount) throws MoneyException {
		
		BigDecimal interestRate = InterestRate.getInterestRate(investedAmount);
		
		Money interestAmount = investedAmount.multiply(interestRate);
		
		Money finalAmount = investedAmount.add(interestAmount);
		
		return finalAmount;
	}
}
