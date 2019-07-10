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

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import javax.sql.DataSource;

/**
 *
 * @author martinstraus
 */
public class SelectSet<T> {

    private final DataSource ds;
    private final String query;
    private final Function<ResultSet, T> transform;

    public SelectSet(DataSource ds, String query, Function<ResultSet, T> transform) {
        this.ds = ds;
        this.query = query;
        this.transform = transform;
    }

    public Set<T> select(Object... parameters) throws SQLException {
        try (Connection c = ds.getConnection()) {
            try (PreparedStatement stmt = c.prepareStatement(query)) {
                Parameters.apply(stmt, parameters);
                try (ResultSet rs = stmt.executeQuery()) {
                    return transform(rs);
                }
            }
        }
    }
    
    private Set<T> transform(ResultSet rs) throws SQLException {
        HashSet<T> set = new HashSet<>();
        while (rs.next()) {
            set.add(transform.apply(rs));
        }
        return set;
    }

}
