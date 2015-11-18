package pl.krzysztofwilk.calculator.model;

import java.math.BigDecimal;

public final class Money {	
	
	private BigDecimal amount;
	
		
	private Money(BigDecimal amount) {
		this.amount = amount;
	}	
	
	private Money(Double amount) throws MoneyException {		
		this.amount = BigDecimal.valueOf(amount);
	}
	
	private Money(Long amount) throws MoneyException {
		
		this.amount = BigDecimal.valueOf(amount);
	}
	
	
	static public Money valueOf(BigDecimal amount) throws MoneyException{
		if (amount == null){
			throw new MoneyException("Null amount not supported");
		}		
		if (amount.doubleValue() < 0.0D){
			throw new MoneyException("Negative amount not supported");
		}		
		
		return new Money(amount);
	}
	
	static public Money valueOf(Double amount) throws MoneyException{
		if (amount == null){
			throw new MoneyException("Null amount not supported");
		}
		if (amount.equals(Double.NaN)){
			throw new MoneyException("Not-a-number as amount not supported");
		}
		if (amount == Double.NEGATIVE_INFINITY){
			throw new MoneyException("Negative infinity as amount not supported");
		}
		if (amount == Double.POSITIVE_INFINITY){
			throw new MoneyException("Positive infinity as amount not supported");
		}
		if (amount.doubleValue() < 0.0D){
			throw new MoneyException("Negative amount not supported");
		}
		
		return new Money(amount);
	}
	
	static public Money valueOf(Long amount) throws MoneyException{
		if (amount == null){
			throw new MoneyException("Null amount not supported");
		}
		if (amount.longValue() < 0L){
			throw new MoneyException("Negative amount not supported");
		}
		
		return new Money(amount);
	}	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Money other = (Money) obj;
		if (amount.compareTo(other.amount) != 0)
			return false;
		
		return true;	
	}
	
	@Override
	public String toString() {
		return "Money [amount=" + amount + "]";
	}
	

	public BigDecimal getAmount() {
		return this.amount;
	}
	
	
	
	public boolean greaterThan(Money second) throws MoneyException {
		if (second == null){
			throw new MoneyException("Null amount not supported");
		}
		
		return (this.amount.compareTo(second.getAmount()) == 1);
	}	
	
	public boolean lessThan(Money second) throws MoneyException {
		if (second == null){
			throw new MoneyException("Null amount not supported");
		}
		
		return (this.amount.compareTo(second.getAmount()) == -1);
	}
	
	public boolean lessThan(Long second) throws MoneyException {
		if (second == null){
			throw new MoneyException("Null amount not supported");
		}
		
		return (this.amount.compareTo(BigDecimal.valueOf(second)) == -1);
	}
	
	
	public Money add(Money second) throws MoneyException {
		if (second == null){
			throw new MoneyException("Null amount not supported");
		}
		
		return Money.valueOf(this.amount.add(second.amount));
	}
	
	public Money multiply(BigDecimal multiplier) throws MoneyException {
		if (multiplier == null){
			throw new MoneyException("Null amount not supported");
		}
		
		return Money.valueOf(this.amount.multiply(multiplier));
	}				
}
