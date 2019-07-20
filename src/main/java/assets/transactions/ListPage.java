/*
 * Copyright (C) 2019 martinstraus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package assets.transactions;

import assets.Transactions;
import java.util.HashMap;
import java.util.Map;
import spark.TemplateEngine;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author martinstraus
 */
public class ListPage implements Route {

    private final Transactions transactions;
    private final TemplateEngine templateEngine;

    public ListPage(Transactions transactions,TemplateEngine templateEngine) {
        this.transactions = transactions;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Map<String,Object> model = new HashMap<>();
        model.put("transactions", transactions.all());
        return templateEngine.render(new ModelAndView(model, "/transactions"));
    }

}
