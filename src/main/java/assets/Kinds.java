package assets;

import java.util.Set;

/**
 *
 * @author martinstraus
 */
public interface Kinds {

    Kind create(Kind.Symbol symbol);

    Kind findBySymbol(Kind.Symbol symbol);
    
    Set<Kind> findAll();
    
    void deleteAll();

}
