package assets;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        get("/", (req, res) -> "Hello, World!");
    }
}
