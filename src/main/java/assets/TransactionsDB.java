package assets;

import assets.db.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;
import javax.money.MonetaryAmount;
import javax.sql.DataSource;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class TransactionsDB implements Transactions {

    private final Kinds kinds;
    private final InsertOne insert;
    private final Delete removeAll;
    private final SelectSet<Transaction> selectAll;

    public TransactionsDB(DataSource ds, Kinds kinds) {
        this.kinds = kinds;
        insert = new InsertOne(ds, "insert into transactions (creation_timestamp, date, kind, quantity, unitary_price_currency, unitary_price_value) values (?,?,?,?,?,?)");
        removeAll = new Delete(ds, "delete from transactions");
        selectAll = new SelectSet<Transaction>(ds, "select * from transactions order by date desc", this::transformOne);
    }

    @Override
    public Transaction registerTransaction(java.time.LocalDateTime purchaseDate, Kind kind, MonetaryAmount unitaryPrice, BigDecimal units) {
        try {
            long id = insert.execute(LocalDateTime.now(), purchaseDate, kind, units,
                    unitaryPrice.getCurrency(), unitaryPrice.getNumber());
            return new Transaction(new Transaction.Id(id), purchaseDate, kind, units, unitaryPrice);
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

    @Override
    public Set<Transaction> all() {
        try {
            return selectAll.select();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Transaction transformOne(ResultSet rs) {
        try {
            return new Transaction(
                    new Transaction.Id(rs.getLong("id")),
                    rs.getTimestamp("date").toLocalDateTime(),
                    kinds.findById(new Kind.Id(rs.getInt("kind"))),
                    rs.getBigDecimal("quantity"),
                    Parameters.monetaryAmount(rs, "unitary_price_currency", "unitary_price_value")
            );
        } catch (SQLException | NotFound ex) {
            throw new RuntimeException(ex);
        }
    }

}
