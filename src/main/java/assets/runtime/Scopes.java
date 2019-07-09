package assets.runtime;

import assets.config.TomlConfig;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Scopes {

    public static Scope current() {
        return forFile(fileForCurrentScope());
    }

    public static Scope test() {
        return forFile(new File("./src/test/config/test.cfg"));
    }

    private static Scope forFile(File file) {
        try {
            return new SimpleScope(new TomlConfig(file));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static File fileForCurrentScope() {
        return new File(String.format("/etc/assets/%s.cfg", scopeName()));
    }

    private static String scopeName() {
        return System.getProperty("scope", "production");
    }
}
