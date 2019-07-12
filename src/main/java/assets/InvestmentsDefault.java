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

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.money.MonetaryAmount;

/**
 *
 * @author martinstraus
 */
public class InvestmentsDefault implements Investments {

    private final Transactions transactions;
    private final Assets assets;

    public InvestmentsDefault(Transactions transactions, Assets assets) {
        this.transactions = transactions;
        this.assets = assets;
    }

    @Override
    public Transaction buy(java.time.LocalDate purchaseDate, Kind kind, MonetaryAmount unitaryPrice, BigDecimal units) {
        assertIsGreaterThanZero(units);

        return transactions.registerTransaction(purchaseDate, kind, unitaryPrice, units);
    }

    @Override
    public Transaction sell(LocalDate purchaseDate, Kind kind,
            MonetaryAmount unitaryPrice, BigDecimal units) throws NotEnoughBalance {
        assertIsGreaterThanZero(units);
        if (assets.ofType(kind).quantity().compareTo(units) < 0) {
            throw new NotEnoughBalance(String.format(
                    "You don't have %f of %s available to sell",
                    units, kind.symbol().value()
            ));
        }
        return transactions.registerTransaction(purchaseDate, kind, unitaryPrice, units.abs().negate());
    }

    private void assertIsGreaterThanZero(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
    }

}
