package assets;

import java.math.BigDecimal;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Asset {

    private final Kind type;
    private final BigDecimal quantity;

    public Asset(Kind type, BigDecimal quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public Kind type() {
        return type;
    }

    public BigDecimal quantity() {
        return quantity;
    }

}
