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
package assets.thymeleaf;

import java.util.Locale;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQuery;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import org.javamoney.moneta.format.CurrencyStyle;

/**
 *
 * @author martinstraus
 */
public class Currency {

    private final MonetaryAmountFormat normalPrecisionFormat;
    private final MonetaryAmountFormat extraPrecisionFormat;

    public Currency() {
        Locale locale = new Locale("es", "AR");
        normalPrecisionFormat = MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.of(locale).set(CurrencyStyle.SYMBOL).build());
        extraPrecisionFormat = MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.of(locale).set(CurrencyStyle.SYMBOL).build());
    }

    public String withNormalPrecision(MonetaryAmount value) {
        return normalPrecisionFormat.format(value);
    }

    public String withExtraPrecision(MonetaryAmount value) {
        return extraPrecisionFormat.format(value);
    }
}
