package assets.config;

import java.io.File;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.*;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Martín Straus <martin.straus@ventapp.com.ar>
 */
public class TomlConfigTest {

    public TomlConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void loadsDatabaseConfigCorrectly() throws IOException {
        assertThat(
            "database config",
            new TomlConfig(new File("./src/test/config/test.cfg")).database(),
            is(equalTo(new Database("jdbc:postgresql/test", "stephen.king", "misery")))
        );
    }

}
