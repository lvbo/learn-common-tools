package io.github.lvbo.lct.jodamoney;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lvbo.lv
 * @date 2021/1/2 15:12
 */
public class JodaMoneyTest {

    @Test
    public void testIt() {
        // create a monetary value
        Money money = Money.parse("USD 23.87");

        // add another amount with safe double conversion
        CurrencyUnit usd = CurrencyUnit.of("USD");
        money = money.plus(Money.of(usd, 12.43d));

        // subtracts an amount in dollars
        money = money.minusMajor(2);

        // multiplies by 3.5 with rounding
        money = money.multipliedBy(3.5d, RoundingMode.DOWN);

        // compare two amounts
        boolean bigAmount = money.isGreaterThan(Money.parse("USD 29"));

        // use a BigMoney for more complex calculations where scale matters
        BigMoney moneyCalc = money.toBigMoney();
    }
}
