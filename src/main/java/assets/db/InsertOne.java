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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author martinstraus
 */
public class InsertOne {

    private final DataSource ds;
    private final String statement;

    public InsertOne(DataSource ds, String statement) {
        this.ds = ds;
        this.statement = statement;
    }

    public Integer execute(Object... parameters) throws SQLException {
        try (Connection c = ds.getConnection()) {
            try (PreparedStatement stmt = c.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)) {
                Parameters.apply(stmt, parameters);
                stmt.executeUpdate();
                return generatedKey(stmt);
            }
        }
    }

    private Integer generatedKey(PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (!rs.next()) {
                throw new RuntimeException("Could not get generated key.");
            }
            return rs.getInt(1);
        }
    }

}
