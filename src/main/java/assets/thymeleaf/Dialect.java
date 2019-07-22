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

import static java.util.Arrays.asList;
import java.util.HashSet;
import java.util.Set;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 *
 * @author martinstraus
 */
public class Dialect implements IExpressionObjectDialect {

    private static final String DATES = "dates";
    private static final String CURRENCY = "currency";
    private static final String ASSETS = "assets";

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new IExpressionObjectFactory() {
            @Override
            public Set<String> getAllExpressionObjectNames() {
                return new HashSet<>(asList(CURRENCY, DATES, ASSETS));
            }

            @Override
            public Object buildObject(IExpressionContext context, String expressionObjectName) {
                switch (expressionObjectName) {
                    case CURRENCY:
                        return new Currency();
                    case DATES:
                        return new Dates();
                    case ASSETS:
                        return new Assets();
                    default:
                        throw new IllegalArgumentException(String.format("Unsupported object: %s.", expressionObjectName));
                }
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }
        };
    }

    @Override
    public String getName() {
        return ASSETS;
    }

}
