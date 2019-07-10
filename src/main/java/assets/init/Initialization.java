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
package assets.init;

import assets.Kind;
import assets.Kinds;
import assets.NotFound;
import assets.Type;

/**
 *
 * @author martinstraus
 */
public class Initialization {

    private final Kinds kinds;

    public Initialization(Kinds kinds) {
        this.kinds = kinds;
    }

    public void run() {
        createIfNotExists(Type.FOREIGN_CURRENCY, "USD");
        createIfNotExists(Type.BOND, "AY24");
        createIfNotExists(Type.FUND, "ALPHA_RF_USD");
    }

    private void createIfNotExists(Type type, String symbol) {
        createIfNotExists(type, new Kind.Symbol(symbol));
    }

    private void createIfNotExists(Type type, Kind.Symbol symbol) {
        try {
            kinds.findBySymbol(symbol);
        } catch (NotFound ex) {
            kinds.create(type, symbol);
        }
    }
}
