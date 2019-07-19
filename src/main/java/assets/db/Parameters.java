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
package assets.db;

import assets.Kind;
import assets.Type;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.money.CurrencyUnit;
import javax.money.NumberValue;

/**
 *
 * @author martinstraus
 */
public class Parameters {

    private static final Map<Class, Function<?, ?>> compatibilityFunctions = new HashMap<>();

    static {
        compatibilityFunctions.put(LocalDateTime.class, (LocalDateTime v) -> Timestamp.valueOf(v));
        compatibilityFunctions.put(LocalDate.class, (LocalDate v) -> Date.valueOf(v));
        compatibilityFunctions.put(Kind.class, (Kind v) -> v.id().value());
        compatibilityFunctions.put(CurrencyUnit.class, (CurrencyUnit v) -> v.getCurrencyCode());
        compatibilityFunctions.put(NumberValue.class, (NumberValue v) -> v.numberValue(BigDecimal.class));
        compatibilityFunctions.put(Type.class, (Type v) -> v.ordinal());
        compatibilityFunctions.put(Kind.Id.class, (Kind.Id v) -> v.value());
    }

    public static void apply(PreparedStatement stmt, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            stmt.setObject(i + 1, compatibleWithJDBC(parameters[i]));
        }
    }

    private static Object compatibleWithJDBC(Object parameter) {
        if (parameter == null) {
            return null;
        }
        Function function = functionForObject(parameter);
        return function != null ? function.apply(parameter) : parameter;
    }

    private static Function functionForObject(Object parameter) {
        return functionForClass(parameter.getClass());
    }

    private static Function functionForClass(Class aClass) {
        Function<?, ?> function = compatibilityFunctions.get(aClass);
        if (function != null) {
            return function;
        }
        for (Class theExpectedClass : compatibilityFunctions.keySet()) {
            if (theExpectedClass.isAssignableFrom(aClass)) {
                return compatibilityFunctions.get(theExpectedClass);
            }
        }
        return null;
    }
}
