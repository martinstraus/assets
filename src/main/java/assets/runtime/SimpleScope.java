package assets.runtime;

import assets.config.Configuration;
import assets.config.Database;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

/**
 * Application-level configuration that could change between runtime
 * environments.
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class SimpleScope implements Scope {

    private final Configuration configuration;

    public SimpleScope(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public DataSource dataSource() {
        Database config = configuration.database();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(config.driver());
        hikariConfig.setJdbcUrl(config.url());
        hikariConfig.setUsername(config.username());
        hikariConfig.setPassword(config.password());
        return new HikariDataSource(hikariConfig);
    }

}
