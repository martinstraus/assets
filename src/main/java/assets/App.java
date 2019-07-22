package assets;

import assets.init.Initialization;
import assets.json.LocalDateAdapter;
import assets.json.LocalDateTimeAdapter;
import assets.runtime.Scope;
import assets.runtime.Scopes;
import assets.thymeleaf.ThymeleafTemplateEngine;
import assets.valuations.DBValuations;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static spark.Spark.*;
import spark.TemplateEngine;

public class App {

    public static void main(String[] args) {
        ThymeleafTemplateEngine thymeleaf = new ThymeleafTemplateEngine();
        new App(Scopes.current(), thymeleaf).run();
    }

    private final TemplateEngine templateEngine;
    private final Kinds kinds;
    private final Investments investments;
    private final Assets assets;
    private final DBValuations valuations;
    private final Transactions transactions;
    private final Gson gson;

    public App(Scope scope, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        var ds = scope.dataSource();
        this.kinds = new KindsDB(ds);
        this.assets = new AssetsDB(ds, kinds);
        this.transactions = new TransactionsDB(ds, kinds);
        this.investments = new InvestmentsDefault(transactions, assets);
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
        get("/investments", new assets.investments.ListPage(assets, valuations, templateEngine));
        get("/transactions", new assets.transactions.ListPage(transactions, templateEngine));
        path("/api", () -> {
            get("/investments", new assets.investments.GETAll(gson, assets, valuations));
            post("/investments", new assets.investments.POST(gson, kinds, investments));
            post("/kinds/:symbol/valuations", new assets.valuations.POST(gson, valuations, kinds));
        });

    }
}
