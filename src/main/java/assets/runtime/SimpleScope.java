package assets.runtime;

import assets.config.Configuration;
import assets.config.TomlConfig;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class SimpleScope implements Scope {

    private final TomlConfig configuration;

    public SimpleScope(File configFile) throws IOException {
        configuration = new TomlConfig(configFile);
    }

    @Override
    public Configuration configuration() {
        return configuration;
    }

}
