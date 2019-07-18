package assets;

import java.util.Comparator;
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

    public static class Symbol implements Comparable<Symbol> {

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

        @Override
        public int compareTo(Symbol other) {
            return value.compareTo(other.value());
        }

    }

    public static final Comparator<Kind> COMPARATOR_BY_SYMBOL = (Kind a, Kind b) -> a.symbol().compareTo(b.symbol());

    private final Kind.Id id;
    private final Type type;
    private final Kind.Symbol symbol;
    private final String description;

    public Kind(int id, Type type, String symbol, String description) {
        this(new Id(id), type, new Symbol(symbol), description);
    }

    public Kind(Id id, Type type, Symbol symbol, String description) {
        this.id = id;
        this.type = type;
        this.symbol = symbol;
        this.description = description;
    }

    public Id id() {
        return id;
    }

    public Type type() {
        return type;
    }

    public Symbol symbol() {
        return symbol;
    }

    public String description() {
        return description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.type);
        hash = 13 * hash + Objects.hashCode(this.symbol);
        hash = 13 * hash + Objects.hashCode(this.description);
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
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        return true;
    }

}
