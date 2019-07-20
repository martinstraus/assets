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
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author martinstraus
 */
public class POST implements Route {

    public static class NewInvestment {

        private LocalDateTime date;
        private String kind;
        private BigDecimal quantity;
        private assets.json.Money unitaryPrice;
    }

    private final Gson gson;
    private final Kinds kinds;
    private final Investments investments;

    public POST(Gson gson, Kinds kinds, Investments investments) {
        this.gson = gson;
        this.kinds = kinds;
        this.investments = investments;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        var newInvestment = gson.fromJson(request.body(), NewInvestment.class);
        Kind kind = kinds.findBySymbol(new Kind.Symbol(newInvestment.kind));
        if (kind == null) {
            response.status(400);
            return gson.toJson(new Error(String.format("Kind %s not found.", newInvestment.kind)));
        }
        Transaction transaction = this.investments.buy(
                newInvestment.date,
                kind,
                newInvestment.unitaryPrice.toMonetaryAmount(),
                newInvestment.quantity
        );
        response.status(201);
        response.header("Location", "/transactions/" + transaction.id().value());
        return "";
    }

}
