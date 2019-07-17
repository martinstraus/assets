package assets;

import java.util.Objects;

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

    public Transaction(Id id) {
        this.id = id;
    }

    public Id id() {
        return id;
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
