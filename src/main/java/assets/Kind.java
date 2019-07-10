package assets;

import java.util.Objects;

/**
 * A specific kind of asset type. For instance, "AY24 bond".
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Kind {

    public static class Id {

        private final int value;

        public Id(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + this.value;
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
            final Id other = (Id) obj;
            if (this.value != other.value) {
                return false;
            }
            return true;
        }

    }

    public static class Symbol {

        private final String value;

        public Symbol(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 59 * hash + Objects.hashCode(this.value);
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
            final Symbol other = (Symbol) obj;
            if (!Objects.equals(this.value, other.value)) {
                return false;
            }
            return true;
        }

    }

    private final Kind.Id id;
    private final Kind.Symbol symbol;

    public Kind(Id id, Symbol symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public Id getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.symbol);
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
        final Kind other = (Kind) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kind{" + "id=" + id + ", symbol=" + symbol + '}';
    }

}
