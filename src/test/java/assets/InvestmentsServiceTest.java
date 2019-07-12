package assets;

import assets.runtime.Scopes;
import assets.tests.Tests;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class InvestmentsServiceTest {

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void buyingLessThanZeroGeneratesException() {
        DataSource ds = Scopes.test().dataSource();
        KindsDB kinds = new KindsDB(ds);
        TransactionsDB transactions = new TransactionsDB(ds);
        AssetsDB assets = new AssetsDB(ds, kinds);
        Kind AAA = kinds.create(Type.STOCK, new Kind.Symbol("AAA"));
        new InvestmentsDefault(transactions, assets).buy(
                LocalDate.now(),
                AAA,
                Tests.money(BigDecimal.ONE),
                BigDecimal.ONE.negate()
        );

    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void sellingLessThanZeroGeneratesException() throws NotEnoughBalance {
        DataSource ds = Scopes.test().dataSource();
        KindsDB kinds = new KindsDB(ds);
        TransactionsDB transactions = new TransactionsDB(ds);
        AssetsDB assets = new AssetsDB(ds, kinds);
        Kind AAA = kinds.create(Type.STOCK, new Kind.Symbol("AAA"));
        new InvestmentsDefault(transactions, assets).sell(
                LocalDate.now(),
                AAA,
                Tests.money(BigDecimal.ONE),
                BigDecimal.ONE.negate()
        );
    }

    @org.junit.Test
    public void balanceAfterBuyingAndSellingIsCorrect() throws NotEnoughBalance {
        DataSource ds = Scopes.test().dataSource();
        KindsDB kinds = new KindsDB(ds);
        AssetsDB assets = new AssetsDB(ds, kinds);
        TransactionsDB transactionsDB = new TransactionsDB(ds);
        Investments investments = new InvestmentsDefault(transactionsDB, assets);
        Kind AAA = kinds.create(Type.BOND, new Kind.Symbol("AAA"));
        MonetaryAmount price = Tests.money(new BigDecimal(100));
        investments.buy(LocalDate.now(), AAA, price, new BigDecimal(5));
        investments.buy(LocalDate.now(), AAA, price, new BigDecimal(10));
        investments.sell(LocalDate.now(), AAA, price, new BigDecimal(3));
        Asset asset = assets.ofType(AAA);
        assertThat(
                "asset total after investing",
                asset.quantity(),
                is(new BigDecimalCloseTo(new BigDecimal(12), new BigDecimal(0.01)))
        );
    }

    @org.junit.Test(expected = NotEnoughBalance.class)
    public void sellingWithoutBalanceThrowsException() throws NotEnoughBalance {
        DataSource ds = Scopes.test().dataSource();
        KindsDB kinds = new KindsDB(ds);
        AssetsDB assets = new AssetsDB(ds, kinds);
        TransactionsDB transactionsDB = new TransactionsDB(ds);
        Investments investments = new InvestmentsDefault(transactionsDB, assets);
        Kind AAA = kinds.create(Type.BOND, new Kind.Symbol("AAA"));
        MonetaryAmount price = Money.of(new BigDecimal(100), Monetary.getCurrency("ARS"));
        investments.sell(LocalDate.now(), AAA, price, new BigDecimal(3));
    }

    @After
    public void removeAll() {
        Tests.removeEverythingFromDB();
    }

}
