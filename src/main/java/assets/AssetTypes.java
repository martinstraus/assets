package assets;

import java.util.Set;

/**
 *
 * @author martinstraus
 */
public interface AssetTypes {

    AssetType create(AssetType.Symbol symbol);

    AssetType findBySymbol(AssetType.Symbol symbol);
    
    Set<AssetType> findAll();
    
    void deleteAll();

}
