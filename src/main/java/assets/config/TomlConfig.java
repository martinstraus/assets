package assets.config;

import java.io.File;
import java.io.IOException;
import me.grison.jtoml.impl.Toml;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class TomlConfig implements Configuration {

    private final Database database;

    public TomlConfig(File file) throws IOException {
        Toml toml = Toml.parse(file);
        this.database = toml.getAs("database", Database.class);
    }

    @Override
    public Database database() {
        return database;
    }

}
