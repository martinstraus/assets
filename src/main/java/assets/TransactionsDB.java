package assets;

import assets.db.Delete;
import assets.db.InsertOne;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.money.MonetaryAmount;
import javax.sql.DataSource;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class TransactionsDB implements Transactions {

    private final InsertOne insert;
    private final Delete removeAll;

    public TransactionsDB(DataSource ds) {
        insert = new InsertOne(ds, "insert into transactions (creation_timestamp, kind, quantity, unitary_price_currency, unitary_price_value) values (?,?,?,?,?)");
        removeAll = new Delete(ds, "delete from transactions");
    }

    @Override
    public Transaction buy(Kind kind, MonetaryAmount unitaryPrice, BigDecimal units) {
        try {
            long id = insert.execute(LocalDateTime.now(), kind, units,
                    unitaryPrice.getCurrency(), unitaryPrice.getNumber());
            return new Transaction();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void removeAll() {
        try {
            removeAll.delete();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
