package assets.runtime;

import javax.sql.DataSource;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Scope {
    DataSource dataSource();
}
