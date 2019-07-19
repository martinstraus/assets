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
package assets.valuations;

import assets.Asset;
import assets.Kind;
import com.google.gson.JsonObject;
import java.math.BigDecimal;
import java.util.Comparator;
import javax.money.MonetaryAmount;

/**
 *
 * @author martinstraus
 */
public class ValuedAsset {

    public static Comparator<ValuedAsset> COMPARATOR_BY_ASSET = (a, b) -> Asset.COMPARATOR_BY_KIND.compare(a.asset, b.asset);

    private final Valuator valuator;
    private final Asset asset;

    public ValuedAsset(Valuator valuator, Asset asset) {
        this.valuator = valuator;
        this.asset = asset;
    }

    public Kind kind() {
        return asset.kind();
    }

    public BigDecimal quantity() {
        return asset.quantity();
    }

    public MonetaryAmount unitaryPrice() {
        return valuator.lastKnowValuation(asset);
    }

    public MonetaryAmount marketValue() {
        return unitaryPrice().multiply(quantity());
    }

    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("kind", asset.kind().symbol().value());
        json.addProperty("description", asset.description());
        json.addProperty("quantity", quantity());
        json.add("price", assets.json.Objects.toJSON(unitaryPrice()));
        json.add("marketValue", assets.json.Objects.toJSON(marketValue()));
        return json;
    }

}
