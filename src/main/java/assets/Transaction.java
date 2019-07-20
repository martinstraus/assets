package assets;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.money.MonetaryAmount;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Transaction {

    public static final class Id {

        private final long value;

        public Id(long value) {
            this.value = value;
        }

        public long value() {
            return value;
        }
    }

    private final Id id;
    private final LocalDateTime timestamp;
    private final Kind kind;
    private final BigDecimal quantity;
    private final MonetaryAmount unitaryPrice;

    public Transaction(Id id, LocalDateTime timestamp, Kind kind, BigDecimal quantity, MonetaryAmount unitaryPrice) {
        this.id = id;
        this.timestamp = timestamp;
        this.kind = kind;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
    }

    public Id id() {
        return id;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public Kind kind() {
        return kind;
    }

    public BigDecimal quantity() {
        return quantity;
    }

    public MonetaryAmount unitaryPrice() {
        return unitaryPrice;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + '}';
    }

}
