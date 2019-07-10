package assets;

import java.math.BigDecimal;
import javax.money.MonetaryAmount;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Transactions {

    Transaction buy(Kind kind, MonetaryAmount unitaryPrice, BigDecimal units);
    
    void removeAll();

}
