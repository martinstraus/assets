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
package assets.investments;

import assets.*;
import assets.json.Arrays;
import assets.lang.Sets;
import assets.valuations.Valuator;
import assets.valuations.ValuedAsset;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Set;
import java.util.SortedSet;
import static java.util.stream.Collectors.toSet;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author martinstraus
 */
public class GETAll implements Route {

    private final Gson gson;
    private final Assets assets;
    private final Valuator valuator;

    public GETAll(Gson gson, Assets assets, Valuator valuator) {
        this.gson = gson;
        this.assets = assets;
        this.valuator = valuator;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        SortedSet<ValuedAsset> assets = Sets.sortedBy(valuedAssets(), ValuedAsset.COMPARATOR_BY_ASSET);
        Set<JsonObject> assetsAsJson = assets.stream().map(ValuedAsset::toJSON).collect(toSet());
        response.status(200);
        response.header("Content-Type", "application/json");
        return Arrays.array(assetsAsJson).toString();
    }

    private Set<ValuedAsset> valuedAssets() {
        return Sets.transformed(assets.all().sortedByKind(), (asset) -> new ValuedAsset(valuator, asset));
    }


}
