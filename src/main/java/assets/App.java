package assets;

import assets.runtime.Scope;
import assets.runtime.Scopes;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        new App().run();
    }

    private final Scope scope;

    public App() {
        this(Scopes.current());
    }

    public App(Scope scope) {
        this.scope = scope;
    }

    public void run() {
        get("/", (req, res) -> "Hello, World!");
    }
}
