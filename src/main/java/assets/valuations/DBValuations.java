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
import assets.db.InsertOne;
import java.time.LocalDateTime;
import javax.money.MonetaryAmount;
import javax.sql.DataSource;

/**
 *
 * @author martinstraus
 */
public class DBValuations implements Valuations {

    private final InsertOne insert;

    public DBValuations(DataSource ds) {
        this.insert = new InsertOne(
                ds,
                "insert into valuations (kind, timestamp, unitary_price_currency, unitary_price_value) values (?,?,?,?)"
        );
    }

    @Override
    public Valuation register(Kind kind, LocalDateTime timestamp, MonetaryAmount unitaryPrice) {
        try {
            insert.execute(kind.id(), timestamp, unitaryPrice.getCurrency(), unitaryPrice.getNumber());
            return new Valuation();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
