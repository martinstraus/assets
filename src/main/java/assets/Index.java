package assets;

import java.util.Collections;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
public class Index implements Route {

    private final TemplateEngine templateEngine;

    public Index(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request rqst, Response rspns) throws Exception {
        return templateEngine.render(new ModelAndView(Collections.EMPTY_MAP, "/index.html"));
    }

}
