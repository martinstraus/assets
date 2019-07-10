package assets;

import java.util.Set;

/**
 *
 * @author martinstraus
 */
public interface Kinds {

    Kind create(Type type, Kind.Symbol symbol);

    Kind findById(Kind.Id id) throws NotFound;

    Kind findBySymbol(Kind.Symbol symbol) throws NotFound;

    Set<Kind> findAll();

    void deleteAll();

}
