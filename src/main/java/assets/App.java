package assets;

import assets.runtime.Scope;
import assets.runtime.Scopes;
import static spark.Spark.*;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class App {

    public static void main(String[] args) {
        new App().run();
    }

    private final Scope scope;
    private final TemplateEngine templateEngine;

    public App() {
        this(Scopes.current(), new ThymeleafTemplateEngine());
    }

    public App(Scope scope, TemplateEngine templateEngine) {
        this.scope = scope;
        this.templateEngine = templateEngine;
    }

    public void run() {
        get("/", new Index(templateEngine));
    }
}
