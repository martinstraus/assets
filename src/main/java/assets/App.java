package assets;

import assets.init.Initialization;
import assets.json.LocalDateAdapter;
import assets.runtime.Scope;
import assets.runtime.Scopes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
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
    private final Investments investments;
    private final Gson gson;

    public App() {
        this(Scopes.current(), new ThymeleafTemplateEngine());
    }

    public App(Scope scope, TemplateEngine templateEngine) {
        this.scope = scope;
        this.templateEngine = templateEngine;
        var ds = scope.dataSource();
        this.kinds = new KindsDB(ds);
        this.investments = new InvestmentsDefault(
                new TransactionsDB(ds),
                new AssetsDB(ds, kinds)
        );
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
                .create();
    }

    public void run() {
        new Initialization(kinds).run();
        staticFiles.location("/static");
        get("/", new Index(templateEngine));
        path("/api", () -> {
            post(
                    "/investments",
                    new assets.investments.POST(gson, kinds, investments)
            );
        });

    }
}
