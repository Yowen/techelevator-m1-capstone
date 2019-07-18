package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.process.Money;


public class MoneyTest {

	private Money money;
	
	@Before
	public void setup() {
		money = new Money();
	}
	
	// Third parameter represents the delta parameter - 
	// Delta is used to reflect how close the alpha (expected)
	// and beta (result) values can be and still remain equal.
	// Not providing delta will result in deprecation
	
	@Test
	public void checking_precision_accuracy_on_setCurrentMoney() {
		money.setCurrentMoney(4.00, false);
		money.setCurrentMoney(1.55, true);
		double result = money.getCurrentMoney();
		Assert.assertEquals(2.45, result, 2.45);
	}
	
	
	@Test
	public void setting_false_returns_added_value() {
		money.setCurrentMoney(4.00, false);
		double result = money.getCurrentMoney();
		// Third parameter represents the delta parameter
		// Delta is used to reflect how close the alpha (expected)
		// and beta (result) values can be and still remain equal.
		// Not providing delta will result in deprecation
		Assert.assertEquals(4.00, result, 4);
	}
	
	@Test 
	public void setting_true_return_subtracted_value(){
		money.setCurrentMoney(4.00, false);
		money.setCurrentMoney(3.00, true);
		double result = money.getCurrentMoney();
		Assert.assertEquals(1.00, result, 1);
	}
	
	@Test
	public void one_dollar_is_4_quarters() {
		money.setCurrentMoney(1.00, false);
		String result = money.getChange();
		Assert.assertEquals("4 quarters, 0 dimes, 0 nickels", result);
	}
	
	@Test
	public void one_dollar_and_20_is_4_quarters_2_dimes() {
		money.setCurrentMoney(1.20, false);
		String result = money.getChange();
		Assert.assertEquals("4 quarters, 2 dimes, 0 nickels", result);
	}
	
	@Test
	public void twenty_cents_equals_two_dimes() {
		money.setCurrentMoney(0.20, false);
		String result = money.getChange();
		Assert.assertEquals("0 quarters, 2 dimes, 0 nickels", result);
	}
	
	@Test
	public void one_dollar_5_cents_equals_1_nickel() {
		money.setCurrentMoney(1.05, false);
		String result = money.getChange();
		Assert.assertEquals("4 quarters, 0 dimes, 1 nickels", result);
	}
	
}
