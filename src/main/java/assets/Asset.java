package assets;

import com.google.gson.JsonElement;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Asset {

    public static final Comparator<Asset> COMPARATOR_BY_KIND = (Asset a, Asset b) -> Kind.COMPARATOR_BY_SYMBOL.compare(a.kind(), b.kind());

    private final Kind type;
    private final BigDecimal quantity;

    public Asset(Kind type, BigDecimal quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public Kind kind() {
        return type;
    }

    public BigDecimal quantity() {
        return quantity;
    }

    public String description() {
        return type.description();
    }

}
