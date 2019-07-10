package assets;

import java.math.BigDecimal;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Asset {

    private final AssetType type;
    private final BigDecimal quantity;

    public Asset(AssetType type, BigDecimal quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public AssetType type() {
        return type;
    }

    public BigDecimal quantity() {
        return quantity;
    }

}
