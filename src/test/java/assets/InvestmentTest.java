package assets;

import assets.runtime.Scopes;
import assets.tests.Tests;
import java.math.BigDecimal;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.sql.DataSource;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.number.BigDecimalCloseTo;
import org.javamoney.moneta.Money;
import org.junit.After;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class InvestmentTest {

    @org.junit.Test
    public void balanceAfterBuyingIsCorrect() {
        DataSource ds = Scopes.test().dataSource();
        KindsDB kinds = new KindsDB(ds);
        AssetsDB assets = new AssetsDB(ds, kinds);
        TransactionsDB transactionsDB = new TransactionsDB(ds);
        Kind AAA = kinds.create(Type.BOND, new Kind.Symbol("AAA"));
        MonetaryAmount price = Money.of(new BigDecimal(100), Monetary.getCurrency("ARS"));
        transactionsDB.buy(AAA, price, new BigDecimal(5));
        transactionsDB.buy(AAA, price, new BigDecimal(10));
        transactionsDB.buy(AAA, price, new BigDecimal(15));
        Asset asset = assets.ofType(AAA);
        assertThat(
                "asset total after investing",
                asset.quantity(),
                is(new BigDecimalCloseTo(new BigDecimal(30), new BigDecimal(0.01)))
        );
    }

    @After
    public void removeAll() {
        Tests.removeEverythingFromDB();
    }

}
