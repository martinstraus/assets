package assets;

/**
 *
 * @author martinstraus
 */
public interface AssetTypes {

    AssetType create(AssetType.Symbol symbol);

    AssetType findBySymbol(AssetType.Symbol symbol);
    
    void deleteAll();

}
