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

import assets.runtime.Scopes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author martinstraus
 */
public class AssetTypeDBTest {

    @Test
    public void assetTypeIsFindableAfterCreation() {
        AssetTypesBD assetTypesBD = new AssetTypesBD(Scopes.current().dataSource());
        AssetType.Symbol symbol = new AssetType.Symbol("AAA");
        AssetType created = assetTypesBD.create(symbol);
        AssetType found = assetTypesBD.findBySymbol(symbol);
        Assert.assertTrue("found equals created", found.equals(created));
    }
    
    @After
    public void removeAll() {
        new AssetTypesBD(Scopes.current().dataSource()).deleteAll();
    }

}
