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
package assets.json;

import com.google.gson.JsonObject;
import java.math.BigDecimal;
import javax.money.MonetaryAmount;

/**
 *
 * @author martinstraus
 */
public class Objects {

    public static JsonObject toJSON(MonetaryAmount value) {
        JsonObject json = new JsonObject();
        json.addProperty("currency", value.getCurrency().getCurrencyCode());
        json.addProperty("value", value.getNumber().numberValue(BigDecimal.class));
        return json;
    }
}
