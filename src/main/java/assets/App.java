package assets;

import assets.init.Initialization;
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
    private final Kinds kinds;

    public App() {
        this(Scopes.current(), new ThymeleafTemplateEngine());
    }

    public App(Scope scope, TemplateEngine templateEngine) {
        this.scope = scope;
        this.templateEngine = templateEngine;
        this.kinds = new KindsDB(scope.dataSource());
    }

    public void run() {
        new Initialization(kinds).run();
        get("/", new Index(templateEngine));
    }
}
