package assets;

import java.util.Objects;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Asset {

    private final AssetType type;
    private final String name;

    public Asset(AssetType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + Objects.hashCode(this.name);
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
        final Asset other = (Asset) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

}
