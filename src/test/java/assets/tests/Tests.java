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
package assets.tests;

import assets.KindsDB;
import assets.TransactionsDB;
import assets.runtime.Scopes;
import java.math.BigDecimal;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.sql.DataSource;
import org.javamoney.moneta.Money;

/**
 *
 * @author martinstraus
 */
public class Tests {

    public static void removeEverythingFromDB() {
        DataSource ds = Scopes.test().dataSource();
        new TransactionsDB(ds, null).removeAll();
        new KindsDB(ds).deleteAll();
    }

    public static MonetaryAmount money(BigDecimal value) {
        return Money.of(value, Monetary.getCurrency("ARS"));
    }
}
