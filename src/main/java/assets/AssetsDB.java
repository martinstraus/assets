/*
 * Copyright (C) 2019 martinstraus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package assets;

import assets.db.SelectOne;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author martinstraus
 */
public class AssetsDB implements Assets {

    private final AssetTypes assetTypes;
    private final SelectOne<Asset> selectOfType;

    public AssetsDB(DataSource ds, AssetTypes assetTypes) {
        this.assetTypes = assetTypes;
        selectOfType = new SelectOne<>(ds, "select * from assets where asset_type = ?", this::transformOne);
    }

    @Override
    public Asset ofType(AssetType type) {
        try {
            return selectOfType.select(type);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Asset transformOne(ResultSet rs) {
        try {
            return new Asset(
                    assetTypes.findBySymbol(new AssetType.Symbol(rs.getString("asset_type"))),
                    rs.getBigDecimal("quantity")
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
