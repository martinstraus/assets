package assets.runtime;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Scopes {

    public static Scope current() {
        try {
            return new SimpleScope(new File(String.format(
                "/etc/assets/%s.cfg",
                System.getProperty("scope", "production")
            )));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
