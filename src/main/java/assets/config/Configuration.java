package assets.config;

/**
 * Low-level configuration. Implementations will usually read values from some
 * kind of repository (a file, for instance).
 * @author Martín Straus <martinstraus@gmail.com>
 */
public interface Configuration {
    Database database();
}
