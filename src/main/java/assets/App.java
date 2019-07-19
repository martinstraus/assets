package assets;

import assets.init.Initialization;
import assets.json.LocalDateAdapter;
import assets.json.LocalDateTimeAdapter;
import assets.runtime.Scope;
import assets.runtime.Scopes;
import assets.valuations.DBValuations;
import assets.valuations.Valuations;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static spark.Spark.*;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class App {

    public static void main(String[] args) {
        ThymeleafTemplateEngine thymeleaf = new ThymeleafTemplateEngine();
        new App(Scopes.current(), thymeleaf).run();
    }

    private final TemplateEngine templateEngine;
    private final Kinds kinds;
    private final Investments investments;
    private final Assets assets;
    private final Valuations valuations;
    private final Gson gson;

    public App(Scope scope, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        var ds = scope.dataSource();
        this.kinds = new KindsDB(ds);
        this.assets = new AssetsDB(ds, kinds);
        this.investments = new InvestmentsDefault(new TransactionsDB(ds), assets);
        this.valuations = new DBValuations(ds);
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
                .create();
    }

    public void run() {
        new Initialization(kinds).run();
        staticFiles.location("/static");
        get("/", new Index(templateEngine));
        get("/investments", new assets.investments.ListPage(assets, templateEngine));
        path("/api", () -> {
            post(
                    "/investments",
                    new assets.investments.POST(gson, kinds, investments)
            );
            post(
                    "/kinds/:symbol/valuations",
                    new assets.valuations.POST(gson, valuations, kinds)
            );
        });

    }
}
