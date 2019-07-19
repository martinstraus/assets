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

import assets.Kind;
import assets.Kinds;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author martinstraus
 */
public class POST implements Route {

    public final class NewValuation {

        public LocalDateTime timestamp;
        public assets.json.Money unitaryPrice;

        public LocalDateTime timestamp() {
            return timestamp != null ? timestamp : LocalDateTime.now();
        }

    }

    private final Gson gson;
    private final Valuations valuations;
    private final Kinds kinds;

    public POST(Gson gson, Valuations valuations, Kinds kinds) {
        this.gson = gson;
        this.valuations = valuations;
        this.kinds = kinds;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        var newValuation = gson.fromJson(request.body(), NewValuation.class);
        var kindSymbol = request.params(":symbol");
        Kind kind = kinds.findBySymbol(new Kind.Symbol(kindSymbol));
        if (kind == null) {
            response.status(400);
            return gson.toJson(new Error(String.format("Kind %s not found.", kindSymbol)));
        }
        valuations.register(
                kind, newValuation.timestamp(), newValuation.unitaryPrice.toMonetaryAmount()
        );
        response.status(201);
        response.header(
                "Location",
                String.format(
                        "/kinds/%s/valuation/%s",
                        kind.symbol().value(), 
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(newValuation.timestamp())
                )
        );
        return "";
    }

}
