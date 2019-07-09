package assets.config;

import java.util.Objects;

/**
 * The attributes are public because the Toml parser require them to be...
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Database {

    public String driver;
    public String url;
    public String username;
    public String password;

    public Database() {
    }

    public Database(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String driver() {
        return driver;
    }

    public String url() {
        return url;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.driver);
        hash = 41 * hash + Objects.hashCode(this.url);
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Database other = (Database) obj;
        if (!Objects.equals(this.driver, other.driver)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database{" + "driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password + '}';
    }
    
}
