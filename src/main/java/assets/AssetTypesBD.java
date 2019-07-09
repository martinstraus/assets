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

import assets.db.Delete;
import assets.db.InsertOne;
import assets.db.SelectOne;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author martinstraus
 */
public class AssetTypesBD implements AssetTypes {

    private final InsertOne create;
    private final SelectOne<AssetType> findBySymbol;
    private final Delete deleteAll;

    public AssetTypesBD(DataSource ds) {
        this.create = new InsertOne(ds, "insert into asset_types (symbol) values (?)");
        this.findBySymbol = new SelectOne<AssetType>(
                ds,
                "select id, symbol from asset_types where symbol = ?",
                this::transformOne
        );
        this.deleteAll = new Delete(ds, "delete from asset_types");
    }

    @Override
    public AssetType create(AssetType.Symbol symbol) {
        try {
            Integer id = create.execute(symbol.value());
            return new AssetType(new AssetType.Id(id), symbol);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public AssetType findBySymbol(AssetType.Symbol symbol) {
        try {
            return findBySymbol.select(symbol.value());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private AssetType transformOne(ResultSet rs) {
        try {
            return new AssetType(
                    new AssetType.Id(rs.getInt("id")),
                    new AssetType.Symbol(rs.getString("symbol"))
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteAll() {
        try {
            deleteAll.delete();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
