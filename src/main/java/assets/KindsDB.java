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

import assets.db.Delete;
import assets.db.InsertOne;
import assets.db.SelectOne;
import assets.db.SelectSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import javax.sql.DataSource;

/**
 *
 * @author martinstraus
 */
public class KindsDB implements Kinds {

    private final InsertOne create;
    private final SelectOne<Kind> findById;
    private final SelectOne<Kind> findBySymbol;
    private final Delete deleteAll;
    private final SelectSet<Kind> findAll;

    public KindsDB(DataSource ds) {
        this.create = new InsertOne(ds, "insert into kinds (type, symbol, description) values (?,?,?)");
        this.findById = new SelectOne<Kind>(
                ds,
                "select id, type, symbol, description from kinds where id = ?",
                this::transformOne
        );
        this.findBySymbol = new SelectOne<Kind>(
                ds,
                "select id, type, symbol, description from kinds where symbol = ?",
                this::transformOne
        );
        this.deleteAll = new Delete(ds, "delete from kinds");
        this.findAll = new SelectSet<>(
                ds,
                "select id, type, symbol, description from kinds",
                this::transformOne
        );
    }

    @Override
    public Kind create(Type type, Kind.Symbol symbol, String description) {
        try {
            Integer id = create.execute(type, symbol.value(), description);
            return new Kind(new Kind.Id(id), type, symbol, description);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Kind findById(Kind.Id id) throws NotFound {
        try {
            Kind kind = findById.select(id.value());
            if (kind == null) {
                throw new NotFound(String.format("No kind found with id %d", id.value()));
            }
            return kind;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Kind findBySymbol(Kind.Symbol symbol) throws NotFound {
        try {
            Kind kind = findBySymbol.select(symbol.value());
            if (kind == null) {
                throw new NotFound(String.format("No kind found with symbol %s", symbol.value()));
            }
            return kind;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Kind transformOne(ResultSet rs) {
        try {
            return new Kind(
                    new Kind.Id(rs.getInt("id")),
                    Type.values()[rs.getInt("type")],
                    new Kind.Symbol(rs.getString("symbol")),
                    Optional.ofNullable(rs.getString("description")).orElse("")
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteAll() {
        try {
            deleteAll.delete();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Set<Kind> findAll() {
        try {
            return findAll.select();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
