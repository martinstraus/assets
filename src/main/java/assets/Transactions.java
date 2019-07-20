package assets;

import java.math.BigDecimal;
import java.util.Set;
import javax.money.MonetaryAmount;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Transactions {

    Transaction registerTransaction(java.time.LocalDateTime purchaseDate, Kind kind, MonetaryAmount unitaryPrice, BigDecimal units);

    void removeAll();

    Set<Transaction> all();

}
